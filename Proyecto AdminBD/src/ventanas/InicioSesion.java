package ventanas;

import controlador.Controlador;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class InicioSesion extends javax.swing.JFrame {

    Controlador controlador = null;

    public InicioSesion() {
        this.controlador = new Controlador();
        initComponents();
    }

    public void IniciarVentana() {
        cargarUsuarios();
        setTitle("Inicio Sesión");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void cargarUsuarios() {
        controlador.getConexion("sys", "root123");
        ResultSet rs = controlador.cargarUsuario();
        if (rs != null) {
            try {
                inputUsuarios.removeAllItems();

                while (rs.next()) {
                    inputUsuarios.addItem(rs.getString(1));
                }

                inputUsuarios.setSelectedIndex(0);
                controlador.cerrar();

            } catch (SQLException e) {
                System.err.println("ERROR AL CARGARUSUARIOS");
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlInicioSesion = new javax.swing.JPanel();
        inputPassword = new javax.swing.JPasswordField();
        inputUsuarios = new javax.swing.JComboBox<>();
        btnIngresar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        pnlInicioSesion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputPassword.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        inputPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pnlInicioSesion.add(inputPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, 150, 40));

        pnlInicioSesion.add(inputUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 200, 40));

        btnIngresar.setBackground(new java.awt.Color(0, 255, 0));
        btnIngresar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        pnlInicioSesion.add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 120, 40));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Administración de Bases de Datos Oracle!");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlInicioSesion.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 400, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("¡Bienvenido al sistema de");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlInicioSesion.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 400, 40));

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsuario.setText("Usuario");
        pnlInicioSesion.add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 200, 20));

        lblPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPassword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPassword.setText("Contraseña");
        pnlInicioSesion.add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 150, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlInicioSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlInicioSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed

        //usuario = (String)esquemas_combo_box.getSelectedItem();
        //contrasena = String.valueOf(contrasena_esquema.getPassword());
        String user = (String) inputUsuarios.getSelectedItem();
        String pass = String.valueOf(inputPassword.getPassword());

        if (controlador.getConexion(user, pass)) {
            JOptionPane.showMessageDialog(null, "Correcto!");
        } else {
            JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error al iniciar sesión", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnIngresarActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InicioSesion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JPasswordField inputPassword;
    private javax.swing.JComboBox<String> inputUsuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pnlInicioSesion;
    // End of variables declaration//GEN-END:variables
}
