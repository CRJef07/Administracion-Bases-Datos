package ventanas;

import controlador.Controlador;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PanelPerformance extends javax.swing.JPanel {

    Controlador controlador = null;
    private String usuario = null;
    private String password = null;

    public PanelPerformance(Controlador controlador, String usuario, String password) {
        this.controlador = controlador;
        this.usuario = usuario;
        this.password = password;
        initComponents();

        this.buttonGroup1.add(this.rbTabla);
        this.buttonGroup1.add(this.rbSchema);
        this.cbTablas.setEnabled(false);
        cargarTablasUsuario();
    }

    public void llenarGrid() {
        ResultSet resultado;
        DefaultTableModel modelo = (DefaultTableModel) this.jTable1.getModel();

        for (int i = 0; i < jTable1.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }

        if (this.rbTabla.isSelected()) {
            resultado = controlador.ConsultaStats(usuario, this.cbTablas.getSelectedItem().toString());
            this.jTable1.removeAll();

            try {
                Object[] fila = new Object[4];
                resultado.next();
                fila[0] = resultado.getString("OWNER");
                fila[1] = resultado.getString("TABLE_NAME");
                fila[2] = resultado.getString("NUM_ROWS");
                fila[3] = resultado.getString("LAST_ANALYZED");
                modelo.addRow(fila);
                jTable1.setModel(modelo);

            } catch (SQLException ex) {
                System.err.println("ERROR SQLException:  " + ex);
            }

        } else if (this.rbSchema.isSelected()) {
            resultado = controlador.ConsultaStats(usuario, "Schema");

            try {
                while (resultado.next()) {

                    Object[] fila = new Object[4];
                    fila[0] = resultado.getString("OWNER");
                    fila[1] = resultado.getString("TABLE_NAME");
                    fila[2] = resultado.getString("NUM_ROWS");
                    fila[3] = resultado.getString("LAST_ANALYZED");
                    modelo.addRow(fila);
                }
                jTable1.setModel(modelo);

            } catch (SQLException ex) {
                System.err.println("ERROR SQLException:  " + ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar alguna opción", "Error al Cargar Estadisticas", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cargarTablasUsuario() {
        ResultSet resultado = controlador.cargarTablasUsuario(usuario);
        cbTablas.removeAllItems();

        try {
            while (resultado.next()) {
                cbTablas.addItem(resultado.getString("TABLE_NAME"));
            }
            cbTablas.setSelectedIndex(-1);

        } catch (SQLException ex) {
            System.err.println("ERROR SQLException:  " + ex);
        }
    }

    public void GenerarEstadisticas() {
        if (this.rbTabla.isSelected()) {
            controlador.GeneraStats(usuario, this.cbTablas.getSelectedItem().toString());

        } else if (this.rbSchema.isSelected()) {
            controlador.GeneraStats(usuario, "Schema");

        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar alguna opción", "Error al Generar Estadisticas", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lblTitulo = new javax.swing.JLabel();
        btnGenerarStats = new javax.swing.JButton();
        cbTablas = new javax.swing.JComboBox<>();
        btnVerStats = new javax.swing.JButton();
        rbSchema = new javax.swing.JRadioButton();
        rbTabla = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setMinimumSize(new java.awt.Dimension(560, 560));
        setPreferredSize(new java.awt.Dimension(560, 560));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setBackground(new java.awt.Color(0, 0, 0));
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Performance");
        lblTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 60));

        btnGenerarStats.setBackground(new java.awt.Color(153, 255, 153));
        btnGenerarStats.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnGenerarStats.setText("Generar Estadisticas");
        btnGenerarStats.setBorder(null);
        btnGenerarStats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarStatsActionPerformed(evt);
            }
        });
        add(btnGenerarStats, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, 210, 40));

        cbTablas.setBorder(null);
        add(cbTablas, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, 260, 60));

        btnVerStats.setBackground(java.awt.Color.darkGray);
        btnVerStats.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnVerStats.setForeground(java.awt.Color.white);
        btnVerStats.setText("Ver Estadisticas");
        btnVerStats.setBorder(null);
        btnVerStats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerStatsActionPerformed(evt);
            }
        });
        add(btnVerStats, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 490, 140, 30));

        rbSchema.setBackground(new java.awt.Color(255, 255, 255));
        rbSchema.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        rbSchema.setText("Esquema");
        rbSchema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSchemaActionPerformed(evt);
            }
        });
        add(rbSchema, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 100, 60));

        rbTabla.setBackground(new java.awt.Color(255, 255, 255));
        rbTabla.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        rbTabla.setText("Tabla");
        rbTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTablaActionPerformed(evt);
            }
        });
        add(rbTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 100, 60));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Schema", "Tabla", "Registros", "Ultimo Analisis"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 650, 220));
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarStatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarStatsActionPerformed
        this.GenerarEstadisticas();
        this.llenarGrid();
    }//GEN-LAST:event_btnGenerarStatsActionPerformed

    private void btnVerStatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerStatsActionPerformed
        this.llenarGrid();
    }//GEN-LAST:event_btnVerStatsActionPerformed

    private void rbSchemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSchemaActionPerformed
        this.cbTablas.setEnabled(false);
    }//GEN-LAST:event_rbSchemaActionPerformed

    private void rbTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTablaActionPerformed
        this.cbTablas.setEnabled(true);
        cbTablas.setSelectedItem(cbTablas.getItemAt(0));
    }//GEN-LAST:event_rbTablaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarStats;
    private javax.swing.JButton btnVerStats;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbTablas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JRadioButton rbSchema;
    private javax.swing.JRadioButton rbTabla;
    // End of variables declaration//GEN-END:variables
}
