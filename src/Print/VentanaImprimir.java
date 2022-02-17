package Print;

import DTO.*;
import DTO.DTORemito;
import GestorBD.GestorBD;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class VentanaImprimir extends javax.swing.JFrame {

    private String CONN = "jdbc:sqlserver://localhost:1433;databaseName=Contegen";
    private String USER = "sa";
    private String PASS = "123456";
    private Connection con;
    DefaultTableModel modeloRemito = new DefaultTableModel();
    GestorBD g;

    public VentanaImprimir() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        g = new GestorBD();
        btnGenerarRecibo.setEnabled(false);
        btnImprimir.setEnabled(false);
        area.setEditable(false);
//-----
        modeloRemito.addColumn("Remito");
        modeloRemito.addColumn("Direccion");
        modeloRemito.addColumn("Fecha");
        modeloRemito.addColumn("FormaPago");
        modeloRemito.addColumn("Dias");
        modeloRemito.addColumn("Contenedor");
        modeloRemito.addColumn("Cantidad");
        modeloRemito.addColumn("Pedido");
        modeloRemito.addColumn("Camion");
        modeloRemito.addColumn("Importe");
        tblRemito.setModel(modeloRemito);
        cargarTablaRemito();
        //----
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); //Alinear Texto Centrado
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int x = 0; x < 10; x++) {
            tblRemito.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }
        tblRemito.getColumnModel().getColumn(0).setPreferredWidth(15);
        tblRemito.getColumnModel().getColumn(1).setPreferredWidth(60);
        tblRemito.getColumnModel().getColumn(2).setPreferredWidth(40);
        tblRemito.getColumnModel().getColumn(3).setPreferredWidth(45);
        tblRemito.getColumnModel().getColumn(4).setPreferredWidth(10);
        tblRemito.getColumnModel().getColumn(5).setPreferredWidth(40);
        tblRemito.getColumnModel().getColumn(6).setPreferredWidth(20);
        tblRemito.getColumnModel().getColumn(7).setPreferredWidth(10);
        tblRemito.getColumnModel().getColumn(8).setPreferredWidth(10);
        tblRemito.getColumnModel().getColumn(9).setPreferredWidth(30);
    }

    private void cargarTablaRemito() {
        ArrayList<DTOImprimir> lista = g.tablaRemitoimprimir();

        for (DTOImprimir c : lista) {
            modeloRemito.addRow(new Object[]{
                c.getIdRemito(),
                c.getDirecciom(),
                c.getFecha(),
                c.getPago(),
                c.getDias(),
                c.getCodigo(),
                c.getCantidad(),
                c.getIdPedido(),
                c.getCamion(),
                c.getImporte()});
        }
        tblRemito.setModel(modeloRemito);
    }

    private void filtro(String query) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modeloRemito);
        tblRemito.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarRemito.getText(), 0));
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

    public void limpiar() {
        txtRemito.setText("");
        txtPedido.setText("");
        txtDireccion.setText("");
        txtCodContenedor.setText("");
        txtDias.setText("");
        txtCantidad.setText("");
        txtImporte.setText("");
        cboCamion.setSelectedIndex(0);
        cboFormaPago.setSelectedIndex(0);
        txtFecha.setText("");
        area.setText("");
        btnImprimir.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backGround1 = new PanelesBG.BackGround();
        txtRemito = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        cboFormaPago = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDias = new javax.swing.JTextField();
        txtCodContenedor = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtPedido = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboCamion = new javax.swing.JComboBox<>();
        txtImporte = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        area = new javax.swing.JTextArea();
        btnLimpiar = new javax.swing.JButton();
        btnGenerarRecibo = new javax.swing.JButton();
        txtBuscarRemito = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblVolver1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRemito = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        backGround1.setColor1(new java.awt.Color(151, 214, 185));
        backGround1.setColor2(new java.awt.Color(151, 214, 185));

        txtRemito.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel9.setText("Remito");

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel2.setText("Direccion");

        txtDireccion.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });

        txtFecha.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N

        cboFormaPago.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        cboFormaPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccionar--", "Tarjeta Debito", "Tarjeta Credito", "Contado", "Transferencia", "Cheque" }));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel4.setText("Forma de Pago");

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel3.setText("Fecha");

        txtDias.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N

        txtCodContenedor.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txtCodContenedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodContenedorKeyTyped(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel11.setText("Codigo Contenedor");

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel5.setText("Dias");

        txtCantidad.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel12.setText("Cantidad de Contenedores");

        txtPedido.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txtPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPedidoKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel7.setText("Pedido");

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel6.setText("Camion");

        cboCamion.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        cboCamion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccionar--", "1", "2" }));

        txtImporte.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txtImporte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtImporteKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel8.setText("Importe");

        btnImprimir.setBackground(new java.awt.Color(229, 232, 232));
        btnImprimir.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\Print.png")); // NOI18N
        btnImprimir.setText("IMPRIMIR");
        btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImprimir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnImprimir.setIconTextGap(12);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        area.setColumns(20);
        area.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        area.setRows(5);
        jScrollPane2.setViewportView(area);

        btnLimpiar.setBackground(new java.awt.Color(229, 232, 232));
        btnLimpiar.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnGenerarRecibo.setBackground(new java.awt.Color(229, 232, 232));
        btnGenerarRecibo.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        btnGenerarRecibo.setText("GENERAR RECIBO");
        btnGenerarRecibo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGenerarRecibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReciboActionPerformed(evt);
            }
        });

        txtBuscarRemito.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtBuscarRemito.setForeground(new java.awt.Color(204, 204, 204));
        txtBuscarRemito.setText("Buscar Remito...");
        txtBuscarRemito.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarRemitoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarRemitoFocusLost(evt);
            }
        });
        txtBuscarRemito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarRemitoKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel1.setText("IMPRIMIR REMITO");

        lblVolver1.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        lblVolver1.setText("<-Volver");
        lblVolver1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblVolver1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVolver1MouseClicked(evt);
            }
        });

        tblRemito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblRemito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRemitoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblRemito);

        jLabel10.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\search.png")); // NOI18N

        javax.swing.GroupLayout backGround1Layout = new javax.swing.GroupLayout(backGround1);
        backGround1.setLayout(backGround1Layout);
        backGround1Layout.setHorizontalGroup(
            backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backGround1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(352, 352, 352)
                .addComponent(lblVolver1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backGround1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backGround1Layout.createSequentialGroup()
                        .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel11)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(84, 84, 84)
                        .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtDias, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCodContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRemito, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(backGround1Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboCamion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, backGround1Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, backGround1Layout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addGap(26, 26, 26)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(backGround1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(179, 179, 179)
                        .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backGround1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(93, 93, 93))
                    .addGroup(backGround1Layout.createSequentialGroup()
                        .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(backGround1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBuscarRemito, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(backGround1Layout.createSequentialGroup()
                                .addComponent(btnLimpiar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGenerarRecibo))
                            .addGroup(backGround1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnImprimir)))
                        .addContainerGap(129, Short.MAX_VALUE))))
        );
        backGround1Layout.setVerticalGroup(
            backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backGround1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblVolver1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarRemito, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backGround1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(backGround1Layout.createSequentialGroup()
                                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnLimpiar)
                                    .addComponent(btnGenerarRecibo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnImprimir)))
                    .addGroup(backGround1Layout.createSequentialGroup()
                        .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtRemito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cboFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtCodContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(36, 36, 36)
                        .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cboCamion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtImporte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap(129, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backGround1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backGround1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarRemitoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarRemitoKeyReleased
        //        BuscarRemito(txtBuscarRemito.getText());
        String query = txtBuscarRemito.getText();
        filtro(query);
    }//GEN-LAST:event_txtBuscarRemitoKeyReleased

    private void txtBuscarRemitoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarRemitoFocusLost
        if (txtBuscarRemito.getText().length() == 0) {
            addPlaceHolder(txtBuscarRemito);
            txtBuscarRemito.setText("Buscar Remito...");
        }
    }//GEN-LAST:event_txtBuscarRemitoFocusLost

    private void txtBuscarRemitoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarRemitoFocusGained
        if (txtBuscarRemito.getText().equals("Buscar Remito...")) {
            txtBuscarRemito.setText(null);
            txtBuscarRemito.requestFocus();
            removePlaceHolder(txtBuscarRemito);
        }
    }//GEN-LAST:event_txtBuscarRemitoFocusGained

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        int response = JOptionPane.showConfirmDialog(this, "Desea Imprimir el Comprobate?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            try {
                area.print();
            } catch (Exception e) {
            }
        } else if (response == JOptionPane.NO_OPTION) {
        }

    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        btnGenerarRecibo.setEnabled(false);
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        int key = evt.getKeyChar();
        boolean numero = key >= 48 && key <= 57;
        if (!numero) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtCodContenedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodContenedorKeyTyped
        int key = evt.getKeyChar();
        boolean numero = key >= 48 && key <= 57;
        if (!numero) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCodContenedorKeyTyped

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        int key = evt.getKeyChar();
        boolean numero = key >= 48 && key <= 57;
        if (!numero) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtPedidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPedidoKeyTyped
        int key = evt.getKeyChar();
        boolean numero = key >= 48 && key <= 57;
        if (!numero) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPedidoKeyTyped

    private void txtImporteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteKeyTyped
        int key = evt.getKeyChar();
        boolean numero = key >= 48 && key <= 57;
        if (!numero) {
            evt.consume();
        }
    }//GEN-LAST:event_txtImporteKeyTyped

    private void tblRemitoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRemitoMouseClicked
        btnGenerarRecibo.setEnabled(true);
        try {
            int selectedRow = tblRemito.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tblRemito.getModel();

            txtRemito.setText(model.getValueAt(selectedRow, 0).toString());
            txtDireccion.setText(model.getValueAt(selectedRow, 1).toString());
//            Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(selectedRow, 2));
//            dtFecha.setDate(fecha);
            txtFecha.setText(model.getValueAt(selectedRow, 2).toString());
            String pago = model.getValueAt(selectedRow, 3).toString();
            switch (pago) {
                case "--Seleccionar--":
                    cboFormaPago.setSelectedIndex(0);
                    break;
                case "Tarjeta Debito":
                    cboFormaPago.setSelectedIndex(1);
                    break;
                case "Tarjeta Credito":
                    cboFormaPago.setSelectedIndex(2);
                    break;
                case "Contado":
                    cboFormaPago.setSelectedIndex(3);
                    break;
                case "Transferencia":
                    cboFormaPago.setSelectedIndex(4);
                    break;
                case "Cheque":
                    cboFormaPago.setSelectedIndex(5);
                    break;
            }
            txtDias.setText(model.getValueAt(selectedRow, 4).toString());
            txtCodContenedor.setText(model.getValueAt(selectedRow, 5).toString());
            txtCantidad.setText(model.getValueAt(selectedRow, 6).toString());
            txtPedido.setText(model.getValueAt(selectedRow, 7).toString());
            String camion = model.getValueAt(selectedRow, 8).toString();
            switch (camion) {
                case "--Seleccionar--":
                    cboCamion.setSelectedIndex(0);
                    break;
                case "1":
                    cboCamion.setSelectedIndex(1);
                    break;
                case "2":
                    cboCamion.setSelectedIndex(2);
                    break;
            }
            txtImporte.setText(model.getValueAt(selectedRow, 9).toString());

        } catch (Exception e) {
        }
    }//GEN-LAST:event_tblRemitoMouseClicked

    private void lblVolver1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVolver1MouseClicked
        dispose();
    }//GEN-LAST:event_lblVolver1MouseClicked

    private void btnGenerarReciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReciboActionPerformed
        btnImprimir.setEnabled(true);
        area.setText("***************************************\n");
        area.setText(area.getText() + "*              ConteGen               *\n");
        area.setText(area.getText() + "***************************************\n");
        area.setText(area.getText() + "Remito: " + txtRemito.getText() + "\n");
        area.setText(area.getText() + "Pedido: " + txtPedido.getText() + "\n");
        area.setText(area.getText() + "***************************************\n");
        area.setText(area.getText() + "Direccion: " + txtDireccion.getText() + "\n");
        area.setText(area.getText() + "Fecha de Entrega: " + txtFecha.getText() + "\n");
        area.setText(area.getText() + "Codigo de Contenedor: " + txtCodContenedor.getText() + "\n");
        area.setText(area.getText() + "Dias: " + txtDias.getText() + "\n");
        area.setText(area.getText() + "Cantidad de Contenedores: " + txtCantidad.getText() + "\n");
        area.setText(area.getText() + "Camion: " + cboCamion.getSelectedItem() + "\n");
        area.setText(area.getText() + "Forma de Pago: " + cboFormaPago.getSelectedItem() + "\n");
        area.setText(area.getText() + "Importe: " + txtImporte.getText() + "\n");
        area.setText(area.getText() + "***************************************\n");
        area.setText(area.getText() + "     GRACIAS POR CONTAR CON NOSOTROS   \n");
        area.setText(area.getText() + "***************************************\n");
        area.setText(area.getText() + "          CONTACTO: 351 7181817        \n");
        area.setText(area.getText() + "        Aviador Pettirossi 1726        \n");
        area.setText(area.getText() + "          Córdoba - Argentina          \n");
        area.setText(area.getText() + "***************************************\n");


    }//GEN-LAST:event_btnGenerarReciboActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaImprimir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaImprimir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaImprimir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaImprimir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaImprimir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area;
    private PanelesBG.BackGround backGround1;
    private javax.swing.JButton btnGenerarRecibo;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cboCamion;
    private javax.swing.JComboBox<String> cboFormaPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblVolver1;
    private javax.swing.JTable tblRemito;
    private javax.swing.JTextField txtBuscarRemito;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodContenedor;
    private javax.swing.JTextField txtDias;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtImporte;
    private javax.swing.JTextField txtPedido;
    private javax.swing.JTextField txtRemito;
    // End of variables declaration//GEN-END:variables
}
