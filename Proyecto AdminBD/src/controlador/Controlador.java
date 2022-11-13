package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
                user = user + " as sysdba";
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

    /* public boolean conectar(String user, String pass) {
        try {
            if (user.equals("sys") || user == "SYSTEM") {
                user = user + " as sysdba";
            }
            Class.forName(DRIVER);

            conector = DriverManager.getConnection(URL, user, pass);

            if (conector != null) {
                return true;

            } else {
                System.err.println("Conexion fallida");
            }

        } catch (Exception e) {
            System.err.println("Exception:  " + e);
        }
        return false;
    }*/
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

}
