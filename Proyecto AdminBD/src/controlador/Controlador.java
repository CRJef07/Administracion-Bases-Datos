package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Controlador {

    private final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";

    private Connection conector;

    public Controlador() {
        this.conector = null;
    }

    public boolean getConexion(String user, String pass) {
        try {
            if (user.equals("sys") || user.equals("SYS") || user.equals("SYSTEM") || user.equals("system")) {
                user = "sys as SYSDBA";
            }

            Class.forName(DRIVER);
            conector = DriverManager.getConnection(URL, user, pass);

            if (conector != null) {
                return true;
            }

        } catch (SQLException e) {
            System.err.println("ERROR SQLException:  " + e);

        } catch (ClassNotFoundException e) {
            System.err.println("ERROR ClassNotFoundException:  " + e);
        }

        return false;
    }

    public ResultSet cargarUsuario() {
        String query = "SELECT USERNAME FROM DBA_USERS";

        try {
            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            return resultado;

        } catch (SQLException ex) {
            return null;
        }
    }

    public void cerrar() {
        try {
            conector.close();
            if (conector.isClosed() == true) {
                System.out.println("CONEXION CERRADA CON EXITO");
            } else {
                System.err.println("CONEXION NO CERRADA");
            }
        } catch (SQLException e) {
            System.err.println("ERROR SQLException:  " + e);
        } finally {
            conector = null;
        }
    }

    //Retorna el total de los privilegios en el sistema
    public ResultSet cargarTotalPrivilegios() {

        String query = "select * from session_privs";

        try {
            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            return resultado;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }

    }

    //Retorna todos los roles existentes en el sistema
    public ResultSet cargarTotalRoles() {

        //conectar("system", "root");
        String query = "SELECT role FROM dba_roles";

        try {
            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            return resultado;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    //Revoca privilegios de un rol
    public boolean revocarPrivilegiosRol(String rol, String priv) {

        try {

            String query = "revoke " + priv + " from " + rol;
            Statement stmt = conector.createStatement();

            stmt.execute(query);
            stmt.close();
            conector.commit();
            return true;

        } catch (SQLException ex) {
            System.err.println("ERROR SQLException:  " + ex);
            return false;
        }
    }

    //Otorga privilegios a un rol
    public boolean otorgarPrivilegiosRol(String rol, String priv) {

        try {

            String query = "GRANT " + priv + " TO " + rol;
            Statement stmt = conector.createStatement();

            stmt.execute(query);
            stmt.close();
            conector.commit();
            return true;

        } catch (SQLException ex) {
            System.err.println("ERROR SQLException:  " + ex);
            return false;
        }
    }

//    //Otorga un rol a un usuario Original
//    public boolean otorgarRolUsuario(String rol, String user){
//
//        try{
//
//            String query = "GRANT " + rol + " TO "+  user;
//            Statement stmt = conector.createStatement();
//
//            stmt.execute(query);
//            stmt.close();
//            conector.commit();
//            return true;
//
//        }catch(SQLException ex){
//            System.err.println("ERROR SQLException:  " + ex);
//            return false;
//        }
//    }
//    //Revoca rol a un usuario Original
//    public boolean revocarRolUsuario(String rol, String user){
//
//        try{
//
//            String query = "REVOKE " + rol + " FROM "+  user;
//            Statement stmt = conector.createStatement();
//
//            stmt.execute(query);
//            stmt.close();
//            conector.commit();
//            return true;
//            
//        }catch(SQLException ex){
//            System.err.println("ERROR SQLException:  " + ex);
//            return false;
//        }
//    }
    //Carga sesiones EDWIN
    public ResultSet cargarSesionesBD() {

        //conectar("system", "root");
        String query = " SELECT s.sid, s.serial#, s.username, s.program FROM gv$session s JOIN gv$process p ON p.addr = s.paddr AND p.inst_id = s.inst_id WHERE  s.type != 'BACKGROUND'";

        try {

            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            return resultado;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    //Cierra sesion EDWIN
    public boolean cerrarSesionBD(String sid, String serial, int band) {

        String query;

        if (band == 1) {
            query = "ALTER SYSTEM KILL SESSION '" + sid + "," + serial + "' IMMEDIATE";
        } else {
            query = "ALTER SYSTEM DISCONNECT SESSION '" + sid + "," + serial + "' IMMEDIATE";
        }

        try {
            Statement stmt = conector.createStatement();

            stmt.executeUpdate(query);
            stmt.close();
            conector.commit();
            //  Desconectar();//desconecta la sesion de sistem
            return true;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    //Carga directorios EDWIN
    public ResultSet cargarDirectorios() {

        String query = "SELECT NAME, PATH FROM datapump_dir_objs";

        try {
            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);
            //Desconectar();
            return resultado;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    //Carga tablas usuario JORGE
    public ResultSet cargarTablasUsuario(String usuario) {

        String query = "SELECT table_name FROM all_tables where owner = '" + usuario + "'";

        try {
            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);
            return resultado;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    //Crea directorio JORGE
    public boolean crearDirectorio(String nombreDir, String pathDir, String user) {

        try {

            String query = "CREATE OR REPLACE DIRECTORY " + nombreDir + " AS '" + pathDir + "'";
            Statement stmt = conector.createStatement();

            stmt.execute(query);
            //conector.commit();

            //TRY PARA OTORGAR PERMISOS AL USUARIO ACTUAL
            try {

                query = "GRANT WRITE, READ ON DIRECTORY " + nombreDir + " TO " + user;
                stmt.execute(query);
                stmt.close();
                //conector.commit();
                return true;

            } catch (SQLException ex) {
                System.err.println("ERROR SQLException:  " + ex);
                return false;
            }

        } catch (SQLException ex) {
            System.err.println("ERROR SQLException:  " + ex);
            return false;
        }
    }

    //Elimina el directorio
    public boolean EliminarDirectorio(String nombreDir) {

        try {

            String query = "DROP DIRECTORY " + nombreDir;

            Statement stmt = conector.createStatement();

            stmt.execute(query);
            stmt.close();
            return true;

        } catch (SQLException ex) {

            System.err.println("ERROR SQLException:  " + ex);
            return false;
        }
    }

//   
//    //Tunning Original
//    private boolean executarQueryOptimizar(String query){
//       
//        try{
//            
//            Statement stmt = conector.createStatement();
//
//            query = "EXPLAIN PLAN FOR " + query;
//
//            stmt.execute(query);
//            stmt.close();
//            conector.commit();
//            return true;
//            
//        }catch(SQLException ex){
//            System.err.println("ERROR SQLException:  " + ex);
//            return false;
//        }
//    }
//    //Explain Plan Original
//    public ResultSet obtenerExplainPlan(String query){
//           
//        //Si se ejecuta correctamente
//        if(executarQueryOptimizar(query)){
//            
//           query = "SELECT OPERATION,OBJECT_NAME,OPTIONS,COST,ACCESS_PREDICATES,FILTER_PREDICATES FROM plan_table";
//           
//            try{
//                
//                Statement stmt = null;
//                stmt = conector.createStatement();;
//                ResultSet resultado = stmt.executeQuery(query);
//                return resultado;
//                
//            }catch(SQLException ex){
//                System.out.println(ex.toString());
//                return null;
//            }
//            
//        }else return null;       
//    }
//       
    //JORGE
    public ResultSet cargarColumnasUser(String usuario, String tabla) {

        String query = "select COLUMN_NAME from ALL_TAB_COLUMNS where owner = '" + usuario + "' AND TABLE_NAME = '" + tabla + "'";

        try {

            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);
            return resultado;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }

    }

    //EDWIN
    public ResultSet cargarPkFkKeyTabla(String tabla) {

        String query = "SELECT cols.column_name, cons.constraint_name,cons.constraint_type\n"
                + "FROM all_constraints cons, all_cons_columns cols\n"
                + "WHERE cols.table_name = '" + tabla + "'\n"
                + "AND NOT cons.constraint_type = 'C'\n"
                + "AND cons.constraint_name = cols.constraint_name\n"
                + "AND cons.owner = cols.owner";

        try {
            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);
            return resultado;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public ResultSet cargarIndicesTabla(String tabla) {

        String query = "SELECT c.index_name, c.column_name FROM all_indexes i JOIN ALL_ind_columns c "
                + "ON i.index_name = c.index_name WHERE  i.table_name = '" + tabla + "'";

        try {

            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);
            return resultado;

        } catch (SQLException ex) {
            System.out.println(ex.toString() + "cargarIndices");
            return null;
        }
    }

    public boolean eliminarIndice(String nomIndice) {

        String query = "DROP INDEX " + nomIndice;

        try {

            Statement stmt = conector.createStatement();
            stmt.execute(query);
            stmt.close();
            conector.commit();
            return true;

        } catch (SQLException ex) {
            System.err.println("ERROR SQLException:  " + ex);
            return false;
        }
    }

    public boolean crearPK(String tabla, String col, String nomPK) {

        String query = "ALTER TABLE " + tabla + " ADD CONSTRAINT " + nomPK + " PRIMARY KEY (" + col + ")";

        try {

            Statement stmt = conector.createStatement();
            stmt.execute(query);
            stmt.close();
            conector.commit();
            return true;

        } catch (SQLException ex) {
            System.err.println("ERROR SQLException:  " + ex);
            return false;
        }
    }

    public boolean eliminarPK(String tabla, String nomPK) {

        String query = "ALTER TABLE " + tabla + " DROP CONSTRAINT " + nomPK;

        try {

            Statement stmt = conector.createStatement();
            stmt.execute(query);
            stmt.close();
            conector.commit();
            return true;

        } catch (SQLException ex) {
            System.err.println("ERROR SQLException:  " + ex);
            return false;
        }
    }

    /*Objetos*/
    public ResultSet cargarInfoTabla(String usuario, String tabla) {

        String query = "select column_name,data_type from ALL_TAB_COLUMNS where owner = '"
                + usuario + "' AND TABLE_NAME = '" + tabla + "'";

        try {

            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);
            return resultado;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public boolean eliminarTabla(String tabla) {

        String query = "DROP TABLE " + tabla;

        try {

            Statement stmt = conector.createStatement();
            stmt.execute(query);
            stmt.close();
            conector.commit();
            return true;

        } catch (SQLException ex) {
            System.err.println("ERROR SQLException:  " + ex);
            return false;
        }
    }

    //metodo para base de datos generico
    public boolean ejecutarCodigo(String cadena) {

        try {

            Statement stmt = null;
            stmt = conector.createStatement();
            stmt.execute(cadena);
            return true;

        } catch (SQLException ex) {
            System.err.println("ERROR SQLException:  " + ex);
            return false;
        }
    }

    public boolean createTable(String nombTable, String cadena) {

        cadena = "CREATE TABLE " + nombTable + "(" + cadena + ")";

        try {

            Statement stmt = null;
            stmt = conector.createStatement();
            stmt.execute(cadena);
            return true;

        } catch (SQLException ex) {
            System.err.println("ERROR SQLException:  " + ex);
            return false;
        }
    }

    public void GeneraStats(String usuario, String tabla) {

        this.getConexion("sys", "root123");
        String query;
// if(tabla.equals("Schema"))
// {
// query="EXEC dbms_stats.gather_schema_stats('"+usuario+"',cascade => true);";
// }
// else
// {
// query="ANALYZE TABLE "+usuario+"."+tabla+" COMPUTE STATISTICS";
// }
// try
// {
// Statement stmt=null;
// stmt=conector.createStatement();
// stmt.executeQuery(query);
// JOptionPane.showMessageDialog(null, "¡La Estadistica se realizo exitosamente!");
// }
// catch(SQLException ex)
// {
// Logger.getLogger(InicioSesion.class.getName()).log(Level.SEVERE, null, ex);
// }
        if (tabla.equals("Schema")) {

            ResultSet resultado = this.cargarTablasUsuario(usuario);

            try {

                while (resultado.next()) {

                    query = "ANALYZE TABLE " + usuario + "." + resultado.getString("TABLE_NAME") + " COMPUTE STATISTICS";
                    Statement stmt = null;
                    stmt = conector.createStatement();
                    stmt.execute(query);
                }

                JOptionPane.showMessageDialog(null, "Estadistica Realizada con exito!");

            } catch (SQLException ex) {
                System.err.println("ERROR SQLException:  " + ex);
            }

        } else {

            query = "ANALYZE TABLE " + usuario + "." + tabla + " COMPUTE STATISTICS";

            try {

                Statement stmt = null;
                stmt = conector.createStatement();
                stmt.execute(query);
                JOptionPane.showMessageDialog(null, "Estadistica Realizada con exito!");

            } catch (SQLException ex) {
                System.err.println("ERROR SQLException:  " + ex);
            }
        }
    }
    //Estadisticas EDWIN

    public ResultSet ConsultaStats(String usuario, String tabla) {
        this.getConexion("sys", "root123");

        String query;

        if (tabla.equals("Schema")) {

            query = " SELECT OWNER, TABLE_NAME, NUM_ROWS, LAST_ANALYZED FROM DBA_TABLES WHERE OWNER='" + usuario + "'";

        } else {
            query = " SELECT OWNER, TABLE_NAME, NUM_ROWS, LAST_ANALYZED FROM DBA_TABLES WHERE OWNER='" + usuario + "' AND TABLE_NAME='" + tabla + "'";
        }

        try {

            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);
            return resultado;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    //PARAMETROS
    //Informacion de la instancia
    public ResultSet infoInstancia() {

        String query = "select * from v$instance";

        try {
            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            return resultado;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    //Nombre de la Base de datos
    public ResultSet nombreDB() {

        String query = "select value from v$system_parameter where name = 'db_name'";

        try {
            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            return resultado;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    //Parámetros de la base de datos
    public ResultSet parametrosDB() {

        String query = "select * from v$system_parameter";

        try {
            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            return resultado;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    //Productos Oracle instalados y versiones
    public ResultSet prodOracle() {

        String query = "select * from product_component_version";

        try {
            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            return resultado;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    //Productos Oracle instalados y versiones
    public ResultSet ipServer() {

        String query = "select utl_inaddr.get_host_address IP from DUAL";

        try {
            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            return resultado;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    //Ubicación y nombre del fichero SPFILE
    public ResultSet SPFILEfile() {

        String query = "select value from v$system_parameter where name = 'spfile'";

        try {
            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            return resultado;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    //Ubicación y nombre de los ficheros de control
    public ResultSet controlfiles() {

        String query = "select value from v$system_parameter where name = 'control_files'";

        try {
            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            return resultado;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    //Todos los ficheros de datos Y su Ubicación
    public ResultSet allfiles() {

        String query = "select * from V$DATAFILE";

        try {
            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            return resultado;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    //Ficheros temporales
    public ResultSet tempfiles() {

        String query = "select * from V$TEMPFILE";

        try {
            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            return resultado;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    //Ficheros de RedoLog
    public ResultSet redoLogfiles() {

        String query = "select member from v$logfile";

        try {
            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            return resultado;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    //Ficheros de Archive Log
//    public ResultSet archiveLogfiles(){
//
//        String query = "show parameters archive_dest";
//        
//        try{
//            Statement stmt = null;
//            stmt = conector.createStatement();;
//            ResultSet resultado = stmt.executeQuery(query);
//
//            return resultado;
//        }catch(SQLException ex){
//            System.out.println(ex.toString());
//            return null;
//        }
//    }
//    
    //Espacio de los Tablespaces EDWIN
    public ResultSet tamTablespaces() {

        String query = "SELECT t.tablespace_name \"Tablespace\",\n"
                + "t.status \"Estado\",\n"
                + "ROUND (MAX (d.bytes) / 1024 / 1024, 2) \"MB Tamaño\",\n"
                + "ROUND ((MAX (d.bytes) / 1024 / 1024)\n"
                + "- (SUM (DECODE (f.bytes, NULL, 0, f.bytes)) / 1024 / 1024),\n"
                + "2) \"MB Usados\",\n"
                + "ROUND (SUM (DECODE (f.bytes, NULL, 0, f.bytes)\n"
                + ") / 1024 / 1024, 2) \"MB Libres\",\n"
                + "t.pct_increase \"% incremento\",\n"
                + "SUBSTR (d.file_name, 1, 80) \"Fichero de datos\"\n"
                + "FROM DBA_FREE_SPACE f, DBA_DATA_FILES d, DBA_TABLESPACES t\n"
                + "WHERE t.tablespace_name = d.tablespace_name\n"
                + "AND f.tablespace_name(+) = d.tablespace_name\n"
                + "AND f.file_id(+) = d.file_id\n"
                + "GROUP BY t.tablespace_name,\n"
                + "d.file_name,\n"
                + "t.pct_increase,\n"
                + "t.status\n"
                + "ORDER BY 1, 3 DESC";

        try {
            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            return resultado;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    //Tamaño ocupado por la base de datos
    public ResultSet tamBD() {

        String query = "select SUM(BYTES)/1024/1024 MB from DBA_EXTENTS";

        try {
            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            return resultado;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    //Tamaño de ficheros de la base de datos
    public ResultSet tamFilesBD() {

        String query = "select SUM(bytes)/1024/1024 MB from dba_data_files";

        try {
            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            return resultado;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    //Ocupación de todos los objetos
    public ResultSet tamObjs() {

        String query = "SELECT SEGMENT_NAME,SUM(BYTES)/1024/1024 FROM DBA_EXTENTS MB GROUP BY SEGMENT_NAME ORDER BY 2 DESC";

        try {
            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            return resultado;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    //Propietarios de objetos y número de objetos
    public ResultSet objOwner() {

        String query = "SELECT owner, COUNT(owner) Numero FROM dba_objects GROUP BY owner ORDER BY Numero DESC";

        try {
            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            return resultado;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    //Información Tablespaces
    public ResultSet infoTablespaces() {

        String query = "select * from V$TABLESPACE";

        try {
            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            return resultado;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    //Borrar Explain Plan STEFANNY
    public boolean BorrarTablaPlanes() {
        try {

            Statement stmt = conector.createStatement();
            stmt.execute("DELETE PLAN_TABLE");
            stmt.close();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    //Tunning STEFANNY
    public String executarQueryOptimizar(String query) {

        try {

            Statement stmt = conector.createStatement();

            query = query.replaceAll("'\'", " ");
            query = "EXPLAIN PLAN FOR " + query;

            stmt.execute(query);
            stmt.close();
            return "true";

        } catch (SQLException ex) {
            return ex.getMessage();
        }
    }

    //Explain Plan STEFANNY
    public ResultSet obtenerExplainPlan() {
        //String query = "SELECT OPERATION,OBJECT_NAME,OPTIONS,COST,ACCESS_PREDICATES,FILTER_PREDICATES FROM plan_table";
        //String query = "SELECT OPERATION AS OPERACION, OBJECT_NAME AS OBJETO, OPTIONS as FECHA FROM plan_table";
        String query = "SELECT SUBSTR (LPAD(' ', LEVEL-1) || OPERATION || ' (' || OPTIONS|| ')',1,30 ) OPERACION, OBJECT_NAME OBJETO, TIMESTAMP FECHA"
                + " FROM PLAN_TABLE START WITH ID = 0 CONNECT BY PRIOR ID=PARENT_ID";

        try {

            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);
            return resultado;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    //Crea Rol de usuario KATHERINE
    public boolean crearRol(String rol) {

        try {
            String query1 = "alter session set \"_ORACLE_SCRIPT\" =true";
            String query = "CREATE ROLE " + rol;

            Statement stmt = conector.createStatement();

            stmt.execute(query1);
            stmt.execute(query);
            stmt.close();
            conector.setAutoCommit(false);
            conector.commit();
            return true;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }

    }

    //Crea usuario KATHERINE
    public boolean crearUsuario(String usuario) {
        try {
            String query1 = "alter session set \"_ORACLE_SCRIPT\" =true";
            String query = "CREATE USER " + usuario;

            Statement stmt = conector.createStatement();

            stmt.execute(query1);
            stmt.execute(query);
            stmt.close();
            conector.setAutoCommit(false);
            conector.commit();
            return true;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    //Retorna todos los roles de usuario KATHERINE
    public ResultSet cargarRoles(String usuario) {

        String query = "SELECT GRANTED_ROLE FROM DBA_ROLE_PRIVS WHERE GRANTEE='" + usuario + "'";

        try {
            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);
            return resultado;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    //Revoca rol a un usuario KATHERINE
    public boolean revocarRolUsuario(String rol, String user) {

        try {
            String query = "REVOKE " + rol + " FROM " + user;
            Statement stmt = conector.createStatement();

            stmt.execute(query);
            stmt.close();
            conector.setAutoCommit(false);
            conector.commit();
            return true;

        } catch (SQLException ex) {
            System.err.println("ERROR SQLException:  " + ex);
            return false;
        }
    }

    //Otorga un rol a un usuario KATHERINE
    public boolean otorgarRolUsuario(String rol, String user) {

        try {

            String query = "GRANT " + rol + " TO " + user;
            Statement stmt = conector.createStatement();

            stmt.execute(query);
            stmt.close();
            conector.setAutoCommit(false);
            conector.commit();
            return true;

        } catch (SQLException ex) {
            System.err.println("ERROR SQLException:  " + ex);
            return false;
        }
    }

    //Franklin sofia
    public boolean auditarConexiones() {
        try {
            String query = "Audit connect";
            Statement stmt = conector.createStatement();

            stmt.execute(query);
            stmt.close();
            conector.setAutoCommit(false);
            conector.commit();
            return true;

        } catch (SQLException ex) {
            System.err.println("ERROR SQLException:  " + ex);
            return false;
        }
    }

    public boolean auditarIniciosSesion() {
        try {
            String query = "audit session";
            Statement stmt = conector.createStatement();

            stmt.execute(query);
            stmt.close();
            conector.setAutoCommit(false);
            conector.commit();
            return true;

        } catch (SQLException ex) {
            System.err.println("ERROR SQLException:  " + ex);
            return false;
        }
    }

    public boolean auditarIniciosSesionExitosos() {
        try {
            String query = "audit session whenever successful";
            Statement stmt = conector.createStatement();

            stmt.execute(query);
            stmt.close();
            conector.setAutoCommit(false);
            conector.commit();
            return true;

        } catch (SQLException ex) {
            System.err.println("ERROR SQLException:  " + ex);
            return false;
        }
    }

    public boolean auditarIniciosSesionNoExitosos() {
        try {
            String query = "audit session whenever not successful";
            Statement stmt = conector.createStatement();

            stmt.execute(query);
            stmt.close();
            conector.setAutoCommit(false);
            conector.commit();
            return true;

        } catch (SQLException ex) {
            System.err.println("ERROR SQLException:  " + ex);
            return false;
        }
    }

    public boolean auditarDeAccion() {
        try {
            String query = "audit role";
            Statement stmt = conector.createStatement();

            stmt.execute(query);
            stmt.close();
            conector.setAutoCommit(false);
            conector.commit();
            return true;

        } catch (SQLException ex) {
            System.err.println("ERROR SQLException:  " + ex);
            return false;
        }
    }

    public boolean auditarTabla(String schema, String tabla) {
        try {
            String query = "Audit insert, update, delete,select on " + schema + "." + tabla + " by access";
            Statement stmt = conector.createStatement();

            stmt.execute(query);
            stmt.close();
            conector.setAutoCommit(false);
            conector.commit();
            return true;

        } catch (SQLException ex) {
            System.err.println("ERROR SQLException:  " + ex);
            return false;
        }
    }

    public ResultSet verAuditoriaPorAccion() {

        String query = "SELECT sessionid,userhost,username,action_name, obj_name, action FROM sys.dba_audit_trail WHERE (action_name = 'INSERT') or (action_name = 'UPDATE') "
                + "or (action_name = 'DELETE') or ( action_name = 'SELECT' )";

        try {
            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);
            return resultado;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public ResultSet visualizarAuditoriaSesiones() {

        String query = "select Username, DECODE (Returncode, '0', 'Conectado',"
                + "'1005', 'Fallo - Null', 1017, 'Fallo', Returncode) Tipo_Suceso,"
                + "TO_CHAR(Timestamp, 'DD-MM-YY HH24:MI:SS')"
                + "Hora_Inicio_Sesion, TO_CHAR(Logoff_Time, 'DD-MM-YY"
                + "HH24:MI:SS') Hora_Fin_Sesion from DBA_AUDIT_SESSION";

        try {
            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);
            return resultado;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public ResultSet verTablasXSchema(String schema) {

        String query = "SELECT table_name FROM all_tables WHERE owner = '" + schema + "' ORDER BY table_name";

        try {
            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            return resultado;

        } catch (SQLException ex) {
            System.err.println("ERROR SQLException:  " + ex);
            return null;
        }
    }

    public ResultSet verCamposXTabla(String tabla) {

        String query = "SELECT column_name FROM all_tab_columns WHERE table_name = '" + tabla + "'";

        try {
            Statement stmt = null;
            stmt = conector.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            return resultado;

        } catch (SQLException ex) {
            System.err.println("ERROR SQLException:  " + ex);
            return null;
        }
    }

    public boolean crearIndice(String schema, String tabla, String col, String nomIndice) {

        String query = "CREATE INDEX " + nomIndice + " ON " + schema + "." + tabla + "(" + col + ")";

        try {
            Statement stmt = conector.createStatement();
            stmt.execute(query);
            stmt.close();
            conector.setAutoCommit(false);
            conector.commit();
            return true;

        } catch (SQLException ex) {
            System.err.println("ERROR SQLException:  " + ex);
            return false;
        }
    }

}
