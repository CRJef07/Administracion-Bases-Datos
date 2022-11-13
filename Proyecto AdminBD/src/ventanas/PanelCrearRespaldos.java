package ventanas;

import controlador.Controlador;

public class PanelCrearRespaldos extends javax.swing.JPanel {

    Controlador controlador = null;
    private String usuario = null;
    private String password = null;

    public PanelCrearRespaldos(Controlador controlador, String usuario, String password) {
        this.controlador = controlador;
        this.usuario = usuario;
        this.password = password;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(560, 560));
        setPreferredSize(new java.awt.Dimension(560, 560));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Tablespaces");
        add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 30));
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
