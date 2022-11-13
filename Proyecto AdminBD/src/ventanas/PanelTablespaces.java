package ventanas;

import controlador.Controlador;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

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
                    Connection conexionSys = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", usuario, contrasena);//Hace conexión con SYSDBA
                    Statement sys = conexionSys.createStatement();
                    sys.executeQuery("CREATE TABLESPACE " + nombreTablespace + " DATAFILE '" + file.getPath() + "' SIZE " + tamanoTablespace + "M ONLINE");
                    conexionSys.close();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
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
        jLabel4 = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        btnCrearTablespaceTemp = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSpinner3 = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        btnSelectDBF = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(560, 560));
        setPreferredSize(new java.awt.Dimension(560, 560));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setBackground(new java.awt.Color(0, 0, 0));
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Tablespaces");
        add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 40));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 102));
        jLabel1.setText("Cambiar Tamaño de Datafile");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 400, 200, -1));

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ingrese el nuevo tamaño:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 460, 180, -1));

        jSpinner1.setBorder(null);
        add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 310, 100, 30));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("MB");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 190, 30, 20));

        btnCrearTablespace.setBackground(new java.awt.Color(153, 255, 153));
        btnCrearTablespace.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnCrearTablespace.setText("Crear");
        btnCrearTablespace.setBorder(null);
        btnCrearTablespace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearTablespaceActionPerformed(evt);
            }
        });
        add(btnCrearTablespace, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 180, 70, 30));

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 102));
        jLabel4.setText("Crear Tablespace");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 140, -1));

        jSpinner2.setToolTipText("50");
        jSpinner2.setBorder(null);
        add(jSpinner2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, 100, 30));

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("MB");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 320, 30, 20));

        btnCrearTablespaceTemp.setBackground(new java.awt.Color(153, 255, 153));
        btnCrearTablespaceTemp.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnCrearTablespaceTemp.setText("Crear");
        btnCrearTablespaceTemp.setBorder(null);
        btnCrearTablespaceTemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearTablespaceTempActionPerformed(evt);
            }
        });
        add(btnCrearTablespaceTemp, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 310, 70, 30));

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 102));
        jLabel7.setText("Crear Tablespace Temporal");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 210, -1));

        jLabel8.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Ingrese el tamaño del tablespace:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, 210, -1));

        jSpinner3.setBorder(null);
        add(jSpinner3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 450, 100, 30));

        jLabel9.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("MB");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 460, 30, 20));

        btnSelectDBF.setBackground(new java.awt.Color(153, 255, 153));
        btnSelectDBF.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnSelectDBF.setText("Seleccione Datafile");
        btnSelectDBF.setBorder(null);
        btnSelectDBF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectDBFActionPerformed(evt);
            }
        });
        add(btnSelectDBF, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 450, 130, 30));

        jLabel10.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Ingrese el tamaño del tablespace:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 200, -1));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 600, 10));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, 600, 10));
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 600, 10));

        jLabel5.setBackground(java.awt.Color.lightGray);
        jLabel5.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 102, 0));
        jLabel5.setText("Advertencia: elija un tamaño superior al que que ya ocupan los datos almacenados o estos seran borrados");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 520, 610, 20));
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
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
