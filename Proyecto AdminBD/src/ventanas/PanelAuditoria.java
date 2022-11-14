package ventanas;

import controlador.Controlador;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PanelAuditoria extends javax.swing.JPanel {

    Controlador controlador = null;
    private String usuario = null;
    private String password = null;

    public PanelAuditoria(Controlador controlador, String usuario, String password) {
        this.controlador = controlador;
        this.usuario = usuario;
        this.password = password;
        initComponents();
        cargarUsuarios();
        cargarTablas();
    }

    public void cargarUsuarios() {

        controlador.getConexion("sys", "root123");
        ResultSet rs = controlador.cargarUsuario();

        if (rs != null) {

            try {
                jComboBox2.removeAllItems();
                while (rs.next()) {
                    jComboBox2.addItem(rs.getString(1));
                }
                jComboBox2.setSelectedIndex(-1);

            } catch (SQLException ex) {
                System.err.println("ERROR SQLException:  " + ex);
            }
        }
    }

    public void cargarTablas() {
        controlador.getConexion("sys", "root123");
        ResultSet rs = controlador.verTablasXSchema(String.valueOf(jComboBox2.getSelectedItem()));

        if (rs != null) {

            try {
                jComboBox1.removeAllItems();
                while (rs.next()) {
                    jComboBox1.addItem(rs.getString(1));
                }
                jComboBox1.setSelectedIndex(-1);

            } catch (SQLException ex) {
                System.err.println("ERROR SQLException:  " + ex);
            }
        }
    }

    public void llenarGrid() {

        ResultSet resultado;
        DefaultTableModel modelo = (DefaultTableModel) this.jTable1.getModel();

        for (int i = 0; i < jTable1.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }

        resultado = controlador.verAuditoriaPorAccion();

        this.jTable1.removeAll();

        try {
            while (resultado.next()) {
                Object[] fila = new Object[6];
                fila[0] = resultado.getString("SESSIONID");
                fila[1] = resultado.getString("USERHOST");
                fila[2] = resultado.getString("USERNAME");
                fila[3] = resultado.getString("ACTION_NAME");
                fila[4] = resultado.getString("OBJ_NAME");
                fila[5] = resultado.getInt("ACTION");
                modelo.addRow(fila);
            }
            jTable1.setModel(modelo);

        } catch (SQLException ex) {
            System.err.println("ERROR SQLException:  " + ex);
        }
    }

    public void llenarGrid2() {

        ResultSet resultado;
        DefaultTableModel modelo = (DefaultTableModel) this.jTable2.getModel();

        for (int i = 0; i < jTable2.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }

        resultado = controlador.visualizarAuditoriaSesiones();

        this.jTable2.removeAll();

        try {
            while (resultado.next()) {
                Object[] fila = new Object[4];
                fila[0] = resultado.getString("USERNAME");
                fila[1] = resultado.getString("TIPO_SUCESO");
                fila[2] = resultado.getString("HORA_INICIO_SESION");
                fila[3] = resultado.getString("HORA_FIN_SESION");
                modelo.addRow(fila);
            }
            jTable2.setModel(modelo);

        } catch (SQLException ex) {
            System.err.println("ERROR SQLException:  " + ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton8 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setMinimumSize(new java.awt.Dimension(560, 560));
        setPreferredSize(new java.awt.Dimension(560, 560));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setBackground(new java.awt.Color(0, 0, 0));
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Auditoria de la Base de Datos");
        lblTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 60));

        jButton3.setForeground(java.awt.Color.blue);
        jButton3.setText("Auditar Conexiones");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jButton4.setForeground(java.awt.Color.blue);
        jButton4.setText("Auditar inicios de sesión fallidos");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 70, -1, -1));

        jButton5.setForeground(java.awt.Color.blue);
        jButton5.setText("Auditar inicios de sesión");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, -1, -1));

        jButton6.setForeground(java.awt.Color.blue);
        jButton6.setText("Auditar inicios de sesión exitosos");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, -1, -1));

        jButton7.setForeground(java.awt.Color.blue);
        jButton7.setText("Visualizar auditoria de las tablas");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Session Id", "Userhost", "Username", "ActionName", "Tabla", "Action"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 500, 200));

        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        jLabel2.setText("Auditoria de tablas");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 140, 30));

        jLabel3.setBackground(java.awt.Color.black);
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Schema:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jLabel1.setBackground(java.awt.Color.black);
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Tabla:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 140, 30));

        jButton8.setForeground(java.awt.Color.blue);
        jButton8.setText("Activar auditoria de la tabla");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, -1));

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setForeground(java.awt.Color.blue);
        jButton1.setText("Visualizar auditoria de sesiones");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 230, 40));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Username", "Tipo Suceso", "Hora Inicio Sesion", "Hora Fin Sesion"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 350, 500, 240));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (controlador.auditarConexiones()) {
            JOptionPane.showMessageDialog(null, "Se ha activado la auditoria para las conexiones", "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se logro activar la auditoria para las conexiones", "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (controlador.auditarIniciosSesionNoExitosos()) {
            JOptionPane.showMessageDialog(null, "Se ha activado la auditoria para los inicios de sesion no exitosos", "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se logro activar la auditoria para los inicios de sesion no exitosos", "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (controlador.auditarIniciosSesion()) {
            JOptionPane.showMessageDialog(null, "Se ha activado la auditoria para los inicios de sesion", "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se logro activar la auditoria para los inicios de sesion", "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (controlador.auditarIniciosSesionExitosos()) {
            JOptionPane.showMessageDialog(null, "Se ha activado la auditoria para los inicios de sesion exitosos", "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se logro activar la auditoria para los inicios de sesion exitosos", "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        llenarGrid();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        cargarTablas();
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if (controlador.auditarTabla(String.valueOf(jComboBox2.getSelectedItem()), String.valueOf(jComboBox1.getSelectedItem()))) {
            JOptionPane.showMessageDialog(null, "Se ha activado la auditoria para la tabla " + String.valueOf(jComboBox1.getSelectedItem())
                    + " del schema " + String.valueOf(jComboBox2.getSelectedItem()), "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se logro activar la auditoria para la tabla " + String.valueOf(jComboBox1.getSelectedItem())
                    + " del schema " + String.valueOf(jComboBox2.getSelectedItem()), "", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        llenarGrid2();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
