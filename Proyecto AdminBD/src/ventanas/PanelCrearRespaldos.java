package ventanas;

import controlador.Controlador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class PanelCrearRespaldos extends javax.swing.JPanel {
    
    Controlador controlador = null;
    private String usuario = null;
    private String password = null;
    List<String> listDirectorios = new ArrayList();
    
    Runnable run = new Runnable() {
        @Override
        public void run() {
            Exportar();
        }
    };
    
    public PanelCrearRespaldos(Controlador controlador, String usuario, String password) {
        this.controlador = controlador;
        this.usuario = usuario;
        this.password = password;
        initComponents();
        cargarTablasUsuario();
        cargarDirectorios();
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
            System.err.println("ERROR SQLException:  " + ex);
        }
    }
    
    public void cargarTablasUsuario() {
        
        ResultSet resultado = controlador.cargarTablasUsuario(usuario);
        
        boxTablas.removeAllItems();
        
        try {
            while (resultado.next()) {
                boxTablas.addItem(resultado.getString("TABLE_NAME"));
            }
            boxTablas.setSelectedIndex(-1);
            
        } catch (SQLException ex) {
            System.err.println("ERROR SQLException:  " + ex);
        }
    }
    
    private void Exportar() {
        
        String s = null;
        String tipoRespaldo = "";//el tipo de respaldo que se hará
        String dir = "";//directorio donde se guardara el respaldo

        if (radioTablas.isSelected()) {
            tipoRespaldo = "TABLES = " + boxTablas.getSelectedItem().toString();
        }
        if (radioSchema.isSelected()) {
            tipoRespaldo = "SCHEMAS = " + usuario;
        }
        if (radioFull.isSelected()) {
            tipoRespaldo = "FULL=Y";
        }
        
        dir = boxDirectorios.getSelectedItem().toString();
        
        try {
            
            String comando;
            comando = "cmd /c " + "EXPDP" + " " + usuario + "/" + password + " " + tipoRespaldo + " DIRECTORY = " + dir
                    + " DUMPFILE = " + txtNombreArchivo.getText() + ".dmp LOGFILE = " + txtNombreArchivo.getText() + "_log.log";

            //Ejecutamos el comando
            Process p = Runtime.getRuntime().exec(comando);
            
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            
            BufferedReader stdError = new BufferedReader(new InputStreamReader(
                    p.getErrorStream()));

            //Leemos la salida del comando
            while ((s = stdInput.readLine()) != null) {
                areaComando.append(s + "\n");
            }

            //Leemos los errores si los hubiera
            while ((s = stdError.readLine()) != null) {
                areaComando.append(s + "\n");
                //JOptionPane.showMessageDialog(null, s);
            }
            
            lblTipoElegido.setText("El respaldo ha terminado");
            JOptionPane.showMessageDialog(null, " La Exportación ha concluido..", "Proceso Completado", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (IOException e) {
            System.out.println("Excepción: ");
            e.printStackTrace();
            System.exit(-1);
        }
    }
    
    public boolean validaciones() {
        
        if (!radioTablas.isSelected() && !radioSchema.isSelected() && !radioFull.isSelected()) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el tipo de respaldo", "", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (radioTablas.isSelected() && boxTablas.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar la tabla a respaldar", "", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (boxDirectorios.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un directorio", "", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (txtNombreArchivo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe darle un nombre al archivo de respaldo", "", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        txtNombreArchivo = new javax.swing.JTextField();
        lblNombreArchivo = new javax.swing.JLabel();
        radioFull = new javax.swing.JRadioButton();
        lblDirectorios = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        boxDirectorios = new javax.swing.JComboBox();
        btnCrear = new javax.swing.JButton();
        lblDirDirectorio = new javax.swing.JLabel();
        txtDirDirectorio = new javax.swing.JTextField();
        radioTablas = new javax.swing.JRadioButton();
        radioSchema = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        boxTablas = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        lblTipoElegido = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaComando = new javax.swing.JTextArea();

        setMinimumSize(new java.awt.Dimension(560, 560));
        setPreferredSize(new java.awt.Dimension(560, 560));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setBackground(new java.awt.Color(0, 0, 0));
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Creación de respaldos por Schemas - Tablas - Full");
        lblTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 50));

        txtNombreArchivo.setBorder(null);
        add(txtNombreArchivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 280, 200, 20));

        lblNombreArchivo.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblNombreArchivo.setText("Nombre del archivo del respaldo a crear");
        add(lblNombreArchivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, -1, -1));

        radioFull.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        radioFull.setText("Full");
        radioFull.setBorder(null);
        radioFull.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioFullActionPerformed(evt);
            }
        });
        add(radioFull, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, -1));

        lblDirectorios.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblDirectorios.setText("Directorios");
        add(lblDirectorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, -1, -1));

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel4.setText("Archivo");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        boxDirectorios.setBackground(new java.awt.Color(240, 240, 240));
        boxDirectorios.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        boxDirectorios.setBorder(null);
        boxDirectorios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxDirectoriosItemStateChanged(evt);
            }
        });
        add(boxDirectorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 240, 203, -1));

        btnCrear.setBackground(new java.awt.Color(153, 255, 153));
        btnCrear.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnCrear.setText("Crear respaldo");
        btnCrear.setBorder(null);
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        add(btnCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, 200, 40));

        lblDirDirectorio.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblDirDirectorio.setText("Dirección de Directorio");
        add(lblDirDirectorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, -1, -1));

        txtDirDirectorio.setEditable(false);
        txtDirDirectorio.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDirDirectorio.setBorder(null);
        txtDirDirectorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDirDirectorioActionPerformed(evt);
            }
        });
        add(txtDirDirectorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 320, 313, 20));

        radioTablas.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        radioTablas.setText("Tabla");
        radioTablas.setBorder(null);
        radioTablas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioTablasActionPerformed(evt);
            }
        });
        add(radioTablas, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, 70, 20));

        radioSchema.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        radioSchema.setText("Esquema");
        radioSchema.setBorder(null);
        radioSchema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioSchemaActionPerformed(evt);
            }
        });
        add(radioSchema, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, -1, -1));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel3.setText("Tipo de Respaldo");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        boxTablas.setBackground(new java.awt.Color(240, 240, 240));
        boxTablas.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        boxTablas.setBorder(null);
        boxTablas.setEnabled(false);
        add(boxTablas, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, 310, -1));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel1.setText("Resultado");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 80, -1));

        lblTipoElegido.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblTipoElegido.setForeground(new java.awt.Color(51, 102, 0));
        lblTipoElegido.setText("...");
        add(lblTipoElegido, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 390, 560, -1));

        areaComando.setEditable(false);
        areaComando.setColumns(1);
        areaComando.setLineWrap(true);
        areaComando.setRows(5);
        areaComando.setWrapStyleWord(true);
        jScrollPane1.setViewportView(areaComando);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 730, 180));
    }// </editor-fold>//GEN-END:initComponents

    private void radioFullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioFullActionPerformed
        boxTablas.enable(false);
        radioTablas.setSelected(false);
        radioSchema.setSelected(false);
    }//GEN-LAST:event_radioFullActionPerformed

    private void boxDirectoriosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxDirectoriosItemStateChanged
        if (listDirectorios.size() > 0 && boxDirectorios.getSelectedIndex() != -1)
            txtDirDirectorio.setText(listDirectorios.get(boxDirectorios.getSelectedIndex()));
    }//GEN-LAST:event_boxDirectoriosItemStateChanged

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        
        if (validaciones()) {
            String mensaje = "Creando respaldo "; //Variable con el mensaje "Creando respaldo "

            if (radioFull.isSelected()) //Si se selecciona la opción Full
            {
                mensaje = mensaje + "Full..."; //El mensaje será "Creando respaldo Full..."
            } else if (radioSchema.isSelected()) //Si se selecciona la opción Schema
            {
                mensaje = mensaje + "del Schema..."; //El mensaje será "Creando respaldo de Schema..."
            } else if (radioTablas.isSelected()) //Si se selecciona la opción Tablas
            {
                mensaje = mensaje + "de Tabla..."; //El mensaje será "Creando respaldo de Tablas..."
            }
            lblTipoElegido.setText(mensaje); //El label cambia su contenido con el texto

            btnCrear.setEnabled(false);//para bloquear el boton de crear

            Thread h1 = new Thread(run);
            h1.start();
        }
    }//GEN-LAST:event_btnCrearActionPerformed

    private void txtDirDirectorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDirDirectorioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDirDirectorioActionPerformed

    private void radioTablasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioTablasActionPerformed
        boxTablas.enable(true);
        radioSchema.setSelected(false);
        radioFull.setSelected(false);
        cargarTablasUsuario();
        boxTablas.setSelectedIndex(0);
    }//GEN-LAST:event_radioTablasActionPerformed

    private void radioSchemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioSchemaActionPerformed
        boxTablas.enable(false);
        radioTablas.setSelected(false);
        radioFull.setSelected(false);
    }//GEN-LAST:event_radioSchemaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaComando;
    private javax.swing.JComboBox boxDirectorios;
    private javax.swing.JComboBox boxTablas;
    private javax.swing.JButton btnCrear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDirDirectorio;
    private javax.swing.JLabel lblDirectorios;
    private javax.swing.JLabel lblNombreArchivo;
    private javax.swing.JLabel lblTipoElegido;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JRadioButton radioFull;
    private javax.swing.JRadioButton radioSchema;
    private javax.swing.JRadioButton radioTablas;
    private javax.swing.JTextField txtDirDirectorio;
    private javax.swing.JTextField txtNombreArchivo;
    // End of variables declaration//GEN-END:variables
}
