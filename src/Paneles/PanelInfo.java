package Paneles;

import DTO.DTOPedidoPorMes;
import DTO.DTOPedidoPorZona;
import GestorBD.GestorBD;
import Model.Conductor;
import Ventana.VentanaContacto;
import Ventana.VentanaTyC;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.general.DefaultPieDataset;

public class PanelInfo extends javax.swing.JPanel {

    GestorBD g;
    DefaultTableModel modeloPedidoPorZona = new DefaultTableModel();
    DefaultTableModel modeloPedidoPorMes = new DefaultTableModel();
    JFreeChart grafica;

    public PanelInfo() {
        initComponents();
        g = new GestorBD();
        //-----
        int cantidadContenedores = g.cantidadContenedoresDisponibles();
        lblContenedoresDisponibles.setText(String.valueOf(cantidadContenedores));
        cargarTablaConductor();
        setTableVisible(tblConductores, false);
        //--
        modeloPedidoPorZona.addColumn("Zona");
        modeloPedidoPorZona.addColumn("Pedidos");
        tblPedidosPorZona.setModel(modeloPedidoPorZona);
        cargarTablaPedidoPorZona();
        modeloPedidoPorMes.addColumn("Mes");
        modeloPedidoPorMes.addColumn("Nombre");
        modeloPedidoPorMes.addColumn("Cantidad");
        tblCantidadPedidoPorMes.setModel(modeloPedidoPorMes);
        cargarTablaPedidoPorMes();
        //-----
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); //Alinear Texto Centrado
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int x = 0; x < 5; x++) {
            tblConductores.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }
        tblConductores.getColumnModel().getColumn(0).setPreferredWidth(70);
        tblConductores.getColumnModel().getColumn(1).setPreferredWidth(40);
        tblConductores.getColumnModel().getColumn(2).setPreferredWidth(20);
        tblConductores.getColumnModel().getColumn(3).setPreferredWidth(40);
        tblConductores.getColumnModel().getColumn(4).setPreferredWidth(20);
    }

    private void cargarTablaPedidoPorZona() {
        ArrayList<DTOPedidoPorZona> lista = g.pedidosPorZona();
        for (DTOPedidoPorZona c : lista) {
            modeloPedidoPorZona.addRow(new Object[]{
                c.getZona(),
                c.getCant()
            });
        }

        tblPedidosPorZona.setModel(modeloPedidoPorZona);
    }

    private void cargarTablaPedidoPorMes() {
        ArrayList<DTOPedidoPorMes> lista = g.pedidosPorMes();
        for (DTOPedidoPorMes c : lista) {
            modeloPedidoPorMes.addRow(new Object[]{
                c.getMes(),
                c.getNombre(),
                c.getCantidad()
            });
        }
        tblCantidadPedidoPorMes.setModel(modeloPedidoPorMes);
    }

    private void cargarTablaConductor() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[]{"Conductor", "Nacimiento", "Dni", "Telefono", "Camion"});
        ArrayList<Conductor> lista = g.listaConductor();

        for (Conductor c : lista) {
            modelo.addRow(new Object[]{
                c.getNombreConductor(),
                c.getFechaNacimiento(),
                c.getDniConductor(),
                c.getTelefonoConductor(),
                c.getIdCamion()
            });
        }

        tblConductores.setModel(modelo);
    }

    public void setTableVisible(JTable table, boolean isVisible) {
        tblConductores.setVisible(isVisible);
        tblConductores.getTableHeader().setVisible(isVisible);
    }

    public void buscarEntreFechaPedidoPorZona() {
        int dia, mes, año;
        int dia2, mes2, año2;
        String fecha1, fecha2;

        dia = dtDesdePedidoPorZona.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes = dtDesdePedidoPorZona.getCalendar().get(Calendar.MONTH) + 1;
        año = dtDesdePedidoPorZona.getCalendar().get(Calendar.YEAR);
        fecha1 = año + "-" + mes + "-" + dia;
        dia2 = dtHastaPedidoPorZona.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes2 = dtHastaPedidoPorZona.getCalendar().get(Calendar.MONTH) + 1;
        año2 = dtHastaPedidoPorZona.getCalendar().get(Calendar.YEAR);
        fecha2 = año2 + "-" + mes2 + "-" + dia2;

        List<DTOPedidoPorZona> list = g.mostrarPedidosPorZonaDesdeHasta(fecha1, fecha2);
        modeloPedidoPorZona.setRowCount(0);
        for (DTOPedidoPorZona p : list) {
            modeloPedidoPorZona.addRow(new Object[]{p.getZona(), p.getCant()});
        }

    }

    public void buscarEntreFechaPedidoPorMes() {
        int dia, mes, año;
        int dia2, mes2, año2;
        String fecha1, fecha2;

        dia = dtDesdePedidosPorMes.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes = dtDesdePedidosPorMes.getCalendar().get(Calendar.MONTH) + 1;
        año = dtDesdePedidosPorMes.getCalendar().get(Calendar.YEAR);
        fecha1 = año + "-" + mes + "-" + dia;
        dia2 = dtHastaPedidosPorMes.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes2 = dtHastaPedidosPorMes.getCalendar().get(Calendar.MONTH) + 1;
        año2 = dtHastaPedidosPorMes.getCalendar().get(Calendar.YEAR);
        fecha2 = año2 + "-" + mes2 + "-" + dia2;

        List<DTOPedidoPorMes> list = g.mostrarPedidosPorMesDesdeHasta(fecha1, fecha2);
        modeloPedidoPorMes.setRowCount(0);
        for (DTOPedidoPorMes p : list) {
            modeloPedidoPorMes.addRow(new Object[]{p.getMes(), p.getNombre(), p.getCantidad()});
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backGround1 = new PanelesBG.BackGround();
        jLabel36 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblContenedoresDisponibles = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblConductores = new javax.swing.JTable();
        btnVerConductores = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        dtHastaPedidoPorZona = new com.toedter.calendar.JDateChooser();
        dtDesdePedidoPorZona = new com.toedter.calendar.JDateChooser();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblPedidosPorZona = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblCantidadPedidoPorMes = new javax.swing.JTable();
        dtHastaPedidosPorMes = new com.toedter.calendar.JDateChooser();
        dtDesdePedidosPorMes = new com.toedter.calendar.JDateChooser();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        btnFiltrarPedidosPorZona = new javax.swing.JButton();
        btnFiltrarPedidosPorMes = new javax.swing.JButton();
        panelGraficoZona = new javax.swing.JPanel();
        panelGraficoMes = new javax.swing.JPanel();
        btnGraficarZona = new javax.swing.JButton();
        btnGraficarPedidos = new javax.swing.JButton();
        lblTerminosYCondiciones = new javax.swing.JLabel();
        lblContacto = new javax.swing.JLabel();

        backGround1.setColor1(new java.awt.Color(255, 168, 11));
        backGround1.setColor2(new java.awt.Color(255, 168, 11));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Logo1.png"))); // NOI18N
        jLabel36.setText("jLabel35");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
        jLabel38.setText("PEDIDOS POR ZONA");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
        jLabel42.setText("CANTIDAD DE PEDIDOS POR MES");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setText("CONTENEDORES DISPONIBLES");

        lblContenedoresDisponibles.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblContenedoresDisponibles.setText("0");

        tblConductores.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblConductores.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane10.setViewportView(tblConductores);

        btnVerConductores.setBackground(new java.awt.Color(229, 232, 232));
        btnVerConductores.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        btnVerConductores.setText("VER CONDUCTORES");
        btnVerConductores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerConductoresActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel41.setText("Desde");

        jLabel50.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel50.setText("Hasta");

        dtHastaPedidoPorZona.setDateFormatString("yyyy-MM-dd");
        dtHastaPedidoPorZona.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        dtDesdePedidoPorZona.setDateFormatString("yyyy-MM-dd");
        dtDesdePedidoPorZona.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tblPedidosPorZona.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblPedidosPorZona.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Zona", "Pedidos"
            }
        ));
        jScrollPane9.setViewportView(tblPedidosPorZona);

        tblCantidadPedidoPorMes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblCantidadPedidoPorMes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mes", "Nombre", "Cantidad de Pedidos"
            }
        ));
        jScrollPane12.setViewportView(tblCantidadPedidoPorMes);

        dtHastaPedidosPorMes.setDateFormatString("yyyy-MM-dd");
        dtHastaPedidosPorMes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        dtDesdePedidosPorMes.setDateFormatString("yyyy-MM-dd");
        dtDesdePedidosPorMes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel52.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel52.setText("Desde");

        jLabel53.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel53.setText("Hasta");

        btnFiltrarPedidosPorZona.setBackground(new java.awt.Color(229, 232, 232));
        btnFiltrarPedidosPorZona.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        btnFiltrarPedidosPorZona.setText("FILTRAR");
        btnFiltrarPedidosPorZona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarPedidosPorZonaActionPerformed(evt);
            }
        });

        btnFiltrarPedidosPorMes.setBackground(new java.awt.Color(229, 232, 232));
        btnFiltrarPedidosPorMes.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        btnFiltrarPedidosPorMes.setText("FILTRAR");
        btnFiltrarPedidosPorMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarPedidosPorMesActionPerformed(evt);
            }
        });

        panelGraficoZona.setBackground(new java.awt.Color(221, 135, 2));
        panelGraficoZona.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelGraficoZonaLayout = new javax.swing.GroupLayout(panelGraficoZona);
        panelGraficoZona.setLayout(panelGraficoZonaLayout);
        panelGraficoZonaLayout.setHorizontalGroup(
            panelGraficoZonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 568, Short.MAX_VALUE)
        );
        panelGraficoZonaLayout.setVerticalGroup(
            panelGraficoZonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelGraficoMes.setBackground(new java.awt.Color(221, 135, 2));
        panelGraficoMes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelGraficoMes.setPreferredSize(new java.awt.Dimension(530, 210));

        javax.swing.GroupLayout panelGraficoMesLayout = new javax.swing.GroupLayout(panelGraficoMes);
        panelGraficoMes.setLayout(panelGraficoMesLayout);
        panelGraficoMesLayout.setHorizontalGroup(
            panelGraficoMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 588, Short.MAX_VALUE)
        );
        panelGraficoMesLayout.setVerticalGroup(
            panelGraficoMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        btnGraficarZona.setBackground(new java.awt.Color(229, 232, 232));
        btnGraficarZona.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        btnGraficarZona.setText("GRAFICAR");
        btnGraficarZona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficarZonaActionPerformed(evt);
            }
        });

        btnGraficarPedidos.setBackground(new java.awt.Color(229, 232, 232));
        btnGraficarPedidos.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        btnGraficarPedidos.setText("GRAFICAR");
        btnGraficarPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficarPedidosActionPerformed(evt);
            }
        });

        lblTerminosYCondiciones.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        lblTerminosYCondiciones.setText("TERMINOS Y CONDICIONES");
        lblTerminosYCondiciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTerminosYCondiciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTerminosYCondicionesMouseClicked(evt);
            }
        });

        lblContacto.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        lblContacto.setText("CONTACTO");
        lblContacto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblContacto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblContactoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout backGround1Layout = new javax.swing.GroupLayout(backGround1);
        backGround1.setLayout(backGround1Layout);
        backGround1Layout.setHorizontalGroup(
            backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backGround1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backGround1Layout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGraficarZona))
                    .addGroup(backGround1Layout.createSequentialGroup()
                        .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(backGround1Layout.createSequentialGroup()
                                .addComponent(jLabel50)
                                .addGap(18, 18, 18)
                                .addComponent(dtHastaPedidoPorZona, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(backGround1Layout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel38)
                                    .addComponent(dtDesdePedidoPorZona, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(26, 26, 26)
                        .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(backGround1Layout.createSequentialGroup()
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(backGround1Layout.createSequentialGroup()
                                        .addGap(53, 53, 53)
                                        .addComponent(lblTerminosYCondiciones))
                                    .addGroup(backGround1Layout.createSequentialGroup()
                                        .addGap(139, 139, 139)
                                        .addComponent(lblContacto))))
                            .addGroup(backGround1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btnFiltrarPedidosPorZona)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(backGround1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(backGround1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(lblContenedoresDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(btnVerConductores))
                    .addComponent(panelGraficoZona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backGround1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(backGround1Layout.createSequentialGroup()
                                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel53, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dtDesdePedidosPorMes, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(backGround1Layout.createSequentialGroup()
                                        .addComponent(dtHastaPedidosPorMes, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnFiltrarPedidosPorMes))))
                            .addGroup(backGround1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGraficarPedidos))
                            .addComponent(jLabel42))
                        .addGap(170, 170, 170))
                    .addGroup(backGround1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelGraficoMes, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        backGround1Layout.setVerticalGroup(
            backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backGround1Layout.createSequentialGroup()
                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backGround1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel38)
                        .addGap(18, 18, 18)
                        .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(backGround1Layout.createSequentialGroup()
                                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(dtDesdePedidoPorZona, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel41))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dtHastaPedidoPorZona, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backGround1Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel50))))
                            .addComponent(btnFiltrarPedidosPorZona))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGraficarZona))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(backGround1Layout.createSequentialGroup()
                        .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(backGround1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(backGround1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(lblTerminosYCondiciones)
                                .addGap(18, 18, 18)
                                .addComponent(lblContacto)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel42)
                        .addGap(18, 18, 18)
                        .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(backGround1Layout.createSequentialGroup()
                                .addComponent(dtDesdePedidosPorMes, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dtHastaPedidosPorMes, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(backGround1Layout.createSequentialGroup()
                                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(backGround1Layout.createSequentialGroup()
                                        .addComponent(jLabel52)
                                        .addGap(21, 21, 21)
                                        .addComponent(jLabel53))
                                    .addComponent(btnFiltrarPedidosPorMes))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGraficarPedidos))))
                        .addGap(6, 6, 6)))
                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelGraficoMes, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                    .addComponent(panelGraficoZona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblContenedoresDisponibles)
                        .addComponent(jLabel14)
                        .addComponent(btnVerConductores))
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(backGround1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backGround1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerConductoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerConductoresActionPerformed
        setTableVisible(tblConductores, true);
    }//GEN-LAST:event_btnVerConductoresActionPerformed

    private void btnFiltrarPedidosPorZonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarPedidosPorZonaActionPerformed
        try {
            buscarEntreFechaPedidoPorZona();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnFiltrarPedidosPorZonaActionPerformed

    private void btnFiltrarPedidosPorMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarPedidosPorMesActionPerformed
        try {
            buscarEntreFechaPedidoPorMes();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnFiltrarPedidosPorMesActionPerformed

    private void btnGraficarZonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficarZonaActionPerformed

//        DefaultPieDataset dtsc = new DefaultPieDataset();
//        for (int i = 0; i < tblPedidosPorZona.getRowCount(); i++) {
//            dtsc.setValue(tblPedidosPorZona.getValueAt(i, 0).toString(), Integer.parseInt(tblPedidosPorZona.getValueAt(i, 1).toString()));
//        }
//        JFreeChart ch = ChartFactory.createPieChart("Pedidos Por Zona", dtsc, true, true, false);
//
//        ChartFrame frame = new ChartFrame("Pedidos Por Mes", ch);
//        frame.setVisible(true);
//        frame.setSize(460, 400);
//        frame.setBounds(830, 195, 530, 220);
//        frame.setResizable(false);
        DefaultPieDataset dtsc = new DefaultPieDataset();
        for (int i = 0; i < tblPedidosPorZona.getRowCount(); i++) {
            dtsc.setValue(tblPedidosPorZona.getValueAt(i, 0).toString(), Integer.parseInt(tblPedidosPorZona.getValueAt(i, 1).toString()));
        }
        JFreeChart ch = ChartFactory.createPieChart("Pedidos Por Zona", dtsc, true, true, false);
        ChartPanel cp = new ChartPanel(ch);
        add(cp);
        cp.setBounds(36, 425, 568, 280);
        cp.setMouseWheelEnabled(true);
    }//GEN-LAST:event_btnGraficarZonaActionPerformed

    private void btnGraficarPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficarPedidosActionPerformed
//        DefaultPieDataset dtsc = new DefaultPieDataset();
//        for (int i = 0; i < tblCantidadPedidoPorMes.getRowCount(); i++) {
//            dtsc.setValue(tblCantidadPedidoPorMes.getValueAt(i, 1).toString(), Integer.parseInt(tblCantidadPedidoPorMes.getValueAt(i, 2).toString()));
//        }
//        JFreeChart ch = ChartFactory.createPieChart("Pedidos Por Mes", dtsc, true, true, false);
//
//        ChartFrame frame = new ChartFrame("Pedidos Por Mes", ch);
//        frame.setVisible(true);
//        frame.setSize(460, 400);
//        frame.setBounds(830, 450, 530, 220);
//        frame.setResizable(false);

        DefaultPieDataset dtsc = new DefaultPieDataset();
        for (int i = 0; i < tblCantidadPedidoPorMes.getRowCount(); i++) {
            dtsc.setValue(tblCantidadPedidoPorMes.getValueAt(i, 1).toString(), Integer.parseInt(tblCantidadPedidoPorMes.getValueAt(i, 2).toString()));
        }
        JFreeChart ch = ChartFactory.createPieChart("Pedidos Por Mes", dtsc, true, true, false);
        ChartPanel cp = new ChartPanel(ch);
        add(cp);
        cp.setBounds(632, 425, 588, 280);
        cp.setMouseWheelEnabled(true);


    }//GEN-LAST:event_btnGraficarPedidosActionPerformed

    private void lblTerminosYCondicionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTerminosYCondicionesMouseClicked
        VentanaTyC vtyc = new VentanaTyC();
        vtyc.setVisible(true);

    }//GEN-LAST:event_lblTerminosYCondicionesMouseClicked

    private void lblContactoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblContactoMouseClicked
        VentanaContacto vc = new VentanaContacto();
        vc.setVisible(true);
    }//GEN-LAST:event_lblContactoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private PanelesBG.BackGround backGround1;
    private javax.swing.JButton btnFiltrarPedidosPorMes;
    private javax.swing.JButton btnFiltrarPedidosPorZona;
    private javax.swing.JButton btnGraficarPedidos;
    private javax.swing.JButton btnGraficarZona;
    private javax.swing.JButton btnVerConductores;
    private com.toedter.calendar.JDateChooser dtDesdePedidoPorZona;
    private com.toedter.calendar.JDateChooser dtDesdePedidosPorMes;
    private com.toedter.calendar.JDateChooser dtHastaPedidoPorZona;
    private com.toedter.calendar.JDateChooser dtHastaPedidosPorMes;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblContacto;
    private javax.swing.JLabel lblContenedoresDisponibles;
    private javax.swing.JLabel lblTerminosYCondiciones;
    private javax.swing.JPanel panelGraficoMes;
    private javax.swing.JPanel panelGraficoZona;
    private javax.swing.JTable tblCantidadPedidoPorMes;
    private javax.swing.JTable tblConductores;
    private javax.swing.JTable tblPedidosPorZona;
    // End of variables declaration//GEN-END:variables
}
