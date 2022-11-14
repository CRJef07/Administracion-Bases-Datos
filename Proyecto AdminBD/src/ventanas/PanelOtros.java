package ventanas;

import controlador.Controlador;
import java.sql.*;

public class PanelOtros extends javax.swing.JPanel {

    Controlador controlador = null;
    private String usuario = null;
    private String password = null;

    public PanelOtros(Controlador controlador, String usuario, String password) {
        this.controlador = controlador;
        this.usuario = usuario;
        this.password = password;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        cbParametro = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaComando = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(560, 560));
        setPreferredSize(new java.awt.Dimension(560, 560));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setBackground(new java.awt.Color(0, 0, 0));
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Administración de archivos de respaldos y directorios");
        lblTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 80));

        cbParametro.setBackground(new java.awt.Color(240, 240, 240));
        cbParametro.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        cbParametro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Información de la instancia", "Nombre de la base de datos", "Parámetros de la base de datos", "Productos Oracle instalados y versiones", "IP del servidor de la base de datos", "Ubicación y nombre del fichero SPFILE", "Ubicación y nombre de los ficheros de control", "Todos los ficheros de datos y su ubicación", "Ficheros temporales", "Ficheros de Redo Log", "Espacio de los Tablespaces", "Tamaño ocupado por la base de datos", "Tamaño de ficheros de la base de datos", "Ocupación de todos los objetos", "Propietarios de objetos y número de objetos", "Informacion tablespaces" }));
        cbParametro.setBorder(null);
        add(cbParametro, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 360, 30));

        areaComando.setEditable(false);
        areaComando.setColumns(10);
        areaComando.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        areaComando.setRows(5);
        areaComando.setWrapStyleWord(true);
        jScrollPane1.setViewportView(areaComando);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 680, 290));

        jLabel2.setBackground(java.awt.Color.lightGray);
        jLabel2.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Se requiere el usuario System");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 780, 20));

        jButton1.setBackground(java.awt.Color.darkGray);
        jButton1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jButton1.setForeground(java.awt.Color.white);
        jButton1.setText("Mostrar parametros");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, 180, 30));

        jLabel1.setBackground(java.awt.Color.lightGray);
        jLabel1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel1.setText("Seleccione una opción:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 150, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        //areaComando.append( String.format( "%s\n", resultado ));
        //areaComando.setText(resultado.getString);
        //db.conectar(usuario + " as sysdba", contrasena);
        String s = null;

        //"Información de la instancia"
        if (cbParametro.getSelectedItem().toString().equals("Información de la instancia")) {

            areaComando.setText("");
            ResultSet resultado = controlador.infoInstancia();
            StringBuffer results = new StringBuffer();

            try {

                ResultSetMetaData metaData = resultado.getMetaData();
                int numberOfColumns = metaData.getColumnCount();

                for (int i = 1; i <= numberOfColumns; i++) {
                    results.append(metaData.getColumnName(i) + "\t");
                    results.append("-----------");
                }

                results.append("\n");

                while (resultado.next()) {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        results.append(resultado.getObject(i) + "\t\t");
                    }
                    //results += "\n";
                    results.append("\n");
                }
                areaComando.append(results.toString());

            } catch (SQLException ex) {
                System.err.println("ERROR: " + ex);
            }

            //Nombre de la base de datos
            //Nombre de la base de datos
        } else if (cbParametro.getSelectedItem().toString().equals("Nombre de la base de datos")) {

            areaComando.setText("");
            ResultSet resultado = controlador.nombreDB();
            StringBuffer results = new StringBuffer();

            try {

                ResultSetMetaData metaData = resultado.getMetaData();
                int numberOfColumns = metaData.getColumnCount();

                for (int i = 1; i <= numberOfColumns; i++) {
                    results.append(metaData.getColumnName(i) + "\t");

                }

                results.append("\n");

                while (resultado.next()) {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        results.append(resultado.getObject(i) + "\t");
                    }
                    //results += "\n";
                    results.append("\n");
                }
                areaComando.append(results.toString());

            } catch (SQLException ex) {
                System.err.println("ERROR: " + ex);
            }

            //Parámetros de la base de datos
            //Parámetros de la base de datos
        } else if (cbParametro.getSelectedItem().toString().equals("Parámetros de la base de datos")) {

            areaComando.setText("");
            ResultSet resultado = controlador.parametrosDB();
            StringBuffer results = new StringBuffer();

            try {

                ResultSetMetaData metaData = resultado.getMetaData();
                int numberOfColumns = metaData.getColumnCount();

                for (int i = 1; i <= numberOfColumns; i++) {
                    results.append(metaData.getColumnName(i) + "\t");

                }

                results.append("\n");

                while (resultado.next()) {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        results.append(resultado.getObject(i) + "\t");
                    }
                    //results += "\n";
                    results.append("\n");
                }
                areaComando.append(results.toString());

            } catch (SQLException ex) {
                System.err.println("ERROR: " + ex);
            }

            //Productos Oracle instalados y versiones
            //Productos Oracle instalados y versiones
        } else if (cbParametro.getSelectedItem().toString().equals("Productos Oracle instalados y versiones")) {

            areaComando.setText("");
            ResultSet resultado = controlador.prodOracle();
            StringBuffer results = new StringBuffer();

            try {

                ResultSetMetaData metaData = resultado.getMetaData();
                int numberOfColumns = metaData.getColumnCount();

                for (int i = 1; i <= numberOfColumns; i++) {
                    results.append(metaData.getColumnName(i) + "\t\t\t\t\t");

                }

                results.append("\n");

                while (resultado.next()) {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        results.append(resultado.getObject(i) + "\t\t\t");
                    }
                    //results += "\n";
                    results.append("\n");
                }
                areaComando.append(results.toString());

            } catch (SQLException ex) {
                System.err.println("ERROR: " + ex);
            }

            //IP del servidor de la base de datos
            //IP del servidor de la base de datos
        } else if (cbParametro.getSelectedItem().toString().equals("IP del servidor de la base de datos")) {

            areaComando.setText("");
            ResultSet resultado = controlador.ipServer();
            StringBuffer results = new StringBuffer();

            try {

                ResultSetMetaData metaData = resultado.getMetaData();
                int numberOfColumns = metaData.getColumnCount();

                for (int i = 1; i <= numberOfColumns; i++) {
                    results.append(metaData.getColumnName(i) + "\t\t\t");

                }

                results.append("\n");

                while (resultado.next()) {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        results.append(resultado.getObject(i) + "\t");
                    }
                    //results += "\n";
                    results.append("\n");
                }
                areaComando.append(results.toString());

            } catch (SQLException ex) {
                System.err.println("ERROR: " + ex);
            }

            //Ubicación y nombre del fichero SPFILE
            //Ubicación y nombre del fichero SPFILE
        } else if (cbParametro.getSelectedItem().toString().equals("Ubicación y nombre del fichero SPFILE")) {

            areaComando.setText("");
            ResultSet resultado = controlador.SPFILEfile();
            StringBuffer results = new StringBuffer();

            try {

                ResultSetMetaData metaData = resultado.getMetaData();
                int numberOfColumns = metaData.getColumnCount();

                for (int i = 1; i <= numberOfColumns; i++) {
                    results.append(metaData.getColumnName(i) + "\t\t\t");

                }

                results.append("\n");

                while (resultado.next()) {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        results.append(resultado.getObject(i) + "\t");
                    }
                    //results += "\n";
                    results.append("\n");
                }
                areaComando.append(results.toString());

            } catch (SQLException ex) {
                System.err.println("ERROR: " + ex);
            }

            //Ubicación y nombre de los ficheros de control
            //Ubicación y nombre de los ficheros de control
        } else if (cbParametro.getSelectedItem().toString().equals("Ubicación y nombre de los ficheros de control")) {

            areaComando.setText("");
            ResultSet resultado = controlador.controlfiles();
            StringBuffer results = new StringBuffer();

            try {

                ResultSetMetaData metaData = resultado.getMetaData();
                int numberOfColumns = metaData.getColumnCount();

                for (int i = 1; i <= numberOfColumns; i++) {
                    results.append(metaData.getColumnName(i) + "\t\t\t");

                }

                results.append("\n");

                while (resultado.next()) {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        results.append(resultado.getObject(i) + "\t");
                    }
                    //results += "\n";
                    results.append("\n");
                }
                areaComando.append(results.toString());

            } catch (SQLException ex) {
                System.err.println("ERROR: " + ex);
            }

            //Todos los ficheros de datos y su ubicación
            //Todos los ficheros de datos y su ubicación
        } else if (cbParametro.getSelectedItem().toString().equals("Todos los ficheros de datos y su ubicación")) {

            areaComando.setText("");
            ResultSet resultado = controlador.allfiles();
            StringBuffer results = new StringBuffer();

            try {

                ResultSetMetaData metaData = resultado.getMetaData();
                int numberOfColumns = metaData.getColumnCount();

                for (int i = 1; i <= numberOfColumns; i++) {
                    results.append(metaData.getColumnName(i) + "\t\t\t");

                }

                results.append("\n");

                while (resultado.next()) {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        results.append(resultado.getObject(i) + "\t");
                    }
                    //results += "\n";
                    results.append("\n");
                }
                areaComando.append(results.toString());

            } catch (SQLException ex) {
                System.err.println("ERROR: " + ex);
            }

            //Ficheros temporales
            //Ficheros temporales
        } else if (cbParametro.getSelectedItem().toString().equals("Ficheros temporales")) {

            areaComando.setText("");
            ResultSet resultado = controlador.tempfiles();
            StringBuffer results = new StringBuffer();

            try {

                ResultSetMetaData metaData = resultado.getMetaData();
                int numberOfColumns = metaData.getColumnCount();

                for (int i = 1; i <= numberOfColumns; i++) {
                    results.append(metaData.getColumnName(i) + "\t\t\t");

                }

                results.append("\n");

                while (resultado.next()) {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        results.append(resultado.getObject(i) + "\t");
                    }
                    //results += "\n";
                    results.append("\n");
                }
                areaComando.append(results.toString());

            } catch (SQLException ex) {
                System.err.println("ERROR: " + ex);
            }

            //Ficheros de RedoLog
            //Ficheros de RedoLog
        } else if (cbParametro.getSelectedItem().toString().equals("Ficheros de Redo Log")) {

            areaComando.setText("");
            ResultSet resultado = controlador.redoLogfiles();
            StringBuffer results = new StringBuffer();

            try {

                ResultSetMetaData metaData = resultado.getMetaData();
                int numberOfColumns = metaData.getColumnCount();

                for (int i = 1; i <= numberOfColumns; i++) {
                    results.append(metaData.getColumnName(i) + "\t\t\t");

                }

                results.append("\n");

                while (resultado.next()) {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        results.append(resultado.getObject(i) + "\t");
                    }
                    //results += "\n";
                    results.append("\n");
                }
                areaComando.append(results.toString());

            } catch (SQLException ex) {
                System.err.println("ERROR: " + ex);
            }

            //Espacio de los Tablespaces
            //Espacio de los Tablespaces
        } else if (cbParametro.getSelectedItem().toString().equals("Espacio de los Tablespaces")) {

            areaComando.setText("");
            ResultSet resultado = controlador.tamTablespaces();
            StringBuffer results = new StringBuffer();

            try {

                ResultSetMetaData metaData = resultado.getMetaData();
                int numberOfColumns = metaData.getColumnCount();

                for (int i = 1; i <= numberOfColumns; i++) {
                    results.append(metaData.getColumnName(i) + "\t\t\t\t");

                }

                results.append("\n");

                while (resultado.next()) {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        results.append(resultado.getObject(i) + "\t\t\t");
                    }
                    //results += "\n";
                    results.append("\n");
                }
                areaComando.append(results.toString());

            } catch (SQLException ex) {
                System.err.println("ERROR: " + ex);
            }

            //Tamaño ocupado por la base de datos
            //Tamaño ocupado por la base de datos
        } else if (cbParametro.getSelectedItem().toString().equals("Tamaño ocupado por la base de datos")) {

            areaComando.setText("");
            ResultSet resultado = controlador.tamBD();
            StringBuffer results = new StringBuffer();

            try {

                ResultSetMetaData metaData = resultado.getMetaData();
                int numberOfColumns = metaData.getColumnCount();

                for (int i = 1; i <= numberOfColumns; i++) {
                    results.append(metaData.getColumnName(i) + "\t\t\t\t");

                }

                results.append("\n");

                while (resultado.next()) {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        results.append(resultado.getObject(i) + "\t\t\t");
                    }
                    //results += "\n";
                    results.append("\n");
                }
                areaComando.append(results.toString());

            } catch (SQLException ex) {
                System.err.println("ERROR: " + ex);
            }

            //Tamaño de ficheros de la base de datos
            //Tamaño de ficheros de la base de datos
        } else if (cbParametro.getSelectedItem().toString().equals("Tamaño de ficheros de la base de datos")) {

            areaComando.setText("");
            ResultSet resultado = controlador.tamFilesBD();
            StringBuffer results = new StringBuffer();

            try {

                ResultSetMetaData metaData = resultado.getMetaData();
                int numberOfColumns = metaData.getColumnCount();

                for (int i = 1; i <= numberOfColumns; i++) {
                    results.append(metaData.getColumnName(i) + "\t\t\t\t");

                }

                results.append("\n");

                while (resultado.next()) {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        results.append(resultado.getObject(i) + "\t\t\t");
                    }
                    //results += "\n";
                    results.append("\n");
                }
                areaComando.append(results.toString());

            } catch (SQLException ex) {
                System.err.println("ERROR: " + ex);
            }

            //Ocupación de todos los objetos
            //Ocupación de todos los objetos
        } else if (cbParametro.getSelectedItem().toString().equals("Ocupación de todos los objetos")) {

            areaComando.setText("");
            ResultSet resultado = controlador.tamObjs();
            StringBuffer results = new StringBuffer();

            try {

                ResultSetMetaData metaData = resultado.getMetaData();
                int numberOfColumns = metaData.getColumnCount();

                for (int i = 1; i <= numberOfColumns; i++) {
                    results.append(metaData.getColumnName(i) + "\t\t\t\t");

                }

                results.append("\n");

                while (resultado.next()) {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        results.append(resultado.getObject(i) + "\t\t\t");
                    }
                    //results += "\n";
                    results.append("\n");
                }
                areaComando.append(results.toString());

            } catch (SQLException ex) {
                System.err.println("ERROR: " + ex);
            }

            //Propietarios de objetos y número de objetos
            //Propietarios de objetos y número de objetos
        } else if (cbParametro.getSelectedItem().toString().equals("Propietarios de objetos y número de objetos")) {

            areaComando.setText("");
            ResultSet resultado = controlador.tamObjs();
            StringBuffer results = new StringBuffer();

            try {

                ResultSetMetaData metaData = resultado.getMetaData();
                int numberOfColumns = metaData.getColumnCount();

                for (int i = 1; i <= numberOfColumns; i++) {
                    results.append(metaData.getColumnName(i) + "\t\t\t\t");

                }

                results.append("\n");

                while (resultado.next()) {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        results.append(resultado.getObject(i) + "\t\t\t");
                    }
                    //results += "\n";
                    results.append("\n");
                }
                areaComando.append(results.toString());

            } catch (SQLException ex) {
                System.err.println("ERROR: " + ex);
            }

            //Información Tablespaces
            //Información Tablespaces
        } else if (cbParametro.getSelectedItem().toString().equals("Informacion tablespaces")) {

            areaComando.setText("");
            ResultSet resultado = controlador.infoTablespaces();
            StringBuffer results = new StringBuffer();

            try {

                ResultSetMetaData metaData = resultado.getMetaData();
                int numberOfColumns = metaData.getColumnCount();

                for (int i = 1; i <= numberOfColumns; i++) {
                    results.append(metaData.getColumnName(i) + "\t\t\t");

                }

                results.append("\n");

                while (resultado.next()) {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        results.append(resultado.getObject(i) + "\t\t\t");
                    }
                    //results += "\n";
                    results.append("\n");
                }
                areaComando.append(results.toString());

            } catch (SQLException ex) {
                System.err.println("ERROR: " + ex);
            }

        }

    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaComando;
    private javax.swing.JComboBox<String> cbParametro;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
