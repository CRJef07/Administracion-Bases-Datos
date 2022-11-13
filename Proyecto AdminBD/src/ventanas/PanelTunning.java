package ventanas;

import controlador.Controlador;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PanelTunning extends javax.swing.JPanel {

    Controlador controlador = null;
    private String usuario = null;
    private String password = null;

    List<String> directorios = new ArrayList();

    public PanelTunning(Controlador controlador, String usuario, String password) {
        this.controlador = controlador;
        this.usuario = usuario;
        this.password = password;
        initComponents();
    }

    public void cargarUsuarios() {
        controlador.getConexion("sys", "root123");
        ResultSet rs = controlador.cargarUsuario();

        if (rs != null) {
            try {
                inputSchema.removeAllItems();
                while (rs.next()) {
                    inputSchema.addItem(rs.getString(1));
                }
                inputSchema.setSelectedIndex(-1);

            } catch (SQLException ex) {
                //
            }
        }
    }

    public void cargarTablas() {
        controlador.getConexion("sys", "root123");
        ResultSet rs = controlador.verTablasXSchema(String.valueOf(inputSchema.getSelectedItem()));

        if (rs != null) {

            try {
                inputTabla.removeAllItems();
                while (rs.next()) {
                    inputTabla.addItem(rs.getString(1));
                }
                inputTabla.setSelectedIndex(-1);

            } catch (SQLException ex) {
                System.err.println("ERROR SQLException:  " + ex);
            }
        }
    }

    public void cargarCampos() {
        controlador.getConexion("sys", "root123");
        ResultSet rs = controlador.verCamposXTabla(String.valueOf(inputTabla.getSelectedItem()));

        if (rs != null) {

            try {
                inputCampo.removeAllItems();
                while (rs.next()) {
                    inputCampo.addItem(rs.getString(1));
                }
                inputCampo.setSelectedIndex(-1);

            } catch (SQLException ex) {
                System.err.println("ERROR SQLException:  " + ex);
            }
        }
    }

    public void CrearPlan() {
        String msj = controlador.executarQueryOptimizar(inputConsultaSQL.getText());
        if (msj.equals("true")) {
            JOptionPane.showMessageDialog(null, "Se ha creado el plan correctamente", "Plan Creado", JOptionPane.INFORMATION_MESSAGE);
            llenarGrid();//llama al metodo que llena la tabla para mostrar el resultado de una vez
            inputConsultaSQL.setText("");
        } else {
            JOptionPane.showMessageDialog(null, msj, "Error al crear Plan", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void llenarGrid() {

        ResultSet resultado;
        DefaultTableModel modelo = (DefaultTableModel) this.tablaPlanes.getModel();

        for (int i = 0; i < tablaPlanes.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }

        resultado = controlador.obtenerExplainPlan();//metodo

        this.tablaPlanes.removeAll();

        if (resultado != null) {
            try {
                while (resultado.next()) {
                    Object[] fila = new Object[3];
                    fila[0] = resultado.getString("OPERACION");
                    fila[1] = resultado.getString("OBJETO");
                    fila[2] = resultado.getString("FECHA");
                    modelo.addRow(fila);
                }
                tablaPlanes.setModel(modelo);

            } catch (Exception ex) {
                System.err.println("ERROR SQLException:  " + ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error al consulta", "Error de Consultar", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void llenarGrid2() {

        ResultSet resultado;
        DefaultTableModel modelo = (DefaultTableModel) this.jTable1.getModel();

        for (int i = 0; i < jTable1.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }

        resultado = controlador.cargarIndicesTabla(String.valueOf(inputTabla.getSelectedItem()));

        this.jTable1.removeAll();

        try {
            while (resultado.next()) {
                Object[] fila = new Object[2];
                fila[0] = resultado.getString("INDEX_NAME");
                fila[1] = resultado.getString("COLUMN_NAME");
                modelo.addRow(fila);
            }
            jTable1.setModel(modelo);

        } catch (SQLException ex) {
            System.err.println("ERROR SQLException:  " + ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        inputConsultaSQL = new javax.swing.JTextField();
        btnCrearPlan = new javax.swing.JButton();
        btnverPlan1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPlanes = new javax.swing.JTable();
        btnEliminarPlanes = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        inputSchema = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        inputTabla = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        inputCampo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        inputNombre = new javax.swing.JTextField();
        btnCrearPlan1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setMinimumSize(new java.awt.Dimension(560, 560));
        setPreferredSize(new java.awt.Dimension(560, 560));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setBackground(new java.awt.Color(0, 0, 0));
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Tunning de consultas (planes de ejecución, estadísticas, indices)");
        lblTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 90));

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Debe digitar una consulta SQL");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 170, 30));

        inputConsultaSQL.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        inputConsultaSQL.setBorder(null);
        add(inputConsultaSQL, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 280, 30));

        btnCrearPlan.setBackground(new java.awt.Color(153, 255, 153));
        btnCrearPlan.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnCrearPlan.setText("Crear plan");
        btnCrearPlan.setBorder(null);
        btnCrearPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearPlanActionPerformed(evt);
            }
        });
        add(btnCrearPlan, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 100, 30));

        btnverPlan1.setBackground(java.awt.Color.darkGray);
        btnverPlan1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnverPlan1.setForeground(java.awt.Color.white);
        btnverPlan1.setText("Ver planes de Ejecución");
        btnverPlan1.setBorder(null);
        btnverPlan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnverPlan1ActionPerformed(evt);
            }
        });
        add(btnverPlan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 550, 30));

        tablaPlanes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Operación", "Objeto", "Fecha de Creación"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaPlanes);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 550, 170));

        btnEliminarPlanes.setBackground(java.awt.Color.darkGray);
        btnEliminarPlanes.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnEliminarPlanes.setForeground(java.awt.Color.white);
        btnEliminarPlanes.setText("Limpiar tabla de planes");
        btnEliminarPlanes.setBorder(null);
        btnEliminarPlanes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPlanesActionPerformed(evt);
            }
        });
        add(btnEliminarPlanes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 550, 30));

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel6.setText("Schema:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 190, 60, 30));

        inputSchema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputSchemaActionPerformed(evt);
            }
        });
        add(inputSchema, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 190, 120, 30));

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel5.setText("Tabla:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 230, 60, 30));

        inputTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputTablaActionPerformed(evt);
            }
        });
        add(inputTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 230, 120, 30));

        jLabel8.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel8.setText("Campo:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 270, 60, 30));

        add(inputCampo, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 270, 120, 30));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel3.setText("Nombre:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 310, 60, 30));
        add(inputNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 310, 120, 30));

        btnCrearPlan1.setBackground(new java.awt.Color(153, 255, 153));
        btnCrearPlan1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnCrearPlan1.setText("Crear Indice");
        btnCrearPlan1.setBorder(null);
        btnCrearPlan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearPlan1ActionPerformed(evt);
            }
        });
        add(btnCrearPlan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 360, 180, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nombre del Indice", "Nombre de la columna"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 450, 320, 140));
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearPlanActionPerformed
        CrearPlan();
    }//GEN-LAST:event_btnCrearPlanActionPerformed

    private void inputSchemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputSchemaActionPerformed
        cargarTablas();
    }//GEN-LAST:event_inputSchemaActionPerformed

    private void inputTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputTablaActionPerformed
        cargarCampos();
    }//GEN-LAST:event_inputTablaActionPerformed

    private void btnCrearPlan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearPlan1ActionPerformed
        if (controlador.crearIndice(String.valueOf(inputSchema.getSelectedItem()), String.valueOf(inputTabla.getSelectedItem()), String.valueOf(inputCampo.getSelectedItem()),
                inputNombre.getText())) {
            JOptionPane.showMessageDialog(null, "Se ha creado un indice para el campo " + String.valueOf(inputCampo.getSelectedItem())
                    + " de la tabla " + String.valueOf(inputTabla.getSelectedItem()) + " en el schema " + String.valueOf(inputSchema.getSelectedItem()), "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se logro crear un indice para el campo " + String.valueOf(inputCampo.getSelectedItem())
                    + " de la tabla " + String.valueOf(inputTabla.getSelectedItem()) + " en el schema " + String.valueOf(inputSchema.getSelectedItem()), "", JOptionPane.ERROR_MESSAGE);
        }
        llenarGrid2();
    }//GEN-LAST:event_btnCrearPlan1ActionPerformed

    private void btnEliminarPlanesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPlanesActionPerformed
        if (controlador.BorrarTablaPlanes()) {
            JOptionPane.showMessageDialog(null, "Se ha limpiado la tabla de planes", "Limpia completa", JOptionPane.INFORMATION_MESSAGE);
            llenarGrid();
        }
    }//GEN-LAST:event_btnEliminarPlanesActionPerformed

    private void btnverPlan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnverPlan1ActionPerformed
        llenarGrid();
    }//GEN-LAST:event_btnverPlan1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrearPlan;
    private javax.swing.JButton btnCrearPlan1;
    private javax.swing.JButton btnEliminarPlanes;
    private javax.swing.JButton btnverPlan1;
    private javax.swing.JComboBox<String> inputCampo;
    private javax.swing.JTextField inputConsultaSQL;
    private javax.swing.JTextField inputNombre;
    private javax.swing.JComboBox<String> inputSchema;
    private javax.swing.JComboBox<String> inputTabla;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tablaPlanes;
    // End of variables declaration//GEN-END:variables
}
