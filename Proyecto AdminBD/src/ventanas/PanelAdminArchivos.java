package ventanas;

import controlador.Controlador;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PanelAdminArchivos extends javax.swing.JPanel {

    Controlador controlador = null;
    private String usuario = null;
    private String password = null;
    List<String> listDirectorios = new ArrayList<String>();

    public PanelAdminArchivos(Controlador controlador, String usuario, String password) {
        this.controlador = controlador;
        this.usuario = usuario;
        this.password = password;
        initComponents();
        cargarDirectorios();
        cargarUsuarios();
    }

    public void cargarDirectorios() {

        ResultSet resultado = controlador.cargarDirectorios();

        boxDirectorios.removeAllItems();
        listDirectorios.clear();

        try {

            while (resultado.next()) {
                boxDirectorios.addItem(resultado.getString(1));
                listDirectorios.add(resultado.getString(2));
            }
            boxDirectorios.setSelectedIndex(-1);
        } catch (SQLException ex) {
            System.err.println("ERROR: " + ex);
        }
    }

    //Carga los usuarios en la interfaz
    public void cargarUsuarios() {

        controlador.getConexion("sys", "root123");

        ResultSet resultado = controlador.cargarUsuario();

        if (resultado != null) {

            try {
                comboUsuarios.removeAllItems();
                while (resultado.next()) {
                    comboUsuarios.addItem(resultado.getString(1));
                }
                comboUsuarios.setSelectedIndex(-1);
                controlador.cerrar();

            } catch (SQLException ex) {
                System.err.println("ERROR: " + ex);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblListaRespaldos = new javax.swing.JLabel();
        lblListaRespaldos1 = new javax.swing.JLabel();
        comboUsuarios = new javax.swing.JComboBox<>();
        boxDirectorios = new javax.swing.JComboBox<>();
        btnCrearDirectorio = new javax.swing.JButton();
        btnBorrarDir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JtxtArea = new javax.swing.JTextArea();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        lblDirectorio = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(560, 560));
        setPreferredSize(new java.awt.Dimension(560, 560));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setBackground(new java.awt.Color(0, 0, 0));
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Administración de Archivos de Respaldos y Directorios");
        lblTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 70));

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 102, 0));
        jLabel7.setText("Primero se le pedira la ubicacion y el nombre de la carpeta.");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 470, 340, 20));

        lblListaRespaldos.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblListaRespaldos.setForeground(new java.awt.Color(204, 0, 0));
        lblListaRespaldos.setText("Advertencia: el directorio sera borrado de forma permanente.");
        lblListaRespaldos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        add(lblListaRespaldos, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, 370, 20));

        lblListaRespaldos1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblListaRespaldos1.setText("Respaldos que hay en el directorio del usuario actual ");
        add(lblListaRespaldos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 322, -1));

        comboUsuarios.setBackground(new java.awt.Color(240, 240, 240));
        comboUsuarios.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        add(comboUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 420, 230, 20));

        boxDirectorios.setBackground(new java.awt.Color(240, 240, 240));
        boxDirectorios.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        boxDirectorios.setBorder(null);
        boxDirectorios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxDirectoriosItemStateChanged(evt);
            }
        });
        add(boxDirectorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 230, 20));

        btnCrearDirectorio.setBackground(new java.awt.Color(153, 255, 153));
        btnCrearDirectorio.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnCrearDirectorio.setText("Crear Directorio");
        btnCrearDirectorio.setBorder(null);
        btnCrearDirectorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearDirectorioActionPerformed(evt);
            }
        });
        add(btnCrearDirectorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 420, 130, 30));

        btnBorrarDir.setBackground(new java.awt.Color(255, 102, 102));
        btnBorrarDir.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnBorrarDir.setForeground(java.awt.Color.white);
        btnBorrarDir.setText("Borrar Directorio");
        btnBorrarDir.setBorder(null);
        btnBorrarDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarDirActionPerformed(evt);
            }
        });
        add(btnBorrarDir, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 130, 30));

        JtxtArea.setEditable(false);
        JtxtArea.setColumns(20);
        JtxtArea.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        JtxtArea.setRows(5);
        jScrollPane1.setViewportView(JtxtArea);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, 360, 160));

        jSeparator2.setBackground(java.awt.Color.black);
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 360, 620, 10));

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 102, 0));
        jLabel6.setText("Luego se le pedira el nombre logico del directorio (el que se vera en Oracle).");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 490, 430, 20));

        lblDirectorio.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblDirectorio.setText("Seleccione el usuario al que le creará un nuevo directorio");
        add(lblDirectorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 370, -1, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void boxDirectoriosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxDirectoriosItemStateChanged
        if (listDirectorios.size() > 0 && boxDirectorios.getSelectedIndex() != -1) {
            JtxtArea.setText("");//para limpiar el jtext cada vez que se cambia de opcion
            String sDirectorio = listDirectorios.get(boxDirectorios.getSelectedIndex()); //Directorio de elemento seleccionado
            File f = new File(sDirectorio);

            if (f.exists()) {
                File[] ficheros = f.listFiles();
                for (int x = 0; x < ficheros.length; x++) {
                    JtxtArea.append(ficheros[x].getName() + "\n");
                }
            }
        }

    }//GEN-LAST:event_boxDirectoriosItemStateChanged

    private void btnCrearDirectorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearDirectorioActionPerformed

        if (comboUsuarios.getSelectedIndex() != -1) {
            JFileChooser dirCarpeta = new JFileChooser();
            dirCarpeta.showSaveDialog(null);

            File crearCarpeta = dirCarpeta.getSelectedFile();

            if (!crearCarpeta.exists()) {
                crearCarpeta.mkdir();

                String dirNuevo = JOptionPane.showInputDialog(null, "Ingrese el nombre del nuevo directorio", "Nuevo Directorio", JOptionPane.OK_CANCEL_OPTION);
                if (dirNuevo != null) {
                    if (controlador.crearDirectorio(dirNuevo, dirCarpeta.getSelectedFile().getPath(), (String) comboUsuarios.getSelectedItem())) {
                        JOptionPane.showMessageDialog(null, "El directorio fue creado con exito", "", JOptionPane.INFORMATION_MESSAGE);
                    }

                    cargarDirectorios();
                    JtxtArea.setText("");
                }

            } else {
                if (crearCarpeta.exists()) {
                    JOptionPane.showMessageDialog(null, "El directorio ya existe");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario", "", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnCrearDirectorioActionPerformed

    private void btnBorrarDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarDirActionPerformed
        controlador.getConexion(usuario, password);
        if (boxDirectorios.getSelectedIndex() != -1) {
            if (controlador.EliminarDirectorio((String) boxDirectorios.getSelectedItem())) {
                JOptionPane.showMessageDialog(null, "El directorio fue eliminado con exito", "", JOptionPane.INFORMATION_MESSAGE);
            }

            cargarDirectorios();
            JtxtArea.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un directorio para borrar", "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBorrarDirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea JtxtArea;
    private javax.swing.JComboBox<String> boxDirectorios;
    private javax.swing.JButton btnBorrarDir;
    private javax.swing.JButton btnCrearDirectorio;
    private javax.swing.JComboBox<String> comboUsuarios;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblDirectorio;
    private javax.swing.JLabel lblListaRespaldos;
    private javax.swing.JLabel lblListaRespaldos1;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
