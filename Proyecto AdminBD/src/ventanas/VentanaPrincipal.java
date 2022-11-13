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
        this.setSize(800, 620);
        setDefaultCloseOperation(EXIT_ON_CLOSE);//javax.​swing.​WindowConstants
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void ShowPanel(JPanel panel) {
        panel.setSize(560, 600);
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
        btnPerformance = new javax.swing.JButton();
        btnAuditoria = new javax.swing.JButton();
        btnRoles = new javax.swing.JButton();
        btnRespaldos = new javax.swing.JButton();
        btnRecuperar = new javax.swing.JButton();
        btnParametros = new javax.swing.JButton();
        btnAdminArchivos = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        pnlContenido = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlPrincipal.setMinimumSize(new java.awt.Dimension(220, 600));
        pnlPrincipal.setPreferredSize(new java.awt.Dimension(220, 600));
        pnlPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnTablespaces.setText("TABLESPACES");
        btnTablespaces.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablespacesActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnTablespaces, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 170, 40));

        btnTuning.setText("TUNING");
        btnTuning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTuningActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnTuning, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 170, 40));

        btnPerformance.setText("PERFORMANCE");
        btnPerformance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerformanceActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnPerformance, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 170, 40));

        btnAuditoria.setText("AUDITORIA");
        btnAuditoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAuditoriaActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnAuditoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 170, 40));

        btnRoles.setText("ROLES");
        btnRoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRolesActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnRoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 170, 40));

        btnRespaldos.setText("RESPALDOS");
        btnRespaldos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRespaldosActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnRespaldos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 170, 40));

        btnRecuperar.setText("RECUPERACIÓN");
        btnRecuperar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecuperarActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnRecuperar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 170, 40));

        btnParametros.setText("PARÁMETROS");
        btnParametros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParametrosActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnParametros, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 170, 40));

        btnAdminArchivos.setText("ADMIN ARCHIVOS");
        btnAdminArchivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminArchivosActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnAdminArchivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 170, 40));

        btnCerrarSesion.setText("CERRAR SESIÓN");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });
        pnlPrincipal.add(btnCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 170, 40));

        getContentPane().add(pnlPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 600));
        pnlPrincipal.getAccessibleContext().setAccessibleName("");

        pnlContenido.setAlignmentX(0.0F);
        pnlContenido.setAlignmentY(0.0F);

        javax.swing.GroupLayout pnlContenidoLayout = new javax.swing.GroupLayout(pnlContenido);
        pnlContenido.setLayout(pnlContenidoLayout);
        pnlContenidoLayout.setHorizontalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );
        pnlContenidoLayout.setVerticalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        getContentPane().add(pnlContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 580, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTablespacesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablespacesActionPerformed
        PanelTablespaces panel = new PanelTablespaces(this.controlador, this.usuario, this.password);
        ShowPanel(panel);
    }//GEN-LAST:event_btnTablespacesActionPerformed

    private void btnTuningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTuningActionPerformed
        PanelTuning panel = new PanelTuning(this.controlador, this.usuario, this.password);
        ShowPanel(panel);
    }//GEN-LAST:event_btnTuningActionPerformed

    private void btnPerformanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerformanceActionPerformed
        PanelPerformance panel = new PanelPerformance(this.controlador, this.usuario, this.password);
        ShowPanel(panel);
    }//GEN-LAST:event_btnPerformanceActionPerformed

    private void btnAuditoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAuditoriaActionPerformed
        PanelAuditoria panel = new PanelAuditoria(this.controlador, this.usuario, this.password);
        ShowPanel(panel);
    }//GEN-LAST:event_btnAuditoriaActionPerformed

    private void btnRolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRolesActionPerformed
        PanelRoles panel = new PanelRoles(this.controlador, this.usuario, this.password);
        ShowPanel(panel);
    }//GEN-LAST:event_btnRolesActionPerformed

    private void btnRespaldosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRespaldosActionPerformed
        PanelCrearRespaldos panel = new PanelCrearRespaldos(this.controlador, this.usuario, this.password);
        ShowPanel(panel);
    }//GEN-LAST:event_btnRespaldosActionPerformed

    private void btnRecuperarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecuperarActionPerformed
        PanelRecuperarRespaldos panel = new PanelRecuperarRespaldos(this.controlador, this.usuario, this.password);
        ShowPanel(panel);
    }//GEN-LAST:event_btnRecuperarActionPerformed

    private void btnParametrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParametrosActionPerformed
        PanelParametros panel = new PanelParametros(this.controlador, this.usuario, this.password);
        ShowPanel(panel);
    }//GEN-LAST:event_btnParametrosActionPerformed

    private void btnAdminArchivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminArchivosActionPerformed
        PanelAdminArchivos panel = new PanelAdminArchivos(this.controlador, this.usuario, this.password);
        ShowPanel(panel);
    }//GEN-LAST:event_btnAdminArchivosActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        controlador.cerrar();

        InicioSesion ventana = new InicioSesion();
        ventana.IniciarVentana();

        this.dispose();

    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdminArchivos;
    private javax.swing.JButton btnAuditoria;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnParametros;
    private javax.swing.JButton btnPerformance;
    private javax.swing.JButton btnRecuperar;
    private javax.swing.JButton btnRespaldos;
    private javax.swing.JButton btnRoles;
    private javax.swing.JButton btnTablespaces;
    private javax.swing.JButton btnTuning;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlPrincipal;
    // End of variables declaration//GEN-END:variables
}
