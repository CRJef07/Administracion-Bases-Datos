package ventanas;

import controlador.Controlador;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class VentanaPrincipal extends javax.swing.JFrame {

    Controlador controlador = null;
    private String usuario = null;
    private String password = null;

    public VentanaPrincipal(Controlador controlador, String usuario, String password) {
        this.controlador = controlador;
        this.usuario = usuario;
        this.password = password;
        initComponents();
    }

    public void IniciarVentana() {
        setTitle("Pantalla Principal");
        setSize(220, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);//javax.​swing.​WindowConstants
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        PnlPrueba1 panel = new PnlPrueba1();
        ShowPanel(panel);
    }

    private void ShowPanel(JPanel panel) {
        panel.setSize(560, 560);
        panel.setLocation(0, 0);
        pnlContenido.removeAll();
        pnlContenido.add(panel, BorderLayout.CENTER);
        pnlContenido.revalidate();
        pnlContenido.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        btnTablespaces = new javax.swing.JButton();
        btnTuning = new javax.swing.JButton();
        btnTablespaces2 = new javax.swing.JButton();
        btnTablespaces3 = new javax.swing.JButton();
        btnTablespaces4 = new javax.swing.JButton();
        btnTablespaces5 = new javax.swing.JButton();
        btnTablespaces6 = new javax.swing.JButton();
        btnTablespaces7 = new javax.swing.JButton();
        btnTablespaces8 = new javax.swing.JButton();
        pnlContenido = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 560));
        setPreferredSize(new java.awt.Dimension(800, 560));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 560));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlPrincipal.setMinimumSize(new java.awt.Dimension(220, 560));
        pnlPrincipal.setPreferredSize(new java.awt.Dimension(220, 560));
        pnlPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnTablespaces.setText("TABLESPACES");
        btnTablespaces.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablespacesActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnTablespaces, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 170, 40));

        btnTuning.setText("TUNING");
        btnTuning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTuningActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnTuning, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 170, 40));

        btnTablespaces2.setText("PERFORMANCE");
        btnTablespaces2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablespaces2ActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnTablespaces2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 170, 40));

        btnTablespaces3.setText("AUDITORIA");
        btnTablespaces3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablespaces3ActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnTablespaces3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 170, 40));

        btnTablespaces4.setText("ROLES");
        btnTablespaces4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablespaces4ActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnTablespaces4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 170, 40));

        btnTablespaces5.setText("RESPALDOS");
        btnTablespaces5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablespaces5ActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnTablespaces5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 170, 40));

        btnTablespaces6.setText("RECUPERACIÓN");
        btnTablespaces6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablespaces6ActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnTablespaces6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 170, 40));

        btnTablespaces7.setText("PARÁMETROS");
        btnTablespaces7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablespaces7ActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnTablespaces7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 170, 40));

        btnTablespaces8.setText("ADMIN ARCHIVOS");
        btnTablespaces8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablespaces8ActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnTablespaces8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 170, 40));

        getContentPane().add(pnlPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 560));
        pnlPrincipal.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout pnlContenidoLayout = new javax.swing.GroupLayout(pnlContenido);
        pnlContenido.setLayout(pnlContenidoLayout);
        pnlContenidoLayout.setHorizontalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );
        pnlContenidoLayout.setVerticalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );

        getContentPane().add(pnlContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 560, 560));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTablespacesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablespacesActionPerformed

    }//GEN-LAST:event_btnTablespacesActionPerformed

    private void btnTuningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTuningActionPerformed

    }//GEN-LAST:event_btnTuningActionPerformed

    private void btnTablespaces2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablespaces2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTablespaces2ActionPerformed

    private void btnTablespaces3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablespaces3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTablespaces3ActionPerformed

    private void btnTablespaces4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablespaces4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTablespaces4ActionPerformed

    private void btnTablespaces5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablespaces5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTablespaces5ActionPerformed

    private void btnTablespaces6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablespaces6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTablespaces6ActionPerformed

    private void btnTablespaces7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablespaces7ActionPerformed
        PnlPrueba1 panel = new PnlPrueba1();
        ShowPanel(panel);
    }//GEN-LAST:event_btnTablespaces7ActionPerformed

    private void btnTablespaces8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablespaces8ActionPerformed
        PnlPrueba2 panel = new PnlPrueba2();
        ShowPanel(panel);
    }//GEN-LAST:event_btnTablespaces8ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTablespaces;
    private javax.swing.JButton btnTablespaces2;
    private javax.swing.JButton btnTablespaces3;
    private javax.swing.JButton btnTablespaces4;
    private javax.swing.JButton btnTablespaces5;
    private javax.swing.JButton btnTablespaces6;
    private javax.swing.JButton btnTablespaces7;
    private javax.swing.JButton btnTablespaces8;
    private javax.swing.JButton btnTuning;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlPrincipal;
    // End of variables declaration//GEN-END:variables
}
