package ventanas;

import controlador.Controlador;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FilenameUtils;

public class PanelTablespaces extends javax.swing.JPanel {

    Controlador controlador = null;
    private String usuario = null;
    private String password = null;

    public PanelTablespaces(Controlador controlador, String usuario, String password) {
        this.controlador = controlador;
        this.usuario = usuario;
        this.password = password;
        initComponents();
    }

    public void crearTablespace() {

        JFileChooser fileChooser = new JFileChooser("C:/OracleXE/app/oracle/oradata/XE");//Abre una ventana para guardar el archivo
        fileChooser.setFileFilter(new FileNameExtensionFilter("Database File", "dbf"));//Filtra la extensión .dbf

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {

            File file = fileChooser.getSelectedFile();
            String nombreTablespace;
            String tamanoTablespace;

            if (FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("dbf")) {
            } else {
                file = new File(file.toString() + ".dbf");//Añade extensión .dbf
                file = new File(file.getParentFile(), FilenameUtils.getBaseName(file.getName()) + ".dbf");//Reemplaza la extensión con .dbf
                nombreTablespace = FilenameUtils.getBaseName(file.getName());
                //nombreTablespace = NombreTablespace.getText();
                tamanoTablespace = this.jSpinner2.getValue().toString();

                try {
                    Connection conexionSys = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", usuario, password);//Hace conexión con SYSDBA
                    Statement sys = conexionSys.createStatement();
                    sys.executeQuery("CREATE TABLESPACE " + nombreTablespace + " DATAFILE '" + file.getPath() + "' SIZE " + tamanoTablespace + "M ONLINE");
                    conexionSys.close();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    public void crearTablespaceTemporal() {

        JFileChooser fileChooser = new JFileChooser("C:/OracleXE/app/oracle/oradata/XE");//Abre una ventana para guardar el archivo
        fileChooser.setFileFilter(new FileNameExtensionFilter("Database File", "dbf"));//Filtra la extensión .dbf

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {

            File file = fileChooser.getSelectedFile();
            String nombreTablespaceTemporal;
            String tamanoTablespaceTemporal;

            if (FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("dbf")) {
            } else {
                file = new File(file.toString() + ".dbf");//Añade extensión .dbf
                file = new File(file.getParentFile(), FilenameUtils.getBaseName(file.getName()) + ".dbf");//Reemplaza la extensión con .dbf
                nombreTablespaceTemporal = FilenameUtils.getBaseName(file.getName());
                //nombreTablespaceTemporal = NombreTablespaceTemp.getText();
                tamanoTablespaceTemporal = this.jSpinner1.getValue().toString();

                try {
                    Connection conexionSys = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", usuario, password);//Hace conexión con SYSDBA
                    Statement sys = conexionSys.createStatement();
                    sys.executeQuery("CREATE TEMPORARY TABLESPACE " + nombreTablespaceTemporal + " TEMPFILE '" + file.getPath() + "' SIZE " + tamanoTablespaceTemporal + "M AUTOEXTEND ON");
                    conexionSys.close();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    public void cambiarTamanoDatafile() {

        JFileChooser fileChooser = new JFileChooser("C:/OracleXE/app/oracle/oradata/XE");//Abre una ventana para guardar el archivo
        fileChooser.setFileFilter(new FileNameExtensionFilter("Database File", "dbf"));//Filtra la extensión .dbf

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

            File file = fileChooser.getSelectedFile();
            String tamanoTablespace = this.jSpinner3.getValue().toString();

            try {
                Connection conexionSys = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", usuario, password);//Hace conexión con SYSDBA
                Statement sys = conexionSys.createStatement();
                sys.executeQuery("ALTER DATABASE DATAFILE '" + file.getPath() + "' RESIZE " + tamanoTablespace + "M");
                conexionSys.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        btnCrearTablespace = new javax.swing.JButton();
        jSpinner2 = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        btnCrearTablespaceTemp = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jSpinner3 = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        btnSelectDBF = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setMinimumSize(new java.awt.Dimension(560, 560));
        setPreferredSize(new java.awt.Dimension(560, 560));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setBackground(new java.awt.Color(0, 0, 0));
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Administración de tablespaces");
        lblTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 90));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cambiar Tamaño de Datafile");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 780, -1));

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Seleccione Tamaño");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 460, 270, 30));

        jSpinner1.setBorder(null);
        add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, 100, 30));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel3.setText("MB");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, 40, 30));

        btnCrearTablespace.setBackground(new java.awt.Color(153, 255, 153));
        btnCrearTablespace.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnCrearTablespace.setText("Crear Tablespace");
        btnCrearTablespace.setBorder(null);
        btnCrearTablespace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearTablespaceActionPerformed(evt);
            }
        });
        add(btnCrearTablespace, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 160, 170, 30));

        jSpinner2.setToolTipText("50");
        jSpinner2.setBorder(null);
        add(jSpinner2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, 100, 30));

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel6.setText("MB");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 300, 40, 30));

        btnCrearTablespaceTemp.setBackground(new java.awt.Color(153, 255, 153));
        btnCrearTablespaceTemp.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnCrearTablespaceTemp.setText("Crear Tablespace Temporal");
        btnCrearTablespaceTemp.setBorder(null);
        btnCrearTablespaceTemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearTablespaceTempActionPerformed(evt);
            }
        });
        add(btnCrearTablespaceTemp, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 300, 170, 30));

        jLabel8.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Tamaño del Tablespace Temporal");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 270, 30));

        jSpinner3.setBorder(null);
        add(jSpinner3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 460, 100, 30));

        jLabel9.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel9.setText("MB");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 460, 40, 30));

        btnSelectDBF.setBackground(new java.awt.Color(153, 255, 153));
        btnSelectDBF.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnSelectDBF.setText("Seleccione Datafile");
        btnSelectDBF.setBorder(null);
        btnSelectDBF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectDBFActionPerformed(evt);
            }
        });
        add(btnSelectDBF, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 460, 180, 30));

        jLabel10.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Tamaño del Tablespace");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 260, 30));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 600, 10));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 600, 10));
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 600, 10));
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearTablespaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearTablespaceActionPerformed
        crearTablespace();
    }//GEN-LAST:event_btnCrearTablespaceActionPerformed

    private void btnCrearTablespaceTempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearTablespaceTempActionPerformed
        crearTablespaceTemporal();
    }//GEN-LAST:event_btnCrearTablespaceTempActionPerformed

    private void btnSelectDBFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectDBFActionPerformed
        cambiarTamanoDatafile();
    }//GEN-LAST:event_btnSelectDBFActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrearTablespace;
    private javax.swing.JButton btnCrearTablespaceTemp;
    private javax.swing.JButton btnSelectDBF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    public javax.swing.JSpinner jSpinner1;
    public javax.swing.JSpinner jSpinner2;
    public javax.swing.JSpinner jSpinner3;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
