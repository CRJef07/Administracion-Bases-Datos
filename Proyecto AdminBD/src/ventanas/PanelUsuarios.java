package ventanas;

import controlador.Controlador;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class PanelUsuarios extends javax.swing.JPanel {

    Controlador controlador = null;
    private String usuario = null;
    private String password = null;

    public PanelUsuarios(Controlador controlador, String usuario, String password) {
        this.controlador = controlador;
        this.usuario = usuario;
        this.password = password;
        initComponents();
        cargarUsuarios();
        cargarUsuario1();
    }

    public void cargarUsuarios() {
        controlador.getConexion("sys", "root123");
        ResultSet rs = controlador.cargarUsuario();

        if (rs != null) {
            try {
                comboUsuarios.removeAllItems();
                while (rs.next()) {
                    comboUsuarios.addItem(rs.getString(1));
                }
                comboUsuarios.setSelectedIndex(-1);
                controlador.cerrar();

            } catch (SQLException ex) {
                System.err.println("ERROR SQLException:  " + ex);
            }
        }
    }

    public void cargarUsuario1() {
        controlador.getConexion("sys", "root123");
        ResultSet rs = controlador.cargarUsuario();

        if (rs != null) {

            try {
                ComboUsuario1.removeAllItems();
                while (rs.next()) {
                    ComboUsuario1.addItem(rs.getString(1));
                }
                ComboUsuario1.setSelectedIndex(-1);
                controlador.cerrar();

            } catch (SQLException ex) {
                System.err.println("ERROR SQLException:  " + ex);
            }
        }
    }

    public void cargarRoles() {
        controlador.getConexion("sys", "root123");
        ResultSet rs = controlador.cargarRoles(ComboUsuario1.getSelectedItem().toString());

        if (rs != null) {
            try {
                comboRoles.removeAllItems();
                while (rs.next()) {
                    comboRoles.addItem(rs.getString(1));
                }
                comboRoles.setSelectedIndex(-1);
                controlador.cerrar();

            } catch (SQLException ex) {
                System.err.println("ERROR SQLException:  " + ex);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        crearRol = new javax.swing.JLabel();
        comboUsuarios = new javax.swing.JComboBox<>();
        btnCrearRol = new javax.swing.JButton();
        txtNombreRol1 = new javax.swing.JTextField();
        crearRol4 = new javax.swing.JLabel();
        comboRoles = new javax.swing.JComboBox<>();
        crearRol1 = new javax.swing.JLabel();
        txtNombreRol = new javax.swing.JTextField();
        btnRemoverRol = new javax.swing.JButton();
        btnAsignarRol = new javax.swing.JButton();
        param_header2 = new javax.swing.JLabel();
        param_header3 = new javax.swing.JLabel();
        crearRol2 = new javax.swing.JLabel();
        btnConsultar = new javax.swing.JButton();
        ComboUsuario1 = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        crearRol3 = new javax.swing.JLabel();
        txtNombreUsuario = new javax.swing.JTextField();
        btnCrearUsuario = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setMinimumSize(new java.awt.Dimension(560, 560));
        setPreferredSize(new java.awt.Dimension(560, 560));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setBackground(new java.awt.Color(0, 0, 0));
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Seguridad de usuarios");
        lblTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 60));

        crearRol.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        crearRol.setText("Indique el usuario:");
        add(crearRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 490, 160, 30));

        comboUsuarios.setBackground(java.awt.Color.darkGray);
        comboUsuarios.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        comboUsuarios.setForeground(java.awt.Color.white);
        comboUsuarios.setBorder(null);
        add(comboUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, 220, 20));

        btnCrearRol.setBackground(new java.awt.Color(153, 255, 153));
        btnCrearRol.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnCrearRol.setText("Crear Rol");
        btnCrearRol.setBorder(null);
        btnCrearRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearRolActionPerformed(evt);
            }
        });
        add(btnCrearRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 110, 40));

        txtNombreRol1.setBorder(null);
        add(txtNombreRol1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 350, 180, 20));

        crearRol4.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        crearRol4.setText("Seleccione el usuario:");
        add(crearRol4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, 130, -1));

        comboRoles.setBackground(java.awt.Color.darkGray);
        comboRoles.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        comboRoles.setForeground(java.awt.Color.white);
        comboRoles.setBorder(null);
        add(comboRoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 490, 190, 30));

        crearRol1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        crearRol1.setText("Indique el usuario que desea crear:");
        add(crearRol1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, -1, 30));

        txtNombreRol.setBorder(null);
        add(txtNombreRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 150, 30));

        btnRemoverRol.setBackground(new java.awt.Color(255, 102, 102));
        btnRemoverRol.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnRemoverRol.setForeground(java.awt.Color.white);
        btnRemoverRol.setText("Remover");
        btnRemoverRol.setBorder(null);
        btnRemoverRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverRolActionPerformed(evt);
            }
        });
        add(btnRemoverRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 400, 100, 30));

        btnAsignarRol.setBackground(new java.awt.Color(153, 255, 153));
        btnAsignarRol.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnAsignarRol.setText("Asignar");
        btnAsignarRol.setBorder(null);
        btnAsignarRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarRolActionPerformed(evt);
            }
        });
        add(btnAsignarRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 400, 100, 30));

        param_header2.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        param_header2.setText("Consultar roles de usuario");
        add(param_header2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, -1, -1));

        param_header3.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        param_header3.setText("Asignar o remover rol a un usuario");
        add(param_header3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, -1, -1));

        crearRol2.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        crearRol2.setText("Indique el rol:");
        add(crearRol2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, 80, -1));

        btnConsultar.setBackground(java.awt.Color.darkGray);
        btnConsultar.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnConsultar.setForeground(java.awt.Color.white);
        btnConsultar.setText("Consultar");
        btnConsultar.setBorder(null);
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        add(btnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 490, 100, 30));

        ComboUsuario1.setBackground(java.awt.Color.darkGray);
        ComboUsuario1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        ComboUsuario1.setForeground(java.awt.Color.white);
        ComboUsuario1.setBorder(null);
        add(ComboUsuario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 490, 190, 30));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 700, 10));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 700, 10));
        add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 700, 10));

        crearRol3.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        crearRol3.setText("Indique el rol que desea crear:");
        add(crearRol3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 30));
        add(txtNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 150, 140, 30));

        btnCrearUsuario.setBackground(new java.awt.Color(153, 255, 153));
        btnCrearUsuario.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnCrearUsuario.setText("Crear Usuario");
        btnCrearUsuario.setBorder(null);
        btnCrearUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearUsuarioActionPerformed(evt);
            }
        });
        add(btnCrearUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 190, 110, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearRolActionPerformed
        if (controlador.crearRol(txtNombreRol.getText())) {
            JOptionPane.showMessageDialog(null, "El rol fue creado con exito", "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Hubo un error, intente de nuevo", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnCrearRolActionPerformed

    private void btnRemoverRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverRolActionPerformed
        if (controlador.revocarRolUsuario(txtNombreRol1.getText(), comboUsuarios.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(null, "El rol fue removido con exito", "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Hubo un error, intente de nuevo", "", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnRemoverRolActionPerformed

    private void btnAsignarRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarRolActionPerformed
        if (controlador.otorgarRolUsuario(txtNombreRol1.getText(), comboUsuarios.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(null, "El rol fue asignado con exito", "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Hubo un error, intente de nuevo", "", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAsignarRolActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        cargarRoles();
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnCrearUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearUsuarioActionPerformed
        if (controlador.crearUsuario(txtNombreUsuario.getText())) {
            JOptionPane.showMessageDialog(null, "El usuario fue creado con exito", "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Hubo un error, intente de nuevo", "", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnCrearUsuarioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboUsuario1;
    private javax.swing.JButton btnAsignarRol;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnCrearRol;
    private javax.swing.JButton btnCrearUsuario;
    private javax.swing.JButton btnRemoverRol;
    private javax.swing.JComboBox<String> comboRoles;
    private javax.swing.JComboBox<String> comboUsuarios;
    private javax.swing.JLabel crearRol;
    private javax.swing.JLabel crearRol1;
    private javax.swing.JLabel crearRol2;
    private javax.swing.JLabel crearRol3;
    private javax.swing.JLabel crearRol4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel param_header2;
    private javax.swing.JLabel param_header3;
    private javax.swing.JTextField txtNombreRol;
    private javax.swing.JTextField txtNombreRol1;
    private javax.swing.JTextField txtNombreUsuario;
    // End of variables declaration//GEN-END:variables
}
