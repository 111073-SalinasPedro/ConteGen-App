package Ventana;

import DTO.*;
import GestorBD.GestorBD;
import Model.Cliente;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class VentanaReporteCliente extends javax.swing.JFrame {

    GestorBD g;
    DefaultTableModel modelo = new DefaultTableModel();

    public VentanaReporteCliente() {
        initComponents();
        g = new GestorBD();
        //--
        modelo.addColumn("Cliente");
        modelo.addColumn("Cantidad de Pedidos");
        tblPedidoPorCliente.setModel(modelo);
        cargarTablaCliente();
        //--
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); //Alinear Texto Centrado
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int x = 0; x < 2; x++) {
            tblPedidoPorCliente.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }
        tblPedidoPorCliente.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblPedidoPorCliente.getColumnModel().getColumn(1).setPreferredWidth(30);

    }

    private void cargarTablaCliente() {
        ArrayList<DTOPedidosPorCliente> lista = g.listadoPedidoPorCliente();

        for (DTOPedidosPorCliente c : lista) {
            modelo.addRow(new Object[]{
                c.getCliente(),
                c.getCantidadPedidos()});
        }
        tblPedidoPorCliente.setModel(modelo);
    }

    public void buscarEntreFecha() {
        int dia, mes, año;
        int dia2, mes2, año2;
        String fecha1, fecha2;

        dia = dtDesde.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes = dtDesde.getCalendar().get(Calendar.MONTH) + 1;
        año = dtDesde.getCalendar().get(Calendar.YEAR);
        fecha1 = año + "-" + mes + "-" + dia;
        dia2 = dtHasta.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes2 = dtHasta.getCalendar().get(Calendar.MONTH) + 1;
        año2 = dtHasta.getCalendar().get(Calendar.YEAR);
        fecha2 = año2 + "-" + mes2 + "-" + dia2;

        List<DTOPedidosPorCliente> list = g.mostrarPedidosPorClienteDesdeHasta(fecha1, fecha2);
        modelo.setRowCount(0);
        for (DTOPedidosPorCliente p : list) {
            modelo.addRow(new Object[]{p.getCliente(), p.getCantidadPedidos()});
        }
    }

    public void addPlaceHolder(JTextField textField) {
        Font font = textField.getFont();
        font = font.deriveFont(Font.ITALIC);
        textField.setFont(font);
        textField.setForeground(Color.gray);
    }

    public void removePlaceHolder(JTextField textField) {
        Font font = textField.getFont();
        font = font.deriveFont(Font.PLAIN);
        textField.setFont(font);
        textField.setForeground(Color.BLACK);
    }

    private void filterConsulta(String query) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modelo);
        tblPedidoPorCliente.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarCliente.getText(), 0));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backGround1 = new PanelesBG.BackGround();
        jLabel2 = new javax.swing.JLabel();
        lblVolver3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dtHasta = new com.toedter.calendar.JDateChooser();
        dtDesde = new com.toedter.calendar.JDateChooser();
        btnFiltrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedidoPorCliente = new javax.swing.JTable();
        txtBuscarCliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        backGround1.setColor1(new java.awt.Color(29, 207, 69));
        backGround1.setColor2(new java.awt.Color(156, 255, 51));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        jLabel2.setText("REPORTE");

        lblVolver3.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        lblVolver3.setText("<-Volver");
        lblVolver3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblVolver3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVolver3MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel1.setText("PEDIDOS POR CLIENTE");

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel4.setText("Hasta");

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel3.setText("Desde");

        dtHasta.setDateFormatString("yyyy-MM-dd");
        dtHasta.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        dtDesde.setDateFormatString("yyyy-MM-dd");
        dtDesde.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        btnFiltrar.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        btnFiltrar.setText("FILTRAR");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        tblPedidoPorCliente.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
        tblPedidoPorCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Cliente", "Pedidos"
            }
        ));
        jScrollPane1.setViewportView(tblPedidoPorCliente);

        txtBuscarCliente.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtBuscarCliente.setForeground(new java.awt.Color(204, 204, 204));
        txtBuscarCliente.setText("Buscar Cliente...");
        txtBuscarCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarClienteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarClienteFocusLost(evt);
            }
        });
        txtBuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarClienteKeyReleased(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\search.png")); // NOI18N

        javax.swing.GroupLayout backGround1Layout = new javax.swing.GroupLayout(backGround1);
        backGround1.setLayout(backGround1Layout);
        backGround1Layout.setHorizontalGroup(
            backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backGround1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backGround1Layout.createSequentialGroup()
                        .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(backGround1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(lblVolver3))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addGroup(backGround1Layout.createSequentialGroup()
                        .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(backGround1Layout.createSequentialGroup()
                                .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(backGround1Layout.createSequentialGroup()
                                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnFiltrar)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backGround1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(140, 140, 140))
        );
        backGround1Layout.setVerticalGroup(
            backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backGround1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblVolver3))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(44, 44, 44)
                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backGround1Layout.createSequentialGroup()
                        .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnFiltrar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backGround1Layout.createSequentialGroup()
                                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(dtHasta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(backGround1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(backGround1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backGround1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        try {
            buscarEntreFecha();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar una fecha", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void lblVolver3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVolver3MouseClicked
        dispose();
    }//GEN-LAST:event_lblVolver3MouseClicked

    private void txtBuscarClienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarClienteFocusGained
        if (txtBuscarCliente.getText().equals("Buscar Cliente...")) {
            txtBuscarCliente.setText(null);
            txtBuscarCliente.requestFocus();
            removePlaceHolder(txtBuscarCliente);
        }
    }//GEN-LAST:event_txtBuscarClienteFocusGained

    private void txtBuscarClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarClienteFocusLost
        if (txtBuscarCliente.getText().length() == 0) {
            addPlaceHolder(txtBuscarCliente);
            txtBuscarCliente.setText("Buscar Cliente...");
        }
    }//GEN-LAST:event_txtBuscarClienteFocusLost

    private void txtBuscarClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClienteKeyReleased
        String query = txtBuscarCliente.getText();
        filterConsulta(query);
    }//GEN-LAST:event_txtBuscarClienteKeyReleased

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
            java.util.logging.Logger.getLogger(VentanaReporteCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaReporteCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaReporteCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaReporteCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaReporteCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private PanelesBG.BackGround backGround1;
    private javax.swing.JButton btnFiltrar;
    private com.toedter.calendar.JDateChooser dtDesde;
    private com.toedter.calendar.JDateChooser dtHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblVolver3;
    private javax.swing.JTable tblPedidoPorCliente;
    private javax.swing.JTextField txtBuscarCliente;
    // End of variables declaration//GEN-END:variables
}
