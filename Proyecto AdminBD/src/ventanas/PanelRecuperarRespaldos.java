package ventanas;

import controlador.Controlador;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.filechooser.*;

public class PanelRecuperarRespaldos extends javax.swing.JPanel {

    Controlador controlador = null;
    private String usuario = null;
    private String password = null;
    List<String> listDirectorios = new ArrayList<String>();

    public PanelRecuperarRespaldos(Controlador controlador, String usuario, String password) {
        this.controlador = controlador;
        this.usuario = usuario;
        this.password = password;
        initComponents();
        cargarTablasUsuario();
        cargarDirectorios();
    }

    Runnable run = new Runnable() {
        @Override
        public void run() {
            Importar();
        }
    };

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

    //metodo de cargar directorios
    public void cargarTablasUsuario() {
        controlador.getConexion(usuario, password);
        ResultSet resultado = controlador.cargarTablasUsuario(usuario);

        boxTablas.removeAllItems();

        try {

            while (resultado.next()) {
                boxTablas.addItem(resultado.getString("TABLE_NAME"));
            }
            boxTablas.setSelectedIndex(-1);
        } catch (SQLException ex) {
            System.err.println("ERROR: " + ex);
        }
    }

    private void Importar() {

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
            comando = "cmd /c " + "IMPDP" + " " + usuario + "/" + password + " " + tipoRespaldo + " DIRECTORY = " + dir
                    + " DUMPFILE = " + jtextRuta.getText() + " LOGFILE = " + jtextRuta.getText() + ".log";

            // Ejcutamos el comando
            Process p = Runtime.getRuntime().exec(comando);

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(
                    p.getErrorStream()));

            // Leemos la salida del comando
            while ((s = stdInput.readLine()) != null) {
                areaComando.append(s + "\n");
            }

            // Leemos los errores si los hubiera
            while ((s = stdError.readLine()) != null) {
                areaComando.append(s + "\n");
            }

            lblTipoElegido.setText("La Importación ha concluido");
            JOptionPane.showMessageDialog(null, " La importación ha concluido..", "Proceso Completado", JOptionPane.INFORMATION_MESSAGE);

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

        if (jtextRuta.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Seleccione un archivo de respaldo", "", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        radioSchema = new javax.swing.JRadioButton();
        radioTablas = new javax.swing.JRadioButton();
        boxTablas = new javax.swing.JComboBox();
        radioFull = new javax.swing.JRadioButton();
        boxDirectorios = new javax.swing.JComboBox();
        lblDirectorios = new javax.swing.JLabel();
        btnCargarResp = new javax.swing.JButton();
        lblTipoElegido = new javax.swing.JLabel();
        btnCargarArch = new javax.swing.JButton();
        lblRuta = new javax.swing.JLabel();
        jtextRuta = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaComando = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();

        setMinimumSize(new java.awt.Dimension(560, 560));
        setPreferredSize(new java.awt.Dimension(560, 560));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setBackground(new java.awt.Color(0, 0, 0));
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Recuperación de respaldos");
        lblTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 80));

        radioSchema.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        radioSchema.setText("Esquema");
        radioSchema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioSchemaActionPerformed(evt);
            }
        });
        add(radioSchema, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, -1, 20));

        radioTablas.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        radioTablas.setText("Tabla");
        radioTablas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioTablasActionPerformed(evt);
            }
        });
        add(radioTablas, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, -1, 20));

        boxTablas.setBackground(new java.awt.Color(240, 240, 240));
        boxTablas.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        boxTablas.setBorder(null);
        boxTablas.setEnabled(false);
        add(boxTablas, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, 210, -1));

        radioFull.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        radioFull.setText("Full");
        radioFull.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioFullActionPerformed(evt);
            }
        });
        add(radioFull, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, -1, 20));

        boxDirectorios.setBackground(new java.awt.Color(240, 240, 240));
        boxDirectorios.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        boxDirectorios.setBorder(null);
        add(boxDirectorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, 203, -1));

        lblDirectorios.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblDirectorios.setText("Directorios");
        add(lblDirectorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, -1, -1));

        btnCargarResp.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnCargarResp.setText("Cargar Respaldo");
        btnCargarResp.setBorder(null);
        btnCargarResp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarRespActionPerformed(evt);
            }
        });
        add(btnCargarResp, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 340, 120, 30));

        lblTipoElegido.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblTipoElegido.setForeground(new java.awt.Color(51, 102, 0));
        lblTipoElegido.setText("...");
        add(lblTipoElegido, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 350, 440, -1));

        btnCargarArch.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnCargarArch.setText("Cargar Archivo");
        btnCargarArch.setBorder(null);
        btnCargarArch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarArchActionPerformed(evt);
            }
        });
        add(btnCargarArch, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 280, 100, 30));

        lblRuta.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblRuta.setText("Archivo:");
        add(lblRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 70, 24));

        jtextRuta.setEditable(false);
        jtextRuta.setBorder(null);
        add(jtextRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 280, 200, 20));

        areaComando.setEditable(false);
        areaComando.setColumns(1);
        areaComando.setLineWrap(true);
        areaComando.setRows(5);
        areaComando.setWrapStyleWord(true);
        jScrollPane1.setViewportView(areaComando);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 380, 630, 150));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel1.setText("Tipo de Respaldo");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, -1, -1));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel3.setText("Archivo");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, -1, -1));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 610, 20));
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 330, 610, 10));
        add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 610, 10));
    }// </editor-fold>//GEN-END:initComponents

    private void radioSchemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioSchemaActionPerformed
        boxTablas.enable(false);
        radioTablas.setSelected(false);
        radioFull.setSelected(false);
    }//GEN-LAST:event_radioSchemaActionPerformed

    private void radioTablasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioTablasActionPerformed
        boxTablas.enable(true);
        radioSchema.setSelected(false);
        radioFull.setSelected(false);
        boxTablas.setSelectedIndex(0);
    }//GEN-LAST:event_radioTablasActionPerformed

    private void radioFullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioFullActionPerformed
        boxTablas.enable(false);
        radioTablas.setSelected(false);
        radioSchema.setSelected(false);
    }//GEN-LAST:event_radioFullActionPerformed

    private void btnCargarRespActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarRespActionPerformed
        if (validaciones()) {
            String mensaje = "Importando respaldo "; //Variable con el mensaje "Creando respaldo "

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

            btnCargarResp.setEnabled(false);//para bloquear el boton de crear

            Thread h1 = new Thread(run);
            h1.start();
        }
    }//GEN-LAST:event_btnCargarRespActionPerformed

    private void btnCargarArchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarArchActionPerformed
        // TODO add your handling code here:
        JFileChooser explorador = new JFileChooser("home"); //Explorador de carpetas y archivos
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".DMP", "dmp"); //Muestra solo los .DMP
        explorador.setFileFilter(filter); //Aplica el filtro
        explorador.setDialogTitle("Cargar archivo Dump File"); //Título
        int seleccion = explorador.showDialog(null, "Cargar"); //Botón

        switch (seleccion) {
            case JFileChooser.APPROVE_OPTION: //Seleccionó cargar
                break;

            case JFileChooser.CANCEL_OPTION: //Seleccionó cancelar o cerró ventana
                break;

            case JFileChooser.ERROR_OPTION: //Viene aquí si hay error
                break;
        }

        File archivo = explorador.getSelectedFile(); //"archivo" tiene lo seleccionado
        String ruta = archivo.getName(); //"ruta" tiene la ruta del archivo seleccionado
        //System.out.println("La ruta del fichero es: "+ruta);
        jtextRuta.setText(ruta); //La ruta se muestra en el jTextField
        //para abrir
        //int seleccion=explorador.showOpenDialog(parent);

        //para guardar
        //int seleccion = explorador.showSaveDialog(parent);
    }//GEN-LAST:event_btnCargarArchActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaComando;
    private javax.swing.JComboBox boxDirectorios;
    private javax.swing.JComboBox boxTablas;
    private javax.swing.JButton btnCargarArch;
    private javax.swing.JButton btnCargarResp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField jtextRuta;
    private javax.swing.JLabel lblDirectorios;
    private javax.swing.JLabel lblRuta;
    private javax.swing.JLabel lblTipoElegido;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JRadioButton radioFull;
    private javax.swing.JRadioButton radioSchema;
    private javax.swing.JRadioButton radioTablas;
    // End of variables declaration//GEN-END:variables
}
