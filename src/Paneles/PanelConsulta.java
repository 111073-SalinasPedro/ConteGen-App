package Paneles;

import DTO.DTOPedido;
import GestorBD.GestorBD;
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

public class PanelConsulta extends javax.swing.JPanel {

    GestorBD g;
    DefaultTableModel modeloListado = new DefaultTableModel();

    public PanelConsulta() {
        initComponents();
        g = new GestorBD();
        //--
        modeloListado.addColumn("Id");
        modeloListado.addColumn("Cliente");
        modeloListado.addColumn("Direccion");
        modeloListado.addColumn("Barrio");
        modeloListado.addColumn("Zona");
        modeloListado.addColumn("Fecha de Entrega");
        modeloListado.addColumn("Horario");
        modeloListado.addColumn("Dias");
        modeloListado.addColumn("Cantidad");
        modeloListado.addColumn("Observaciones");
        modeloListado.addColumn("Estado");
        tblListadoPedidos.setModel(modeloListado);
        cargarTablaPedidos();
        //---
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); //Alinear Texto Centrado
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int x = 0; x < 11; x++) {
            tblListadoPedidos.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }
        tblListadoPedidos.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblListadoPedidos.getColumnModel().getColumn(1).setPreferredWidth(70);
        tblListadoPedidos.getColumnModel().getColumn(2).setPreferredWidth(60);
        tblListadoPedidos.getColumnModel().getColumn(3).setPreferredWidth(15);
        tblListadoPedidos.getColumnModel().getColumn(4).setPreferredWidth(40);
        tblListadoPedidos.getColumnModel().getColumn(5).setPreferredWidth(40);
        tblListadoPedidos.getColumnModel().getColumn(6).setPreferredWidth(30);
        tblListadoPedidos.getColumnModel().getColumn(7).setPreferredWidth(15);
        tblListadoPedidos.getColumnModel().getColumn(8).setPreferredWidth(15);
        tblListadoPedidos.getColumnModel().getColumn(9).setPreferredWidth(30);
        tblListadoPedidos.getColumnModel().getColumn(10).setPreferredWidth(30);
    }

    private void cargarTablaPedidos() {
        ArrayList<DTOPedido> lista = g.listadoPedidos();

        for (DTOPedido c : lista) {
            modeloListado.addRow(new Object[]{
                c.getIdPedido(),
                c.getNombreCliente(),
                c.getDireccion(),
                c.getBarrio(),
                c.getZona(),
                c.getFechaEntrega(),
                c.getHorario(),
                c.getDias(),
                c.getCant(),
                c.getObservaciones(),
                c.getEstado()});
        }
        tblListadoPedidos.setModel(modeloListado);
    }
    private void filterConsulta(String query) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modeloListado);
        tblListadoPedidos.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarPedido.getText(), 0));
    }    
    
     public void buscarEntreFechaConsulta() {
        int dia, mes, año;
        int dia2, mes2, año2;
        String fecha1, fecha2;

        dia = dtDesdeListaPedido.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes = dtDesdeListaPedido.getCalendar().get(Calendar.MONTH) + 1;
        año = dtDesdeListaPedido.getCalendar().get(Calendar.YEAR);
        fecha1 = año + "-" + mes + "-" + dia;
        dia2 = dtHastaListaPedido.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes2 = dtHastaListaPedido.getCalendar().get(Calendar.MONTH) + 1;
        año2 = dtHastaListaPedido.getCalendar().get(Calendar.YEAR);
        fecha2 = año2 + "-" + mes2 + "-" + dia2;

        List<DTOPedido> list = g.mostrarListadoPedidoDesdeHasta(fecha1, fecha2);
        modeloListado.setRowCount(0);
        for (DTOPedido p : list) {
            modeloListado.addRow(new Object[]{p.getIdPedido(), p.getNombreCliente(), p.getDireccion(), p.getBarrio(),
                p.getZona(), p.getFechaEntrega(), p.getHorario(), p.getDias(), p.getCant(),
                p.getObservaciones(), p.getEstado()});
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        backGround1 = new PanelesBG.BackGround();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblListadoPedidos = new javax.swing.JTable();
        txtBuscarPedido = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        btnFiltarListadoPedido = new javax.swing.JButton();
        dtDesdeListaPedido = new com.toedter.calendar.JDateChooser();
        dtHastaListaPedido = new com.toedter.calendar.JDateChooser();

        backGround1.setColor1(new java.awt.Color(255, 168, 11));
        backGround1.setColor2(new java.awt.Color(255, 168, 11));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 1, 36)); // NOI18N
        jLabel1.setText("LISTADO DE PEDIDOS");

        tblListadoPedidos.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblListadoPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Cliente", "Direccion", "Barrio", "Zona", "Fecha de Entrega", "Horario", "Dias", "Cantidad", "Observaciones", "Estado"
            }
        ));
        jScrollPane3.setViewportView(tblListadoPedidos);

        txtBuscarPedido.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtBuscarPedido.setForeground(new java.awt.Color(204, 204, 204));
        txtBuscarPedido.setText("Buscar Pedido...");
        txtBuscarPedido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarPedidoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarPedidoFocusLost(evt);
            }
        });
        txtBuscarPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarPedidoKeyReleased(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\search.png")); // NOI18N

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel44.setText("Desde");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel45.setText("Hasta");

        btnFiltarListadoPedido.setBackground(new java.awt.Color(229, 232, 232));
        btnFiltarListadoPedido.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        btnFiltarListadoPedido.setText("FILTRAR");
        btnFiltarListadoPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFiltarListadoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltarListadoPedidoActionPerformed(evt);
            }
        });

        dtDesdeListaPedido.setDateFormatString("yyyy-MM-dd");
        dtDesdeListaPedido.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        dtHastaListaPedido.setDateFormatString("yyyy-MM-dd");
        dtHastaListaPedido.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        javax.swing.GroupLayout backGround1Layout = new javax.swing.GroupLayout(backGround1);
        backGround1.setLayout(backGround1Layout);
        backGround1Layout.setHorizontalGroup(
            backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backGround1Layout.createSequentialGroup()
                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backGround1Layout.createSequentialGroup()
                        .addGap(405, 405, 405)
                        .addComponent(jLabel1))
                    .addGroup(backGround1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(backGround1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtBuscarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(164, 164, 164)
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dtDesdeListaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel45)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dtHastaListaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(btnFiltarListadoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        backGround1Layout.setVerticalGroup(
            backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backGround1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscarPedido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnFiltarListadoPedido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dtDesdeListaPedido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtHastaListaPedido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backGround1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backGround1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarPedidoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarPedidoFocusGained
        if (txtBuscarPedido.getText().equals("Buscar Pedido...")) {
            txtBuscarPedido.setText(null);
            txtBuscarPedido.requestFocus();
            removePlaceHolder(txtBuscarPedido);
        }
    }//GEN-LAST:event_txtBuscarPedidoFocusGained

    private void txtBuscarPedidoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarPedidoFocusLost
        if (txtBuscarPedido.getText().length() == 0) {
            addPlaceHolder(txtBuscarPedido);
            txtBuscarPedido.setText("Buscar Pedido...");
        }
    }//GEN-LAST:event_txtBuscarPedidoFocusLost

    private void txtBuscarPedidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPedidoKeyReleased
        String query = txtBuscarPedido.getText();
        filterConsulta(query);
    }//GEN-LAST:event_txtBuscarPedidoKeyReleased

    private void btnFiltarListadoPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltarListadoPedidoActionPerformed
        try {
            buscarEntreFechaConsulta();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnFiltarListadoPedidoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private PanelesBG.BackGround backGround1;
    private javax.swing.JButton btnFiltarListadoPedido;
    private com.toedter.calendar.JDateChooser dtDesdeListaPedido;
    private com.toedter.calendar.JDateChooser dtHastaListaPedido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblListadoPedidos;
    private javax.swing.JTextField txtBuscarPedido;
    // End of variables declaration//GEN-END:variables
}
