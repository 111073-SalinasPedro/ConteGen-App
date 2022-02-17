package Paneles;

import DTO.DTOPedido;
import GestorBD.GestorBD;
import Model.*;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class PanelPedidos extends javax.swing.JPanel {

    GestorBD g;
    private Connection con;
    private String CONN = "jdbc:sqlserver://localhost:1433;databaseName=Contegen";
    private String USER = "sa";
    private String PASS = "123456";
    PreparedStatement pst = null;
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modeloEliminar = new DefaultTableModel();

    public PanelPedidos() {
        initComponents();
        g = new GestorBD();
        panelAgregarPedido.setVisible(true);
        panelModificarPedido.setVisible(false);
        panelEliminarPedido.setVisible(false);
        btnModificar.setEnabled(false);
        btnFinalizarPedido.setEnabled(false);
        btnEliminar.setEnabled(false);
        txtIdPedido.setEditable(false);
        txtIdEliminarPedido.setEditable(false);
        cargarCliente();
        cargarEstado();
        cargarZona();
        cboCliente.setSelectedItem("");
        AutoCompleteDecorator.decorate(cboCliente);
        //---
        modelo.addColumn("id");
        modelo.addColumn("Direccion");
        modelo.addColumn("Barrio");
        modelo.addColumn("Zona");
        modelo.addColumn("Fecha");
        modelo.addColumn("Horario");
        modelo.addColumn("Dias");
        modelo.addColumn("Contenedores");
        modelo.addColumn("Observaciones");
        modelo.addColumn("Estado");
        tblListadoModificarPedido.setModel(modelo);
        cargarTablaPedidos();
        cargarZonaModificar();
        cargarEstadoModificar();
        //----
        modeloEliminar.addColumn("id");
        modeloEliminar.addColumn("Direccion");
        modeloEliminar.addColumn("Barrio");
        modeloEliminar.addColumn("Zona");
        modeloEliminar.addColumn("Fecha");
        modeloEliminar.addColumn("Horario");
        modeloEliminar.addColumn("Dias");
        modeloEliminar.addColumn("Contenedores");
        modeloEliminar.addColumn("Observaciones");
        modeloEliminar.addColumn("Estado");
        tblListadoEliminarPedido.setModel(modeloEliminar);
        cargarTablaEliminarPedidos();
        txtDireccionEliminar.setEditable(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); //Alinear Texto Centrado
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int x = 0; x < 10; x++) {
            tblListadoModificarPedido.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }
        tblListadoModificarPedido.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblListadoModificarPedido.getColumnModel().getColumn(1).setPreferredWidth(70);
        tblListadoModificarPedido.getColumnModel().getColumn(2).setPreferredWidth(60);
        tblListadoModificarPedido.getColumnModel().getColumn(3).setPreferredWidth(15);
        tblListadoModificarPedido.getColumnModel().getColumn(4).setPreferredWidth(40);
        tblListadoModificarPedido.getColumnModel().getColumn(5).setPreferredWidth(40);
        tblListadoModificarPedido.getColumnModel().getColumn(6).setPreferredWidth(15);
        tblListadoModificarPedido.getColumnModel().getColumn(7).setPreferredWidth(35);
        tblListadoModificarPedido.getColumnModel().getColumn(8).setPreferredWidth(40);
        tblListadoModificarPedido.getColumnModel().getColumn(9).setPreferredWidth(30);

        for (int x = 0; x < 10; x++) {
            tblListadoEliminarPedido.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }
        tblListadoEliminarPedido.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblListadoEliminarPedido.getColumnModel().getColumn(1).setPreferredWidth(70);
        tblListadoEliminarPedido.getColumnModel().getColumn(2).setPreferredWidth(60);
        tblListadoEliminarPedido.getColumnModel().getColumn(3).setPreferredWidth(15);
        tblListadoEliminarPedido.getColumnModel().getColumn(4).setPreferredWidth(40);
        tblListadoEliminarPedido.getColumnModel().getColumn(5).setPreferredWidth(40);
        tblListadoEliminarPedido.getColumnModel().getColumn(6).setPreferredWidth(15);
        tblListadoEliminarPedido.getColumnModel().getColumn(7).setPreferredWidth(35);
        tblListadoEliminarPedido.getColumnModel().getColumn(8).setPreferredWidth(40);
        tblListadoEliminarPedido.getColumnModel().getColumn(9).setPreferredWidth(30);

        addPlaceHolder(txtBuscarModificarPedido);
        removePlaceHolder(txtBuscarModificarPedido);
    }
    //--Agregar Pedido

    private void cargarCliente() {
        ArrayList<Cliente> cliente = g.obtenerTodosLosClientes();

        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        for (Cliente p : cliente) {
            modelo.addElement(p);
        }
        cboCliente.setModel(modelo);
    }

    private void cargarEstado() {
        ArrayList<Estado> estado = g.obtenerTodosLosEstados();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addElement("Seleccionar");
        for (Estado p : estado) {
            modelo.addElement(p);
        }
        cboEstado.setModel(modelo);
    }

    private void cargarZona() {
        ArrayList<Zona> zona = g.obtenerTodosLasZonas();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addElement("Seleccionar");
        for (Zona p : zona) {
            modelo.addElement(p);
        }
        cboZona.setModel(modelo);
    }

    //----ModificarPedido
    private void abrirConexion() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            con = DriverManager.getConnection(CONN, USER, PASS);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void cargarTablaPedidos() {
        ArrayList<DTOPedido> lista = g.listadoPedido();
        modelo.setRowCount(0);

        for (DTOPedido c : lista) {
            modelo.addRow(new Object[]{
                c.getIdPedido(),
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
        tblListadoModificarPedido.setModel(modelo);
    }

    private void cargarZonaModificar() {
        ArrayList<Zona> zona = g.obtenerTodosLasZonas();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addElement("Seleccionar");
        for (Zona p : zona) {
            modelo.addElement(p);
        }
        cboZona1.setModel(modelo);
    }

    private void cargarEstadoModificar() {
        ArrayList<Estado> estado = g.obtenerTodosLosEstados();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addElement("Seleccionar");
        for (Estado p : estado) {
            modelo.addElement(p);
        }
        cboEstado1.setModel(modelo);
    }

    public void buscarEntreFecha() {
        int dia, mes, año;
        int dia2, mes2, año2;
        String fecha1, fecha2;

        dia = dtDesde.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes = dtDesde.getCalendar().get(Calendar.MONTH) + 1;
        año = dtDesde.getCalendar().get(Calendar.YEAR);
        fecha1 = dia + "-" + mes + "-" + año;
        dia2 = dtHasta.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes2 = dtHasta.getCalendar().get(Calendar.MONTH) + 1;
        año2 = dtHasta.getCalendar().get(Calendar.YEAR);
        fecha2 = dia2 + "-" + mes2 + "-" + año2;

        List<DTOPedido> list = g.mostrarPedidoModificarDesdeHasta(fecha1, fecha2);
        modelo.setRowCount(0);
        for (DTOPedido p : list) {
            modelo.addRow(new Object[]{p.getIdPedido(), p.getDireccion(), p.getBarrio(), p.getZona(),
                p.getFechaEntrega(), p.getHorario(), p.getDias(), p.getCant(), p.getObservaciones(),
                p.getEstado()});
        }
    }

    private void filter(String query) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modelo);
        tblListadoModificarPedido.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarModificarPedido.getText(), 0));
    }

    //---Eliminar Pedido
    public void buscarEntreFechaEliminar() {
        int dia, mes, año;
        int dia2, mes2, año2;
        String fecha1, fecha2;

        dia = dtDesde1.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes = dtDesde1.getCalendar().get(Calendar.MONTH) + 1;
        año = dtDesde1.getCalendar().get(Calendar.YEAR);
        fecha1 = dia + "-" + mes + "-" + año;
        dia2 = dtHasta1.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes2 = dtHasta1.getCalendar().get(Calendar.MONTH) + 1;
        año2 = dtHasta1.getCalendar().get(Calendar.YEAR);
        fecha2 = dia2 + "-" + mes2 + "-" + año2;

        List<DTOPedido> list = g.mostrarPedidoModificarDesdeHasta(fecha1, fecha2);
        modeloEliminar.setRowCount(0);
        for (DTOPedido p : list) {
            modeloEliminar.addRow(new Object[]{p.getIdPedido(), p.getDireccion(), p.getBarrio(), p.getZona(),
                p.getFechaEntrega(), p.getHorario(), p.getDias(), p.getCant(), p.getObservaciones(),
                p.getEstado()});
        }
    }

    private void cargarTablaEliminarPedidos() {
        ArrayList<DTOPedido> lista = g.listadoPedido();
        modeloEliminar.setRowCount(0);
        for (DTOPedido c : lista) {
            modeloEliminar.addRow(new Object[]{
                c.getIdPedido(),
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
        tblListadoEliminarPedido.setModel(modeloEliminar);
    }

    private void filterEliminarPedido(String query) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modelo);
        tblListadoEliminarPedido.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarEliminarPedido.getText(), 0));
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
        textField.setForeground(Color.GRAY);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelOpciones = new javax.swing.JPanel();
        backGround2 = new PanelesBG.BackGround();
        lblAgregarPedido = new javax.swing.JLabel();
        backGround3 = new PanelesBG.BackGround();
        lblModificarPedido = new javax.swing.JLabel();
        backGround4 = new PanelesBG.BackGround();
        lblEliminarPedido = new javax.swing.JLabel();
        panelBg = new javax.swing.JPanel();
        panelAgregarPedido = new PanelesBG.BackGround();
        jLabel27 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        cboCliente = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cboZona = new javax.swing.JComboBox<>();
        txtBarrio = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        dtFechaEntrega = new com.toedter.calendar.JDateChooser();
        cboRangoHorarioEntrega = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtDias = new javax.swing.JTextField();
        txtCantidadCont = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        cboEstado = new javax.swing.JComboBox<>();
        btnCargarPedido = new javax.swing.JButton();
        panelModificarPedido = new PanelesBG.BackGround();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtIdPedido = new javax.swing.JTextField();
        txtDireccion1 = new javax.swing.JTextField();
        txtBarrio1 = new javax.swing.JTextField();
        cboEstado1 = new javax.swing.JComboBox();
        dtFechaEntrega1 = new com.toedter.calendar.JDateChooser();
        cboHorario = new javax.swing.JComboBox();
        txtDias1 = new javax.swing.JTextField();
        txtContenedores = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservaciones1 = new javax.swing.JTextArea();
        cboZona1 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListadoModificarPedido = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        dtDesde = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        dtHasta = new com.toedter.calendar.JDateChooser();
        txtBuscarModificarPedido = new javax.swing.JTextField();
        btnFiltrar = new javax.swing.JButton();
        btnFinalizarPedido = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        panelEliminarPedido = new PanelesBG.BackGround();
        jLabel21 = new javax.swing.JLabel();
        txtIdEliminarPedido = new javax.swing.JTextField();
        txtDireccionEliminar = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblListadoEliminarPedido = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        txtBuscarEliminarPedido = new javax.swing.JTextField();
        dtDesde1 = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        dtHasta1 = new com.toedter.calendar.JDateChooser();
        btnFiltrar1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        panelOpciones.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        backGround2.setColor1(new java.awt.Color(102, 102, 102));
        backGround2.setColor2(new java.awt.Color(92, 184, 92));

        lblAgregarPedido.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        lblAgregarPedido.setForeground(new java.awt.Color(255, 255, 255));
        lblAgregarPedido.setText("AGREGAR PEDIDO");
        lblAgregarPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAgregarPedido.setIconTextGap(10);
        lblAgregarPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAgregarPedidoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout backGround2Layout = new javax.swing.GroupLayout(backGround2);
        backGround2.setLayout(backGround2Layout);
        backGround2Layout.setHorizontalGroup(
            backGround2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backGround2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAgregarPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addContainerGap())
        );
        backGround2Layout.setVerticalGroup(
            backGround2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backGround2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAgregarPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        backGround3.setColor1(new java.awt.Color(102, 102, 102));
        backGround3.setColor2(new java.awt.Color(240, 173, 78));

        lblModificarPedido.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        lblModificarPedido.setForeground(new java.awt.Color(255, 255, 255));
        lblModificarPedido.setText("MODIFICAR PEDIDO");
        lblModificarPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblModificarPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblModificarPedidoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout backGround3Layout = new javax.swing.GroupLayout(backGround3);
        backGround3.setLayout(backGround3Layout);
        backGround3Layout.setHorizontalGroup(
            backGround3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backGround3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblModificarPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                .addContainerGap())
        );
        backGround3Layout.setVerticalGroup(
            backGround3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backGround3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblModificarPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        backGround4.setColor1(new java.awt.Color(102, 102, 102));
        backGround4.setColor2(new java.awt.Color(217, 83, 79));

        lblEliminarPedido.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        lblEliminarPedido.setForeground(new java.awt.Color(255, 255, 255));
        lblEliminarPedido.setText("ELIMINAR PEDIDO");
        lblEliminarPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblEliminarPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEliminarPedidoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout backGround4Layout = new javax.swing.GroupLayout(backGround4);
        backGround4.setLayout(backGround4Layout);
        backGround4Layout.setHorizontalGroup(
            backGround4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backGround4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEliminarPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );
        backGround4Layout.setVerticalGroup(
            backGround4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backGround4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEliminarPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelOpcionesLayout = new javax.swing.GroupLayout(panelOpciones);
        panelOpciones.setLayout(panelOpcionesLayout);
        panelOpcionesLayout.setHorizontalGroup(
            panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcionesLayout.createSequentialGroup()
                .addComponent(backGround2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backGround3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backGround4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelOpcionesLayout.setVerticalGroup(
            panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backGround4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(backGround2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(backGround3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelAgregarPedido.setColor1(new java.awt.Color(255, 168, 11));
        panelAgregarPedido.setColor2(new java.awt.Color(255, 168, 11));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel27.setText("Cliente");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel26.setText("Direccion");

        txtDireccion.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        cboCliente.setEditable(true);
        cboCliente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel20.setText("Barrio");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setText("Zona");

        cboZona.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtBarrio.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setText("Fecha de Entrega");

        dtFechaEntrega.setDateFormatString("yyyy-MM-dd");
        dtFechaEntrega.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        cboRangoHorarioEntrega.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cboRangoHorarioEntrega.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08:00 - 10:00", "10:00 - 12:00", "14:00 - 16:00", "16:00 - 18:00" }));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setText("Horario de Entrega");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel28.setText("Dias");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel29.setText("Cantidad de Contenedores");

        txtDias.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        txtDias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiasKeyTyped(evt);
            }
        });

        txtCantidadCont.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        txtCantidadCont.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadContKeyTyped(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel30.setText("Observaciones");

        txtObservaciones.setColumns(20);
        txtObservaciones.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtObservaciones.setRows(5);
        jScrollPane8.setViewportView(txtObservaciones);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel16.setText("Estado");

        cboEstado.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        btnCargarPedido.setBackground(new java.awt.Color(102, 255, 102));
        btnCargarPedido.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        btnCargarPedido.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\add_pedido.png")); // NOI18N
        btnCargarPedido.setText("CARGAR PEDIDO");
        btnCargarPedido.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCargarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarPedidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAgregarPedidoLayout = new javax.swing.GroupLayout(panelAgregarPedido);
        panelAgregarPedido.setLayout(panelAgregarPedidoLayout);
        panelAgregarPedidoLayout.setHorizontalGroup(
            panelAgregarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgregarPedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAgregarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAgregarPedidoLayout.createSequentialGroup()
                        .addGroup(panelAgregarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAgregarPedidoLayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dtFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAgregarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelAgregarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelAgregarPedidoLayout.createSequentialGroup()
                                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtDias, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelAgregarPedidoLayout.createSequentialGroup()
                                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCantidadCont, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(panelAgregarPedidoLayout.createSequentialGroup()
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(70, 70, 70)
                                    .addComponent(cboRangoHorarioEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelAgregarPedidoLayout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelAgregarPedidoLayout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cboZona, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelAgregarPedidoLayout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtBarrio, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelAgregarPedidoLayout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 353, Short.MAX_VALUE)
                        .addGroup(panelAgregarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCargarPedido))
                        .addGap(60, 60, 60))
                    .addGroup(panelAgregarPedidoLayout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panelAgregarPedidoLayout.setVerticalGroup(
            panelAgregarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgregarPedidoLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelAgregarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAgregarPedidoLayout.createSequentialGroup()
                        .addGroup(panelAgregarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(cboCliente))
                        .addGap(35, 35, 35)
                        .addGroup(panelAgregarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(panelAgregarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtBarrio, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(panelAgregarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboZona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCargarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(panelAgregarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dtFechaEntrega, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(31, 31, 31)
                        .addGroup(panelAgregarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(cboRangoHorarioEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelAgregarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel28)
                            .addComponent(txtDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(panelAgregarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(txtCantidadCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelAgregarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboEstado))
                        .addGap(30, 30, 30))
                    .addGroup(panelAgregarPedidoLayout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(173, 424, Short.MAX_VALUE))))
        );

        panelModificarPedido.setColor1(new java.awt.Color(255, 168, 11));
        panelModificarPedido.setColor2(new java.awt.Color(255, 168, 11));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("ID PEDIDO");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("DIRECCION");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("BARRIO");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("ZONA");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("FECHA DE ENTREGA");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("HORARIO");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setText("DIAS");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setText("CONTENEDORES");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setText("OBSERVACIONES");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setText("ESTADO");

        txtIdPedido.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtDireccion1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtBarrio1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        cboEstado1.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        dtFechaEntrega1.setDateFormatString("yyyy-MM-dd");
        dtFechaEntrega1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cboHorario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cboHorario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08:00 - 10:00", "10:00 - 12:00", "14:00 - 16:00", "16:00 - 18:00" }));

        txtDias1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtDias1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDias1KeyTyped(evt);
            }
        });

        txtContenedores.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtContenedores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContenedoresKeyTyped(evt);
            }
        });

        txtObservaciones1.setColumns(20);
        txtObservaciones1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtObservaciones1.setRows(5);
        jScrollPane2.setViewportView(txtObservaciones1);

        cboZona1.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        tblListadoModificarPedido.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tblListadoModificarPedido.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblListadoModificarPedido.setModel(new javax.swing.table.DefaultTableModel(
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
        tblListadoModificarPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListadoModificarPedidoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblListadoModificarPedido);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Desde");

        dtDesde.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Hasta");

        dtHasta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtBuscarModificarPedido.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtBuscarModificarPedido.setForeground(new java.awt.Color(204, 204, 204));
        txtBuscarModificarPedido.setText("Buscar Pedido...");
        txtBuscarModificarPedido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarModificarPedidoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarModificarPedidoFocusLost(evt);
            }
        });
        txtBuscarModificarPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarModificarPedidoKeyReleased(evt);
            }
        });

        btnFiltrar.setBackground(new java.awt.Color(229, 232, 232));
        btnFiltrar.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        btnFiltrar.setText("FILTRAR");
        btnFiltrar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnFiltrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        btnFinalizarPedido.setBackground(new java.awt.Color(255, 51, 51));
        btnFinalizarPedido.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        btnFinalizarPedido.setText("FINALIZAR PEDIDO");
        btnFinalizarPedido.setHideActionText(true);
        btnFinalizarPedido.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnFinalizarPedido.setIconTextGap(6);
        btnFinalizarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarPedidoActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(255, 255, 0));
        btnModificar.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\update.png")); // NOI18N
        btnModificar.setText("GRABAR");
        btnModificar.setHideActionText(true);
        btnModificar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnModificar.setIconTextGap(6);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\search.png")); // NOI18N

        javax.swing.GroupLayout panelModificarPedidoLayout = new javax.swing.GroupLayout(panelModificarPedido);
        panelModificarPedido.setLayout(panelModificarPedidoLayout);
        panelModificarPedidoLayout.setHorizontalGroup(
            panelModificarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelModificarPedidoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelModificarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel17))
                .addGap(55, 55, 55)
                .addGroup(panelModificarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelModificarPedidoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtDireccion1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))
                    .addGroup(panelModificarPedidoLayout.createSequentialGroup()
                        .addGroup(panelModificarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtContenedores, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelModificarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(panelModificarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBarrio1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dtFechaEntrega1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDias1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(cboZona1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cboEstado1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(panelModificarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelModificarPedidoLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelModificarPedidoLayout.createSequentialGroup()
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(btnFinalizarPedido))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelModificarPedidoLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(14, 14, 14)
                        .addComponent(txtBuscarModificarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );
        panelModificarPedidoLayout.setVerticalGroup(
            panelModificarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelModificarPedidoLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(panelModificarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelModificarPedidoLayout.createSequentialGroup()
                        .addGroup(panelModificarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtBuscarModificarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(7, 7, 7)
                        .addGroup(panelModificarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(dtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(dtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelModificarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFinalizarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelModificarPedidoLayout.createSequentialGroup()
                        .addGroup(panelModificarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(19, 19, 19)
                        .addGroup(panelModificarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDireccion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(panelModificarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtBarrio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(panelModificarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cboZona1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(panelModificarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dtFechaEntrega1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(panelModificarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboHorario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(29, 29, 29)
                        .addGroup(panelModificarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDias1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(21, 21, 21)
                        .addGroup(panelModificarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtContenedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(panelModificarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelModificarPedidoLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel18)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelModificarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboEstado1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(54, 54, 54))))
        );

        panelEliminarPedido.setColor1(new java.awt.Color(255, 168, 11));
        panelEliminarPedido.setColor2(new java.awt.Color(255, 168, 11));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel21.setText("ID PEDIDO");

        txtIdEliminarPedido.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtDireccionEliminar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel22.setText("DIRECCION");

        tblListadoEliminarPedido.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tblListadoEliminarPedido.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblListadoEliminarPedido.setModel(new javax.swing.table.DefaultTableModel(
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
        tblListadoEliminarPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListadoEliminarPedidoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblListadoEliminarPedido);

        btnEliminar.setBackground(new java.awt.Color(217, 83, 79));
        btnEliminar.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\delete.png")); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEliminar.setIconTextGap(6);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        txtBuscarEliminarPedido.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtBuscarEliminarPedido.setForeground(new java.awt.Color(204, 204, 204));
        txtBuscarEliminarPedido.setText("Buscar Pedido...");
        txtBuscarEliminarPedido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarEliminarPedidoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarEliminarPedidoFocusLost(evt);
            }
        });
        txtBuscarEliminarPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarEliminarPedidoKeyReleased(evt);
            }
        });

        dtDesde1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Desde");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel23.setText("Hasta");

        dtHasta1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnFiltrar1.setBackground(new java.awt.Color(229, 232, 232));
        btnFiltrar1.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        btnFiltrar1.setText("FILTRAR");
        btnFiltrar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnFiltrar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFiltrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrar1ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\search.png")); // NOI18N

        javax.swing.GroupLayout panelEliminarPedidoLayout = new javax.swing.GroupLayout(panelEliminarPedido);
        panelEliminarPedido.setLayout(panelEliminarPedidoLayout);
        panelEliminarPedidoLayout.setHorizontalGroup(
            panelEliminarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEliminarPedidoLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(btnEliminar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEliminarPedidoLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panelEliminarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEliminarPedidoLayout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(txtDireccionEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dtDesde1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addComponent(dtHasta1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFiltrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79))
                    .addGroup(panelEliminarPedidoLayout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(txtIdEliminarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscarEliminarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(398, 398, 398))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEliminarPedidoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );
        panelEliminarPedidoLayout.setVerticalGroup(
            panelEliminarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEliminarPedidoLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(panelEliminarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelEliminarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIdEliminarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21)
                        .addComponent(txtBuscarEliminarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(panelEliminarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(dtDesde1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(dtHasta1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelEliminarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(txtDireccionEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelBgLayout = new javax.swing.GroupLayout(panelBg);
        panelBg.setLayout(panelBgLayout);
        panelBgLayout.setHorizontalGroup(
            panelBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelAgregarPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelModificarPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelEliminarPedido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBgLayout.setVerticalGroup(
            panelBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelAgregarPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelModificarPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelEliminarPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelOpciones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBg, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 4, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblAgregarPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAgregarPedidoMouseClicked
        panelAgregarPedido.setVisible(true);
        panelModificarPedido.setVisible(false);
        panelEliminarPedido.setVisible(false);
    }//GEN-LAST:event_lblAgregarPedidoMouseClicked

    private void lblModificarPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblModificarPedidoMouseClicked
        panelAgregarPedido.setVisible(false);
        panelModificarPedido.setVisible(true);
        panelEliminarPedido.setVisible(false);
    }//GEN-LAST:event_lblModificarPedidoMouseClicked

    private void lblEliminarPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEliminarPedidoMouseClicked
        panelAgregarPedido.setVisible(false);
        panelModificarPedido.setVisible(false);
        panelEliminarPedido.setVisible(true);
    }//GEN-LAST:event_lblEliminarPedidoMouseClicked

    private void txtDiasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasKeyTyped
        int key = evt.getKeyChar();
        boolean numero = key >= 48 && key <= 57;
        if (!numero) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDiasKeyTyped

    private void txtCantidadContKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadContKeyTyped
        int key = evt.getKeyChar();
        boolean numero = key >= 48 && key <= 57;
        if (!numero) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadContKeyTyped

    private void btnCargarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarPedidoActionPerformed

        Zona zona = (Zona) cboZona.getSelectedItem();
        Cliente cliente = (Cliente) cboCliente.getSelectedItem();
        Estado estado = (Estado) cboEstado.getSelectedItem();
        String direccion = txtDireccion.getText();
        String barrio = txtBarrio.getText();
        String horarioEntrega = cboRangoHorarioEntrega.getSelectedItem().toString();
        String observaciones = txtObservaciones.getText();
        int dias = Integer.parseInt(txtDias.getText());
        int cantidadCont = Integer.parseInt(txtCantidadCont.getText());

        DetallePedido d = new DetallePedido(((JTextField) dtFechaEntrega.getDateEditor().getUiComponent()).getText(), horarioEntrega, observaciones, direccion, barrio, dias, cantidadCont, zona.getIdZona(), estado.getIdEstado(), cliente.getIdCliente());

        try {
            g.agregarPedido(d);
            //------------------
            txtDireccion.setText("");
            txtBarrio.setText("");
            txtDias.setText("");
            txtCantidadCont.setText("");
            txtObservaciones.setText("");
            cboZona.setSelectedIndex(0);
            cboEstado.setSelectedIndex(0);
            cboCliente.setSelectedIndex(0);
            cargarTablaPedidos();
            cargarTablaEliminarPedidos();
            //------------------
            JOptionPane.showMessageDialog(this, "Carga de Pedido Exitosa");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Revise los campos");
        }
    }//GEN-LAST:event_btnCargarPedidoActionPerformed

    private void txtDias1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDias1KeyTyped
        int key = evt.getKeyChar();
        boolean numero = key >= 48 && key <= 57;
        if (!numero) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDias1KeyTyped

    private void txtContenedoresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContenedoresKeyTyped
        int key = evt.getKeyChar();
        boolean numero = key >= 48 && key <= 57;
        if (!numero) {
            evt.consume();
        }
    }//GEN-LAST:event_txtContenedoresKeyTyped

    private void tblListadoModificarPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListadoModificarPedidoMouseClicked
        btnModificar.setEnabled(true);
        btnFinalizarPedido.setEnabled(true);
        try {
            int selectedRow = tblListadoModificarPedido.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tblListadoModificarPedido.getModel();

            txtIdPedido.setText(model.getValueAt(selectedRow, 0).toString());
            txtDireccion1.setText(model.getValueAt(selectedRow, 1).toString());
            txtBarrio1.setText(model.getValueAt(selectedRow, 2).toString());
            String zona = model.getValueAt(selectedRow, 3).toString();
            switch (zona) {
                case "Norte":
                    cboZona1.setSelectedIndex(1);
                    break;
                case "Sur":
                    cboZona1.setSelectedIndex(2);
                    break;
                case "Este":
                    cboZona1.setSelectedIndex(3);
                    break;
                case "Oeste":
                    cboZona1.setSelectedIndex(4);
                    break;
                case "Centro":
                    cboZona1.setSelectedIndex(5);
                    break;
            }

            Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(selectedRow, 4));
            dtFechaEntrega1.setDate(fecha);

            String horario = model.getValueAt(selectedRow, 5).toString();
            switch (horario) {
                case "08:00 - 10:00":
                    cboHorario.setSelectedIndex(0);
                    break;
                case "10:00 - 12:00":
                    cboHorario.setSelectedIndex(1);
                    break;
                case "14:00 - 16:00":
                    cboHorario.setSelectedIndex(2);
                    break;
                case "16:00 - 18:00":
                    cboHorario.setSelectedIndex(3);
                    break;

            }
            txtDias1.setText(model.getValueAt(selectedRow, 6).toString());
            txtContenedores.setText(model.getValueAt(selectedRow, 7).toString());
            txtObservaciones1.setText(model.getValueAt(selectedRow, 8).toString());
            String estado = model.getValueAt(selectedRow, 9).toString();
            switch (estado) {
                case "Pedido":
                    cboEstado1.setSelectedIndex(1);
                    break;
                case "Entregado":
                    cboEstado1.setSelectedIndex(2);
                    break;
                case "Recambio":
                    cboEstado1.setSelectedIndex(3);
                    break;
                case "Finalizado":
                    cboEstado1.setSelectedIndex(4);
                    break;

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tblListadoModificarPedidoMouseClicked

    private void txtBuscarModificarPedidoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarModificarPedidoFocusGained
        if (txtBuscarModificarPedido.getText().equals("Buscar Pedido...")) {
            txtBuscarModificarPedido.setText(null);
            txtBuscarModificarPedido.requestFocus();
            removePlaceHolder(txtBuscarModificarPedido);
        }
    }//GEN-LAST:event_txtBuscarModificarPedidoFocusGained

    private void txtBuscarModificarPedidoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarModificarPedidoFocusLost
        if (txtBuscarModificarPedido.getText().length() == 0) {
            addPlaceHolder(txtBuscarModificarPedido);
            txtBuscarModificarPedido.setText("Buscar Pedido...");
        }
    }//GEN-LAST:event_txtBuscarModificarPedidoFocusLost

    private void txtBuscarModificarPedidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarModificarPedidoKeyReleased
        String query = txtBuscarModificarPedido.getText();
        filter(query);
    }//GEN-LAST:event_txtBuscarModificarPedidoKeyReleased

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        try {
            buscarEntreFecha();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnFinalizarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarPedidoActionPerformed
        int id = Integer.parseInt(txtIdPedido.getText());

        DetallePedido p = new DetallePedido(id);
        try {
            g.finalizarPedido(p);
            cargarTablaPedidos();
            cargarTablaEliminarPedidos();

            JOptionPane.showMessageDialog(this, "Pedido Finalizado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Revise los campos");
        }

    }//GEN-LAST:event_btnFinalizarPedidoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

        int id = Integer.parseInt(txtIdPedido.getText());
        String horario = cboHorario.getSelectedItem().toString();
        String observacion = txtObservaciones1.getText();
        String direccion = txtDireccion1.getText();
        int dias = Integer.parseInt(txtDias1.getText());
        String barrio = txtBarrio1.getText();
        int contenedor = Integer.parseInt(txtContenedores.getText());
        Zona zona = (Zona) cboZona1.getSelectedItem();
        Estado estado = (Estado) cboEstado1.getSelectedItem();

        DetallePedido p = new DetallePedido(id, ((JTextField) dtFechaEntrega1.getDateEditor().getUiComponent()).getText(), horario, observacion, direccion, barrio, dias, contenedor, zona.getIdZona(), estado.getIdEstado());
        try {
            g.modificarPedido(p);
            cargarTablaPedidos();
            cargarTablaEliminarPedidos();
            btnModificar.setEnabled(false);
            btnFinalizarPedido.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Pedido Modificado");
            //--
            txtIdPedido.setText("");
            txtObservaciones1.setText("");
            txtDireccion1.setText("");
            txtDias1.setText("");
            txtBarrio1.setText("");
            txtContenedores.setText("");
            //--
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Revise los campos");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void tblListadoEliminarPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListadoEliminarPedidoMouseClicked
        btnEliminar.setEnabled(true);
        try {
            int selectedRow = tblListadoEliminarPedido.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tblListadoEliminarPedido.getModel();

            txtIdEliminarPedido.setText(model.getValueAt(selectedRow, 0).toString());
            txtDireccionEliminar.setText(model.getValueAt(selectedRow, 1).toString());

        } catch (Exception e) {
        }
    }//GEN-LAST:event_tblListadoEliminarPedidoMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int response = JOptionPane.showConfirmDialog(this, "Esta seguro de eliminar el Pedido?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {

            int id = Integer.parseInt(txtIdEliminarPedido.getText());

            DetallePedido p = new DetallePedido(id);
            try {
                g.deletePedido(p);
                cargarTablaPedidos();
                cargarTablaEliminarPedidos();
                txtIdEliminarPedido.setText("");
                txtDireccionEliminar.setText("");
                btnEliminar.setEnabled(false);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Seleccionar un Cliente");
            }
        } else if (response == JOptionPane.NO_OPTION) {

        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtBuscarEliminarPedidoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarEliminarPedidoFocusGained
        if (txtBuscarEliminarPedido.getText().equals("Buscar Pedido...")) {
            txtBuscarEliminarPedido.setText(null);
            txtBuscarEliminarPedido.requestFocus();
            removePlaceHolder(txtBuscarEliminarPedido);
        }
    }//GEN-LAST:event_txtBuscarEliminarPedidoFocusGained

    private void txtBuscarEliminarPedidoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarEliminarPedidoFocusLost
        if (txtBuscarEliminarPedido.getText().length() == 0) {
            addPlaceHolder(txtBuscarEliminarPedido);
            txtBuscarEliminarPedido.setText("Buscar Pedido...");
        }
    }//GEN-LAST:event_txtBuscarEliminarPedidoFocusLost

    private void txtBuscarEliminarPedidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarEliminarPedidoKeyReleased
        String query = txtBuscarEliminarPedido.getText();
        filterEliminarPedido(query);
    }//GEN-LAST:event_txtBuscarEliminarPedidoKeyReleased

    private void btnFiltrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrar1ActionPerformed
        try {
            buscarEntreFechaEliminar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnFiltrar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private PanelesBG.BackGround backGround2;
    private PanelesBG.BackGround backGround3;
    private PanelesBG.BackGround backGround4;
    private javax.swing.JButton btnCargarPedido;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnFiltrar1;
    private javax.swing.JButton btnFinalizarPedido;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cboCliente;
    private javax.swing.JComboBox<String> cboEstado;
    private javax.swing.JComboBox cboEstado1;
    private javax.swing.JComboBox cboHorario;
    private javax.swing.JComboBox cboRangoHorarioEntrega;
    private javax.swing.JComboBox<String> cboZona;
    private javax.swing.JComboBox cboZona1;
    private com.toedter.calendar.JDateChooser dtDesde;
    private com.toedter.calendar.JDateChooser dtDesde1;
    private com.toedter.calendar.JDateChooser dtFechaEntrega;
    private com.toedter.calendar.JDateChooser dtFechaEntrega1;
    private com.toedter.calendar.JDateChooser dtHasta;
    private com.toedter.calendar.JDateChooser dtHasta1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lblAgregarPedido;
    private javax.swing.JLabel lblEliminarPedido;
    private javax.swing.JLabel lblModificarPedido;
    private PanelesBG.BackGround panelAgregarPedido;
    private javax.swing.JPanel panelBg;
    private PanelesBG.BackGround panelEliminarPedido;
    private PanelesBG.BackGround panelModificarPedido;
    private javax.swing.JPanel panelOpciones;
    private javax.swing.JTable tblListadoEliminarPedido;
    private javax.swing.JTable tblListadoModificarPedido;
    private javax.swing.JTextField txtBarrio;
    private javax.swing.JTextField txtBarrio1;
    private javax.swing.JTextField txtBuscarEliminarPedido;
    private javax.swing.JTextField txtBuscarModificarPedido;
    private javax.swing.JTextField txtCantidadCont;
    private javax.swing.JTextField txtContenedores;
    private javax.swing.JTextField txtDias;
    private javax.swing.JTextField txtDias1;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDireccion1;
    private javax.swing.JTextField txtDireccionEliminar;
    private javax.swing.JTextField txtIdEliminarPedido;
    private javax.swing.JTextField txtIdPedido;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextArea txtObservaciones1;
    // End of variables declaration//GEN-END:variables
}
