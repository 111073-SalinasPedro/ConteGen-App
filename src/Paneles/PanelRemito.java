package Paneles;

import DTO.DTOPedido;
import DTO.DTORemito;
import GestorBD.GestorBD;
import Model.*;
import Print.VentanaImprimir;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public class PanelRemito extends javax.swing.JPanel {

    GestorBD g;
    PreparedStatement pst = null;
    ResultSet st = null;
    private String CONN = "jdbc:sqlserver://localhost:1433;databaseName=Contegen";
    private String USER = "sa";
    private String PASS = "123456";
    private Connection con;
    TableRowSorter tr;
    DefaultTableModel modeloAgregarRemito = new DefaultTableModel();
    DefaultTableModel modeloRemito = new DefaultTableModel();
    DefaultTableModel modeloModificarRemito = new DefaultTableModel();
    DefaultTableModel modeloEliminarRemito = new DefaultTableModel();

    public PanelRemito() {
        initComponents();
        g = new GestorBD();
        EliminarRemito.setVisible(false);
        ModificarRemito.setVisible(false);
        btnCargarRemito.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        txtIdRemito.setEditable(false);
        txtIdPedido.setEditable(false);
        txtidPedidoRemito.setEditable(false);
        txtRemitoContenedores.setEditable(false);
        txtRemitoDias.setEditable(false);
        txtIdEliminarRemito.setEditable(false);
        txtIdEliminarRemitoPedido.setEditable(false);
        cboEliminarContenedor.setEnabled(false);
        //---
        modeloAgregarRemito.addColumn("Pedido");
        modeloAgregarRemito.addColumn("Fecha");
        modeloAgregarRemito.addColumn("Cliente");
        modeloAgregarRemito.addColumn("Direccion");
        modeloAgregarRemito.addColumn("Contenedores");
        modeloAgregarRemito.addColumn("Dias");
        tblPedido.setModel(modeloAgregarRemito);
        cargarTablaPedidosRemito();
        //---
        modeloRemito.addColumn("Remito");
        modeloRemito.addColumn("Fecha");
        modeloRemito.addColumn("FormaPago");
        modeloRemito.addColumn("Estado");
        modeloRemito.addColumn("Contenedor");
        modeloRemito.addColumn("Dias");
        modeloRemito.addColumn("Contendores");
        modeloRemito.addColumn("Pedido");
        modeloRemito.addColumn("Camion");
        modeloRemito.addColumn("Importe");
        tblRemito.setModel(modeloRemito);
        cargarTablaRemito();
        //---
        modeloModificarRemito.addColumn("idRemito");
        modeloModificarRemito.addColumn("Fecha");
        modeloModificarRemito.addColumn("FormaPago");
        modeloModificarRemito.addColumn("EstadoRemito");
        modeloModificarRemito.addColumn("Contenedor");
        modeloModificarRemito.addColumn("Dias");
        modeloModificarRemito.addColumn("Contenedores");
        modeloModificarRemito.addColumn("EstadoPedido");
        modeloModificarRemito.addColumn("Camion");
        modeloModificarRemito.addColumn("Pedido");
        modeloModificarRemito.addColumn("Importe");
        tblModificarRemito.setModel(modeloModificarRemito);
        cargarTablaModificarRemito();
        //---
        modeloEliminarRemito.addColumn("idRemito");
        modeloEliminarRemito.addColumn("Fecha");
        modeloEliminarRemito.addColumn("FormaPago");
        modeloEliminarRemito.addColumn("EstadoRemito");
        modeloEliminarRemito.addColumn("Contenedor");
        modeloEliminarRemito.addColumn("Dias");
        modeloEliminarRemito.addColumn("Contenedores");
        modeloEliminarRemito.addColumn("EstadoPedido");
        modeloEliminarRemito.addColumn("Camion");
        modeloEliminarRemito.addColumn("Pedido");
        modeloEliminarRemito.addColumn("Importe");
        tblEliminarRemito.setModel(modeloEliminarRemito);
        cargarTablaEliminarRemito();
        //---
        cargarCamion();
        cargarCodigoContenedor();
        cargarEstadoRemito();
        cargarFormadePago();
        //---
        cargarModificarCamion();
        cargarModificarCodigoContenedor();
        cargarModificarFormadePago();
        cargarModificarEstadoRemito();
        //---
        cargarEliminarCodigoContenedor();
        //---
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); //Alinear Texto Centrado
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int x = 0; x < 6; x++) {
            tblPedido.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }
        tblPedido.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblPedido.getColumnModel().getColumn(1).setPreferredWidth(30);
        tblPedido.getColumnModel().getColumn(2).setPreferredWidth(60);
        tblPedido.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblPedido.getColumnModel().getColumn(4).setPreferredWidth(10);
        tblPedido.getColumnModel().getColumn(5).setPreferredWidth(10);
        //---
        for (int x = 0; x < 10; x++) {
            tblRemito.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }
        tblRemito.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblRemito.getColumnModel().getColumn(1).setPreferredWidth(30);
        tblRemito.getColumnModel().getColumn(2).setPreferredWidth(60);
        tblRemito.getColumnModel().getColumn(3).setPreferredWidth(15);
        tblRemito.getColumnModel().getColumn(4).setPreferredWidth(10);
        tblRemito.getColumnModel().getColumn(5).setPreferredWidth(10);
        tblRemito.getColumnModel().getColumn(6).setPreferredWidth(15);
        tblRemito.getColumnModel().getColumn(7).setPreferredWidth(10);
        tblRemito.getColumnModel().getColumn(8).setPreferredWidth(10);
        tblRemito.getColumnModel().getColumn(9).setPreferredWidth(20);
        //--
        for (int x = 0; x < 11; x++) {
            tblModificarRemito.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }
        tblModificarRemito.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblModificarRemito.getColumnModel().getColumn(1).setPreferredWidth(20);
        tblModificarRemito.getColumnModel().getColumn(2).setPreferredWidth(40);
        tblModificarRemito.getColumnModel().getColumn(3).setPreferredWidth(40);
        tblModificarRemito.getColumnModel().getColumn(4).setPreferredWidth(10);
        tblModificarRemito.getColumnModel().getColumn(5).setPreferredWidth(10);
        tblModificarRemito.getColumnModel().getColumn(6).setPreferredWidth(30);
        tblModificarRemito.getColumnModel().getColumn(7).setPreferredWidth(30);
        tblModificarRemito.getColumnModel().getColumn(8).setPreferredWidth(10);
        tblModificarRemito.getColumnModel().getColumn(9).setPreferredWidth(10);
        tblModificarRemito.getColumnModel().getColumn(10).setPreferredWidth(10);
        //--
        for (int x = 0; x < 11; x++) {
            tblEliminarRemito.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }
        tblEliminarRemito.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblEliminarRemito.getColumnModel().getColumn(1).setPreferredWidth(20);
        tblEliminarRemito.getColumnModel().getColumn(2).setPreferredWidth(40);
        tblEliminarRemito.getColumnModel().getColumn(3).setPreferredWidth(40);
        tblEliminarRemito.getColumnModel().getColumn(4).setPreferredWidth(10);
        tblEliminarRemito.getColumnModel().getColumn(5).setPreferredWidth(10);
        tblEliminarRemito.getColumnModel().getColumn(6).setPreferredWidth(30);
        tblEliminarRemito.getColumnModel().getColumn(7).setPreferredWidth(30);
        tblEliminarRemito.getColumnModel().getColumn(8).setPreferredWidth(10);
        tblEliminarRemito.getColumnModel().getColumn(9).setPreferredWidth(10);
        tblEliminarRemito.getColumnModel().getColumn(10).setPreferredWidth(10);

    }

    private void abrirConexion() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            con = DriverManager.getConnection(CONN, USER, PASS);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //--AgregarRemito
    private void filterPedidoRemito(String query) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modeloAgregarRemito);
        tblPedido.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarAgregarPedidoRemito.getText(), 0));
    }

    private void filterRemito(String query) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modeloRemito);
        tblRemito.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarAgregarRemito.getText(), 0));
    }

    private void cargarTablaPedidosRemito() {
        ArrayList<DTOPedido> lista = g.listadoPedidoRemito();

        for (DTOPedido c : lista) {
            modeloAgregarRemito.addRow(new Object[]{
                c.getIdPedido(),
                c.getFechaEntrega(),
                c.getNombreCliente(),
                c.getDireccion(),
                c.getCant(),
                c.getDias()});
        }
        tblPedido.setModel(modeloAgregarRemito);
    }

    private void cargarTablaRemito() {
        ArrayList<DTORemito> lista = g.listadoRemito();
        modeloRemito.setRowCount(0);
        for (DTORemito c : lista) {
            modeloRemito.addRow(new Object[]{
                c.getIdRemito(),
                c.getFecha(),
                c.getFormaPago(),
                c.getEstado(),
                c.getContenedor(),
                c.getDias(),
                c.getCantContenedores(),
                c.getIdPedido(),
                c.getCamion(),
                c.getImporte()});
        }
        tblRemito.setModel(modeloRemito);
    }

    private void cargarFormadePago() {
        ArrayList<FormaPago> formaPago = g.obtenerTodosLasFormaPago();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addElement("Seleccionar");
        for (FormaPago f : formaPago) {
            modelo.addElement(f);
        }
        cboFormaPago.setModel(modelo);
    }

    private void cargarCodigoContenedor() {
        ArrayList<Contenedor> contenedor = g.obtenerTodosLosContenedoresDisponibles();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addElement("Seleccionar");
        for (Contenedor c : contenedor) {
            modelo.addElement(c);
        }
        cboCodContenedor1.setModel(modelo);

    }

    private void cargarEstadoRemito() {
        ArrayList<EstadoRemito> estadoRemito = g.obtenerTodosLosEstadosRemito();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addElement("Seleccionar");
        for (EstadoRemito e : estadoRemito) {
            modelo.addElement(e);
        }
        cboEstadoRemito.setModel(modelo);

    }

    private void cargarCamion() {
        ArrayList<Camion> camion = g.obtenerTodosLosCamiones();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addElement("Seleccionar");
        for (Camion e : camion) {
            modelo.addElement(e);
        }
        cboCamion.setModel(modelo);

    }

    private void calcularImporte() {
        int dias, cont;
        double resultado;
        dias = Integer.parseInt(txtRemitoDias.getText());
        cont = Integer.parseInt(txtRemitoContenedores.getText());
        resultado = (3000 * cont) * dias;
        lblImporte.setText(String.valueOf(resultado));
    }

    public void buscarEntreFechaPedidoRemito() {
        int dia, mes, año;
        int dia2, mes2, año2;
        String fecha1, fecha2;

        dia = dtPedidoRemitoDesde.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes = dtPedidoRemitoDesde.getCalendar().get(Calendar.MONTH) + 1;
        año = dtPedidoRemitoDesde.getCalendar().get(Calendar.YEAR);
        fecha1 = año + "-" + mes + "-" + dia;
        dia2 = dtPedidoRemitoHasta.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes2 = dtPedidoRemitoHasta.getCalendar().get(Calendar.MONTH) + 1;
        año2 = dtPedidoRemitoHasta.getCalendar().get(Calendar.YEAR);
        fecha2 = año2 + "-" + mes2 + "-" + dia2;

        List<DTOPedido> list = g.mostrarPedidoRemitoDesdeHasta(fecha1, fecha2);
        modeloAgregarRemito.setRowCount(0);
        for (DTOPedido p : list) {
            modeloAgregarRemito.addRow(new Object[]{p.getIdPedido(), p.getFechaEntrega(), p.getNombreCliente(), p.getDireccion(), p.getCant(), p.getDias()});
        }

    }

    public void buscarEntreFechaRemito() {
        int dia, mes, año;
        int dia2, mes2, año2;
        String fecha1, fecha2;

        dia = dtRemitoDesde.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes = dtRemitoDesde.getCalendar().get(Calendar.MONTH) + 1;
        año = dtRemitoDesde.getCalendar().get(Calendar.YEAR);
        fecha1 = dia + "-" + mes + "-" + año;
        dia2 = dtRemitoHasta.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes2 = dtRemitoHasta.getCalendar().get(Calendar.MONTH) + 1;
        año2 = dtRemitoHasta.getCalendar().get(Calendar.YEAR);
        fecha2 = dia2 + "-" + mes2 + "-" + año2;

        List<DTORemito> list = g.mostrarRemitoDesdeHasta(fecha1, fecha2);
        modeloRemito.setRowCount(0);
        for (DTORemito p : list) {
            modeloRemito.addRow(new Object[]{p.getIdRemito(), p.getFecha(), p.getFormaPago(), p.getEstado(),
                p.getContenedor(), p.getDias(), p.getCantContenedores(), p.getImporte(), p.getIdPedido(),
                p.getCamion()});
        }

    }

    //--Modificar Remito
    private void filterModificarRemito(String query) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modeloModificarRemito);
        tblModificarRemito.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarModificarRemito.getText(), 0));
    }

    public void buscarEntreFechaModificarRemito() {
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

        List<DTORemito> list = g.mostrarRemitoModificarDesdeHasta(fecha1, fecha2);
        modeloModificarRemito.setRowCount(0);
        for (DTORemito p : list) {
            modeloModificarRemito.addRow(new Object[]{p.getIdRemito(), p.getFecha(), p.getFormaPago(), p.getEstado(), p.getContenedor(), p.getDias(),
                p.getCantContenedores(), p.getEstadoPedido(), p.getCamion(), p.getIdPedido(), p.getImporte()});
        }
    }

    private void cargarTablaModificarRemito() {
        ArrayList<DTORemito> lista = g.listadoModificarRemito();
        modeloModificarRemito.setRowCount(0);
        for (DTORemito c : lista) {
            modeloModificarRemito.addRow(new Object[]{
                c.getIdRemito(),
                c.getFecha(),
                c.getFormaPago(),
                c.getEstado(),
                c.getContenedor(),
                c.getDias(),
                c.getCantContenedores(),
                c.getEstadoPedido(),
                c.getCamion(),
                c.getIdPedido(),
                c.getImporte()
            });
        }
        tblModificarRemito.setModel(modeloModificarRemito);
    }

    private void cargarModificarCodigoContenedor() {

        ArrayList<Contenedor> contenedor = g.obtenerTodosLosContenedores();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addElement("  ");
        for (Contenedor c : contenedor) {
            modelo.addElement(c);
        }
        cboContenedor.setModel(modelo);

    }

    private void cargarModificarEstadoRemito() {
        ArrayList<EstadoRemito> estadoRemito = g.obtenerTodosLosEstadosRemito();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addElement("  ");
        for (EstadoRemito e : estadoRemito) {
            modelo.addElement(e);
        }
        cboEstadoRemito1.setModel(modelo);

    }

    private void cargarModificarFormadePago() {
        ArrayList<FormaPago> formaPago = g.obtenerTodosLasFormaPago();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addElement("  ");
        for (FormaPago f : formaPago) {
            modelo.addElement(f);
        }
        cboFormaPago1.setModel(modelo);
    }

    private void cargarModificarCamion() {
        ArrayList<Camion> camion = g.obtenerTodosLosCamiones();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addElement("  ");
        for (Camion e : camion) {
            modelo.addElement(e);
        }
        cboCamion1.setModel(modelo);
    }

    //--Eliminar Remito
    private void cargarEliminarCodigoContenedor() {
        ArrayList<Contenedor> contenedor = g.obtenerTodosLosContenedores();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addElement("  ");
        for (Contenedor c : contenedor) {
            modelo.addElement(c);
        }
        cboEliminarContenedor.setModel(modelo);

    }

    private void filterEliminarRemito(String query) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modeloModificarRemito);
        tblEliminarRemito.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarEliminarRemito.getText(), 0));
    }

    public void buscarEntreFechaEliminarRemito() {
        int dia, mes, año;
        int dia2, mes2, año2;
        String fecha1, fecha2;

        dia = dtDesde1.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes = dtDesde1.getCalendar().get(Calendar.MONTH) + 1;
        año = dtDesde1.getCalendar().get(Calendar.YEAR);
        fecha1 = año + "-" + mes + "-" + dia;
        dia2 = dtHasta1.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes2 = dtHasta1.getCalendar().get(Calendar.MONTH) + 1;
        año2 = dtHasta1.getCalendar().get(Calendar.YEAR);
        fecha2 = año2 + "-" + mes2 + "-" + dia2;

        List<DTORemito> list = g.mostrarRemitoModificarDesdeHasta(fecha1, fecha2);
        modeloEliminarRemito.setRowCount(0);
        for (DTORemito p : list) {
            modeloEliminarRemito.addRow(new Object[]{p.getIdRemito(), p.getFecha(), p.getFormaPago(), p.getEstado(), p.getContenedor(), p.getDias(),
                p.getCantContenedores(), p.getEstadoPedido(), p.getCamion(), p.getIdPedido(), p.getImporte()});
        }
    }

    private void cargarTablaEliminarRemito() {
        ArrayList<DTORemito> lista = g.listadoModificarRemito();
        modeloEliminarRemito.setRowCount(0);
        for (DTORemito c : lista) {
            modeloEliminarRemito.addRow(new Object[]{
                c.getIdRemito(),
                c.getFecha(),
                c.getFormaPago(),
                c.getEstado(),
                c.getContenedor(),
                c.getDias(),
                c.getCantContenedores(),
                c.getEstadoPedido(),
                c.getCamion(),
                c.getIdPedido(),
                c.getImporte()
            });
        }
        tblEliminarRemito.setModel(modeloEliminarRemito);
    }

    //----
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

        panelHeaderRemito = new javax.swing.JPanel();
        backGround1 = new PanelesBG.BackGround();
        lblAgregarRemto = new javax.swing.JLabel();
        backGround2 = new PanelesBG.BackGround();
        lblModificarRemito = new javax.swing.JLabel();
        backGround3 = new PanelesBG.BackGround();
        lblEliminarRemito = new javax.swing.JLabel();
        backGround4 = new PanelesBG.BackGround();
        lblImprimirRemito = new javax.swing.JLabel();
        bgRemito = new javax.swing.JPanel();
        AgregarRemito = new PanelesBG.BackGround();
        jLabel22 = new javax.swing.JLabel();
        txtidPedidoRemito = new javax.swing.JTextField();
        txtFechaEntrega = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        cboFormaPago = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txtRemitoContenedores = new javax.swing.JTextField();
        cboCodContenedor1 = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        txtRemitoDias = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        cboEstadoRemito = new javax.swing.JComboBox<>();
        cboCamion = new javax.swing.JComboBox<>();
        jLabel46 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        lblImporte = new javax.swing.JLabel();
        txtBuscarAgregarPedidoRemito = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblPedido = new javax.swing.JTable();
        btnFiltarPedidosRemito = new javax.swing.JButton();
        dtPedidoRemitoHasta = new com.toedter.calendar.JDateChooser();
        jLabel51 = new javax.swing.JLabel();
        dtPedidoRemitoDesde = new com.toedter.calendar.JDateChooser();
        jLabel40 = new javax.swing.JLabel();
        btnCargarRemito = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblRemito = new javax.swing.JTable();
        jLabel41 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        txtBuscarAgregarRemito = new javax.swing.JTextField();
        btnFiltarRemitos = new javax.swing.JButton();
        dtRemitoDesde = new com.toedter.calendar.JDateChooser();
        dtRemitoHasta = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ModificarRemito = new PanelesBG.BackGround();
        txtBuscarModificarRemito = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblModificarRemito = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cboCamion1 = new javax.swing.JComboBox<>();
        txtImporte = new javax.swing.JTextField();
        txtIdPedido = new javax.swing.JTextField();
        txtIdRemito = new javax.swing.JTextField();
        dtFechaEntrega = new com.toedter.calendar.JDateChooser();
        cboFormaPago1 = new javax.swing.JComboBox<>();
        cboEstadoRemito1 = new javax.swing.JComboBox<>();
        cboContenedor = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        dtDesde = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        dtHasta = new com.toedter.calendar.JDateChooser();
        btnFiltrar = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        EliminarRemito = new PanelesBG.BackGround();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEliminarRemito = new javax.swing.JTable();
        txtIdEliminarRemito = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtIdEliminarRemitoPedido = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        txtBuscarEliminarRemito = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        dtDesde1 = new com.toedter.calendar.JDateChooser();
        jLabel25 = new javax.swing.JLabel();
        dtHasta1 = new com.toedter.calendar.JDateChooser();
        btnFiltrar1 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        cboEliminarContenedor = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));

        panelHeaderRemito.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        backGround1.setColor1(new java.awt.Color(102, 102, 102));
        backGround1.setColor2(new java.awt.Color(92, 184, 92));

        lblAgregarRemto.setBackground(new java.awt.Color(255, 255, 255));
        lblAgregarRemto.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        lblAgregarRemto.setForeground(new java.awt.Color(255, 255, 255));
        lblAgregarRemto.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\add_remito.png")); // NOI18N
        lblAgregarRemto.setText("AGREGAR REMITO");
        lblAgregarRemto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAgregarRemto.setIconTextGap(10);
        lblAgregarRemto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAgregarRemtoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout backGround1Layout = new javax.swing.GroupLayout(backGround1);
        backGround1.setLayout(backGround1Layout);
        backGround1Layout.setHorizontalGroup(
            backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backGround1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAgregarRemto, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                .addContainerGap())
        );
        backGround1Layout.setVerticalGroup(
            backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backGround1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAgregarRemto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        backGround2.setColor1(new java.awt.Color(102, 102, 102));
        backGround2.setColor2(new java.awt.Color(240, 173, 78));

        lblModificarRemito.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        lblModificarRemito.setForeground(new java.awt.Color(255, 255, 255));
        lblModificarRemito.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\update_remito.png")); // NOI18N
        lblModificarRemito.setText("MODIFICAR REMITO");
        lblModificarRemito.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblModificarRemito.setIconTextGap(10);
        lblModificarRemito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblModificarRemitoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout backGround2Layout = new javax.swing.GroupLayout(backGround2);
        backGround2.setLayout(backGround2Layout);
        backGround2Layout.setHorizontalGroup(
            backGround2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backGround2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblModificarRemito, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
        );
        backGround2Layout.setVerticalGroup(
            backGround2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backGround2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblModificarRemito, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addContainerGap())
        );

        backGround3.setColor1(new java.awt.Color(102, 102, 102));
        backGround3.setColor2(new java.awt.Color(217, 83, 79));

        lblEliminarRemito.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        lblEliminarRemito.setForeground(new java.awt.Color(255, 255, 255));
        lblEliminarRemito.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\delete_remito.png")); // NOI18N
        lblEliminarRemito.setText("ELIMINAR REMITO");
        lblEliminarRemito.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblEliminarRemito.setIconTextGap(10);
        lblEliminarRemito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEliminarRemitoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout backGround3Layout = new javax.swing.GroupLayout(backGround3);
        backGround3.setLayout(backGround3Layout);
        backGround3Layout.setHorizontalGroup(
            backGround3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backGround3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEliminarRemito, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addContainerGap())
        );
        backGround3Layout.setVerticalGroup(
            backGround3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backGround3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEliminarRemito, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addContainerGap())
        );

        backGround4.setColor1(new java.awt.Color(102, 102, 102));
        backGround4.setColor2(new java.awt.Color(2, 94, 181));

        lblImprimirRemito.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        lblImprimirRemito.setForeground(new java.awt.Color(255, 255, 255));
        lblImprimirRemito.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\Print.png")); // NOI18N
        lblImprimirRemito.setText("IMPRIMIR REMITO");
        lblImprimirRemito.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblImprimirRemito.setIconTextGap(10);
        lblImprimirRemito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImprimirRemitoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout backGround4Layout = new javax.swing.GroupLayout(backGround4);
        backGround4.setLayout(backGround4Layout);
        backGround4Layout.setHorizontalGroup(
            backGround4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backGround4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImprimirRemito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        backGround4Layout.setVerticalGroup(
            backGround4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backGround4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImprimirRemito, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelHeaderRemitoLayout = new javax.swing.GroupLayout(panelHeaderRemito);
        panelHeaderRemito.setLayout(panelHeaderRemitoLayout);
        panelHeaderRemitoLayout.setHorizontalGroup(
            panelHeaderRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderRemitoLayout.createSequentialGroup()
                .addComponent(backGround1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backGround2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backGround3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backGround4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelHeaderRemitoLayout.setVerticalGroup(
            panelHeaderRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backGround1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderRemitoLayout.createSequentialGroup()
                .addComponent(backGround4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderRemitoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelHeaderRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backGround2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backGround3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        AgregarRemito.setColor1(new java.awt.Color(255, 168, 11));
        AgregarRemito.setColor2(new java.awt.Color(255, 168, 11));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel22.setText("Pedido");

        txtidPedidoRemito.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtFechaEntrega.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtFechaEntrega.setOpaque(false);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel23.setText("Fecha");

        cboFormaPago.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel24.setText("Forma de Pago");

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel48.setText("Contenedores");

        txtRemitoContenedores.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        cboCodContenedor1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel31.setText("Codigo Contenedor");

        txtRemitoDias.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel47.setText("Dias");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel34.setText("Estado Remito");

        cboEstadoRemito.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        cboCamion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel46.setText("Camion");

        jLabel49.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel49.setText("Importe:");

        lblImporte.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblImporte.setText("0");

        txtBuscarAgregarPedidoRemito.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtBuscarAgregarPedidoRemito.setForeground(new java.awt.Color(204, 204, 204));
        txtBuscarAgregarPedidoRemito.setText("Buscar Pedido...");
        txtBuscarAgregarPedidoRemito.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarAgregarPedidoRemitoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarAgregarPedidoRemitoFocusLost(evt);
            }
        });
        txtBuscarAgregarPedidoRemito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarAgregarPedidoRemitoKeyReleased(evt);
            }
        });

        tblPedido.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Pedido", "Fecha", "Cliente", "Direccion", "Contenedores", "Dias"
            }
        ));
        tblPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPedidoMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblPedido);

        btnFiltarPedidosRemito.setBackground(new java.awt.Color(229, 232, 232));
        btnFiltarPedidosRemito.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        btnFiltarPedidosRemito.setText("FILTRAR");
        btnFiltarPedidosRemito.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFiltarPedidosRemito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltarPedidosRemitoActionPerformed(evt);
            }
        });

        dtPedidoRemitoHasta.setDateFormatString("yyyy-MM-dd");
        dtPedidoRemitoHasta.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel51.setText("Hasta");

        dtPedidoRemitoDesde.setDateFormatString("yyyy-MM-dd");
        dtPedidoRemitoDesde.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel40.setText("Desde");

        btnCargarRemito.setBackground(new java.awt.Color(102, 255, 102));
        btnCargarRemito.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        btnCargarRemito.setText("CARGAR REMITO");
        btnCargarRemito.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCargarRemito.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCargarRemito.setIconTextGap(12);
        btnCargarRemito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarRemitoActionPerformed(evt);
            }
        });

        tblRemito.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblRemito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Remito", "Fecha", "Forma de Pago", "Estado", "Contenedor", "Dias", "Contenedores", "Pedido", "Camion", "Importe"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblRemito);

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel41.setText("Desde");

        jLabel52.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel52.setText("Hasta");

        txtBuscarAgregarRemito.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtBuscarAgregarRemito.setForeground(new java.awt.Color(204, 204, 204));
        txtBuscarAgregarRemito.setText("Buscar Remito...");
        txtBuscarAgregarRemito.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarAgregarRemitoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarAgregarRemitoFocusLost(evt);
            }
        });
        txtBuscarAgregarRemito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarAgregarRemitoKeyReleased(evt);
            }
        });

        btnFiltarRemitos.setBackground(new java.awt.Color(229, 232, 232));
        btnFiltarRemitos.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        btnFiltarRemitos.setText("FILTRAR");
        btnFiltarRemitos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFiltarRemitos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltarRemitosActionPerformed(evt);
            }
        });

        dtRemitoDesde.setDateFormatString("yyyy-MM-dd");
        dtRemitoDesde.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        dtRemitoHasta.setDateFormatString("yyyy-MM-dd");
        dtRemitoHasta.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\search.png")); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\search.png")); // NOI18N

        javax.swing.GroupLayout AgregarRemitoLayout = new javax.swing.GroupLayout(AgregarRemito);
        AgregarRemito.setLayout(AgregarRemitoLayout);
        AgregarRemitoLayout.setHorizontalGroup(
            AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AgregarRemitoLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AgregarRemitoLayout.createSequentialGroup()
                        .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AgregarRemitoLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboCodContenedor1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtRemitoContenedores, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtRemitoDias, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboEstadoRemito, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboCamion, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(AgregarRemitoLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtidPedidoRemito, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jLabel48)
                    .addComponent(jLabel24)
                    .addComponent(jLabel23)
                    .addComponent(btnCargarRemito)
                    .addComponent(jLabel46)
                    .addComponent(jLabel49)
                    .addComponent(jLabel34)
                    .addComponent(jLabel47))
                .addGap(54, 54, 54)
                .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AgregarRemitoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(108, 108, 108))
                    .addGroup(AgregarRemitoLayout.createSequentialGroup()
                        .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 767, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(AgregarRemitoLayout.createSequentialGroup()
                                .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel40)
                                    .addComponent(jLabel1))
                                .addGap(21, 21, 21)
                                .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBuscarAgregarPedidoRemito, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(AgregarRemitoLayout.createSequentialGroup()
                                        .addComponent(dtPedidoRemitoDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel51)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dtPedidoRemitoHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(btnFiltarPedidosRemito))))
                            .addGroup(AgregarRemitoLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(26, 26, 26)
                                .addComponent(txtBuscarAgregarRemito, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(AgregarRemitoLayout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dtRemitoDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel52)
                                .addGap(2, 2, 2)
                                .addComponent(dtRemitoHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnFiltarRemitos)))
                        .addContainerGap(49, Short.MAX_VALUE))))
        );
        AgregarRemitoLayout.setVerticalGroup(
            AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AgregarRemitoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtidPedidoRemito, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addComponent(txtBuscarAgregarPedidoRemito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(dtPedidoRemitoHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel51)
                        .addComponent(btnFiltarPedidosRemito, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dtPedidoRemitoDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel40))
                    .addGroup(AgregarRemitoLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(txtFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(AgregarRemitoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtBuscarAgregarRemito, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel47)
                                    .addComponent(txtRemitoDias, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel2))))
                    .addGroup(AgregarRemitoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(cboFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(txtRemitoContenedores, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(cboCodContenedor1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(72, 72, 72)))
                .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AgregarRemitoLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel41)
                                .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(dtRemitoHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel52)
                                    .addComponent(dtRemitoDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnFiltarRemitos, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(46, Short.MAX_VALUE))
                    .addGroup(AgregarRemitoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboEstadoRemito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34))
                        .addGap(27, 27, 27)
                        .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46)
                            .addComponent(cboCamion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(AgregarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel49)
                            .addComponent(lblImporte))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCargarRemito, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
        );

        ModificarRemito.setColor1(new java.awt.Color(255, 168, 11));
        ModificarRemito.setColor2(new java.awt.Color(255, 168, 11));

        txtBuscarModificarRemito.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtBuscarModificarRemito.setForeground(new java.awt.Color(204, 204, 204));
        txtBuscarModificarRemito.setText("Buscar Remito...");
        txtBuscarModificarRemito.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarModificarRemitoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarModificarRemitoFocusLost(evt);
            }
        });
        txtBuscarModificarRemito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarModificarRemitoKeyReleased(evt);
            }
        });

        tblModificarRemito.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblModificarRemito.setModel(new javax.swing.table.DefaultTableModel(
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
        tblModificarRemito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblModificarRemitoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblModificarRemito);

        btnModificar.setBackground(new java.awt.Color(255, 255, 0));
        btnModificar.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\update.png")); // NOI18N
        btnModificar.setText("GRABAR");
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificar.setHideActionText(true);
        btnModificar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnModificar.setIconTextGap(6);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("ID REMITO");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("FECHA");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("FORMA DE PAGO");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("ESTADO REMITO");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("CONTENEDOR");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setText("ID PEDIDO");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setText("IMPORTE");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setText("CAMION");

        cboCamion1.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        txtImporte.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtIdPedido.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtIdRemito.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        dtFechaEntrega.setDateFormatString("yyyy-MM-dd");
        dtFechaEntrega.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cboFormaPago1.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        cboEstadoRemito1.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        cboContenedor.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\search.png")); // NOI18N

        dtDesde.setDateFormatString("yyyy-MM-dd");
        dtDesde.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setText("Hasta");

        dtHasta.setDateFormatString("yyyy-MM-dd");
        dtHasta.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        btnFiltrar.setBackground(new java.awt.Color(229, 232, 232));
        btnFiltrar.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        btnFiltrar.setText("FILTRAR");
        btnFiltrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setText("Desde");

        javax.swing.GroupLayout ModificarRemitoLayout = new javax.swing.GroupLayout(ModificarRemito);
        ModificarRemito.setLayout(ModificarRemitoLayout);
        ModificarRemitoLayout.setHorizontalGroup(
            ModificarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ModificarRemitoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ModificarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ModificarRemitoLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIdRemito, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13))
                .addGap(61, 61, 61)
                .addGroup(ModificarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboCamion1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ModificarRemitoLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtIdPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cboFormaPago1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboEstadoRemito1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(ModificarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ModificarRemitoLayout.createSequentialGroup()
                        .addGroup(ModificarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ModificarRemitoLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ModificarRemitoLayout.createSequentialGroup()
                                .addGap(213, 213, 213)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBuscarModificarRemito, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ModificarRemitoLayout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 55, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ModificarRemitoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        ModificarRemitoLayout.setVerticalGroup(
            ModificarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ModificarRemitoLayout.createSequentialGroup()
                .addGroup(ModificarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ModificarRemitoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(ModificarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel15)
                            .addComponent(txtIdRemito, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ModificarRemitoLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(ModificarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtBuscarModificarRemito, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))))
                .addGroup(ModificarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ModificarRemitoLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(ModificarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(ModificarRemitoLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(49, 49, 49)
                                .addGroup(ModificarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(cboFormaPago1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(51, 51, 51)
                                .addComponent(jLabel9))
                            .addGroup(ModificarRemitoLayout.createSequentialGroup()
                                .addComponent(dtFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cboEstadoRemito1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(48, 48, 48)
                        .addGroup(ModificarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(cboContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(ModificarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(ModificarRemitoLayout.createSequentialGroup()
                                .addComponent(cboCamion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ModificarRemitoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(ModificarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(ModificarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ModificarRemitoLayout.createSequentialGroup()
                                .addGroup(ModificarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(ModificarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(dtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(ModificarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(dtHasta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel18)
                                    .addComponent(btnFiltrar))
                                .addGap(38, 38, 38)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        EliminarRemito.setColor1(new java.awt.Color(255, 168, 11));
        EliminarRemito.setColor2(new java.awt.Color(255, 168, 11));

        tblEliminarRemito.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblEliminarRemito.setModel(new javax.swing.table.DefaultTableModel(
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
        tblEliminarRemito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEliminarRemitoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblEliminarRemito);

        txtIdEliminarRemito.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setText("ID REMITO");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setText("ID PEDIDO");

        txtIdEliminarRemitoPedido.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        btnEliminar.setBackground(new java.awt.Color(217, 83, 79));
        btnEliminar.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\delete.png")); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEliminar.setIconTextGap(6);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        txtBuscarEliminarRemito.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtBuscarEliminarRemito.setForeground(new java.awt.Color(204, 204, 204));
        txtBuscarEliminarRemito.setText("Buscar Remito...");
        txtBuscarEliminarRemito.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarEliminarRemitoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarEliminarRemitoFocusLost(evt);
            }
        });
        txtBuscarEliminarRemito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarEliminarRemitoKeyReleased(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel21.setText("Desde");

        dtDesde1.setDateFormatString("yyyy-MM-dd");
        dtDesde1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel25.setText("Hasta");

        dtHasta1.setDateFormatString("yyyy-MM-dd");
        dtHasta1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        btnFiltrar1.setBackground(new java.awt.Color(229, 232, 232));
        btnFiltrar1.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        btnFiltrar1.setText("FILTRAR");
        btnFiltrar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFiltrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrar1ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel26.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\search.png")); // NOI18N

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel28.setText("CONTENEDOR");

        cboEliminarContenedor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout EliminarRemitoLayout = new javax.swing.GroupLayout(EliminarRemito);
        EliminarRemito.setLayout(EliminarRemitoLayout);
        EliminarRemitoLayout.setHorizontalGroup(
            EliminarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EliminarRemitoLayout.createSequentialGroup()
                .addGroup(EliminarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EliminarRemitoLayout.createSequentialGroup()
                        .addGroup(EliminarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EliminarRemitoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(EliminarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(EliminarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(EliminarRemitoLayout.createSequentialGroup()
                                        .addComponent(txtIdEliminarRemitoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(dtDesde1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46)
                                        .addComponent(jLabel25)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(dtHasta1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(btnFiltrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(EliminarRemitoLayout.createSequentialGroup()
                                        .addComponent(txtIdEliminarRemito, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(44, 44, 44)
                                        .addComponent(jLabel28)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cboEliminarContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                                        .addComponent(jLabel26)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtBuscarEliminarRemito, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(206, 206, 206))))
                            .addGroup(EliminarRemitoLayout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(btnEliminar)))
                        .addGap(81, 81, 81))
                    .addGroup(EliminarRemitoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2)))
                .addContainerGap())
        );
        EliminarRemitoLayout.setVerticalGroup(
            EliminarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EliminarRemitoLayout.createSequentialGroup()
                .addGroup(EliminarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EliminarRemitoLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(EliminarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboEliminarContenedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(EliminarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtIdEliminarRemito, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel28)
                                .addComponent(jLabel19)))
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EliminarRemitoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(EliminarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBuscarEliminarRemito, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addGap(18, 18, 18)))
                .addGroup(EliminarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(EliminarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(txtIdEliminarRemitoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(EliminarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(EliminarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dtDesde1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(EliminarRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel25)
                            .addComponent(dtHasta1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFiltrar1))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout bgRemitoLayout = new javax.swing.GroupLayout(bgRemito);
        bgRemito.setLayout(bgRemitoLayout);
        bgRemitoLayout.setHorizontalGroup(
            bgRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AgregarRemito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(bgRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ModificarRemito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(bgRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(EliminarRemito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bgRemitoLayout.setVerticalGroup(
            bgRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AgregarRemito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(bgRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ModificarRemito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(bgRemitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(EliminarRemito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHeaderRemito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bgRemito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelHeaderRemito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bgRemito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblAgregarRemtoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAgregarRemtoMouseClicked
        EliminarRemito.setVisible(false);
        AgregarRemito.setVisible(true);
        ModificarRemito.setVisible(false);

    }//GEN-LAST:event_lblAgregarRemtoMouseClicked

    private void lblModificarRemitoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblModificarRemitoMouseClicked
        EliminarRemito.setVisible(false);
        AgregarRemito.setVisible(false);
        ModificarRemito.setVisible(true);
    }//GEN-LAST:event_lblModificarRemitoMouseClicked

    private void lblEliminarRemitoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEliminarRemitoMouseClicked
        EliminarRemito.setVisible(true);
        AgregarRemito.setVisible(false);
        ModificarRemito.setVisible(false);
    }//GEN-LAST:event_lblEliminarRemitoMouseClicked

    private void lblImprimirRemitoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImprimirRemitoMouseClicked
        VentanaImprimir vi = new VentanaImprimir();
        vi.setVisible(true);
    }//GEN-LAST:event_lblImprimirRemitoMouseClicked

    private void txtBuscarAgregarPedidoRemitoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarAgregarPedidoRemitoFocusGained
        if (txtBuscarAgregarPedidoRemito.getText().equals("Buscar Pedido...")) {
            txtBuscarAgregarPedidoRemito.setText(null);
            txtBuscarAgregarPedidoRemito.requestFocus();
            removePlaceHolder(txtBuscarAgregarPedidoRemito);
        }
    }//GEN-LAST:event_txtBuscarAgregarPedidoRemitoFocusGained

    private void txtBuscarAgregarPedidoRemitoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarAgregarPedidoRemitoFocusLost
        if (txtBuscarAgregarPedidoRemito.getText().length() == 0) {
            addPlaceHolder(txtBuscarAgregarPedidoRemito);
            txtBuscarAgregarPedidoRemito.setText("Buscar Pedido...");
        }
    }//GEN-LAST:event_txtBuscarAgregarPedidoRemitoFocusLost

    private void txtBuscarAgregarPedidoRemitoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarAgregarPedidoRemitoKeyReleased
        String query = txtBuscarAgregarPedidoRemito.getText();
        filterPedidoRemito(query);
    }//GEN-LAST:event_txtBuscarAgregarPedidoRemitoKeyReleased

    private void tblPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPedidoMouseClicked
        btnCargarRemito.setEnabled(true);
        int selectedRow = tblPedido.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tblPedido.getModel();
        txtidPedidoRemito.setText(model.getValueAt(selectedRow, 0).toString());
        txtFechaEntrega.setText(model.getValueAt(selectedRow, 1).toString());
        txtRemitoContenedores.setText(model.getValueAt(selectedRow, 4).toString());
        txtRemitoDias.setText(model.getValueAt(selectedRow, 5).toString());
        calcularImporte();
    }//GEN-LAST:event_tblPedidoMouseClicked

    private void btnFiltarPedidosRemitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltarPedidosRemitoActionPerformed
        try {
            buscarEntreFechaPedidoRemito();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnFiltarPedidosRemitoActionPerformed

    private void btnCargarRemitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarRemitoActionPerformed
        try {
            FormaPago fPago = (FormaPago) cboFormaPago.getSelectedItem();
            Contenedor contenedor = (Contenedor) cboCodContenedor1.getSelectedItem();
            EstadoRemito estado = (EstadoRemito) cboEstadoRemito.getSelectedItem();
            Camion camion = (Camion) cboCamion.getSelectedItem();
            String fecha = txtFechaEntrega.getText();
            int idPedido = Integer.parseInt(txtidPedidoRemito.getText());
            int cant = Integer.parseInt(txtRemitoContenedores.getText());
            int dias = Integer.parseInt(txtRemitoDias.getText());

            Remito r = new Remito(fPago.getIdFormaPago(), fecha, contenedor.getIdContenedor(), estado.getIdEstadoRemito(), idPedido, camion.getIdCamion());
            g.agregarRemito(r);

            Contenedor c = new Contenedor(contenedor.getCodigoContenedor());
            g.modificarContenedorDisponilbe(c);
            //-------------------
            double importe = Double.parseDouble(lblImporte.getText());
            listadoFactura f = new listadoFactura(importe, idPedido);
            g.agregarImporte(f);
            lblImporte.setText("0");
            //------------------
            cargarTablaRemito();
            cargarCodigoContenedor();
            JOptionPane.showMessageDialog(this, "Carga de Remito Exitosa");
            txtidPedidoRemito.setText("");
            txtFechaEntrega.setText("");
            cboFormaPago.setSelectedIndex(0);
            cboCodContenedor1.setSelectedIndex(0);
            cboEstadoRemito.setSelectedIndex(0);
            cboCamion.setSelectedIndex(0);
            txtRemitoContenedores.setText("");
            txtRemitoDias.setText("");
            cargarTablaEliminarRemito();
            cargarTablaModificarRemito();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Revise los campos");
        }
        //------------------
//        if (tblPedido.getSelectedRow() == -1) {
//            return;
//        }
//        modeloAgregarRemito.removeRow(tblPedido.getSelectedRow());
    }//GEN-LAST:event_btnCargarRemitoActionPerformed

    private void txtBuscarAgregarRemitoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarAgregarRemitoFocusGained
        if (txtBuscarAgregarRemito.getText().equals("Buscar Remito...")) {
            txtBuscarAgregarRemito.setText(null);
            txtBuscarAgregarRemito.requestFocus();
            removePlaceHolder(txtBuscarAgregarRemito);
        }
    }//GEN-LAST:event_txtBuscarAgregarRemitoFocusGained

    private void txtBuscarAgregarRemitoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarAgregarRemitoFocusLost
        if (txtBuscarAgregarRemito.getText().length() == 0) {
            addPlaceHolder(txtBuscarAgregarRemito);
            txtBuscarAgregarRemito.setText("Buscar Remito...");
        }
    }//GEN-LAST:event_txtBuscarAgregarRemitoFocusLost

    private void txtBuscarAgregarRemitoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarAgregarRemitoKeyReleased
        String query = txtBuscarAgregarRemito.getText();
        filterRemito(query);
    }//GEN-LAST:event_txtBuscarAgregarRemitoKeyReleased

    private void btnFiltarRemitosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltarRemitosActionPerformed
        try {
            buscarEntreFechaRemito();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnFiltarRemitosActionPerformed

    private void txtBuscarModificarRemitoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarModificarRemitoFocusGained
        if (txtBuscarModificarRemito.getText().equals("Buscar Remito...")) {
            txtBuscarModificarRemito.setText(null);
            txtBuscarModificarRemito.requestFocus();
            removePlaceHolder(txtBuscarModificarRemito);
        }
    }//GEN-LAST:event_txtBuscarModificarRemitoFocusGained

    private void txtBuscarModificarRemitoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarModificarRemitoFocusLost
        if (txtBuscarModificarRemito.getText().length() == 0) {
            addPlaceHolder(txtBuscarModificarRemito);
            txtBuscarModificarRemito.setText("Buscar Remito...");
        }
    }//GEN-LAST:event_txtBuscarModificarRemitoFocusLost

    private void txtBuscarModificarRemitoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarModificarRemitoKeyReleased
        String query = txtBuscarModificarRemito.getText();
        filterModificarRemito(query);
    }//GEN-LAST:event_txtBuscarModificarRemitoKeyReleased

    private void tblModificarRemitoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblModificarRemitoMouseClicked
        btnModificar.setEnabled(true);
        try {
            int selectedRow = tblModificarRemito.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tblModificarRemito.getModel();
            txtIdRemito.setText(model.getValueAt(selectedRow, 0).toString());

            Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(selectedRow, 1));
            dtFechaEntrega.setDate(fecha);

            String pago = model.getValueAt(selectedRow, 2).toString();
            switch (pago) {
                case "Tarjeta Debito":
                    cboFormaPago1.setSelectedIndex(1);
                    break;
                case "Tarjeta Credito":
                    cboFormaPago1.setSelectedIndex(2);
                    break;
                case "Contado":
                    cboFormaPago1.setSelectedIndex(3);
                    break;
                case "Transferencia":
                    cboFormaPago1.setSelectedIndex(4);
                    break;
                case "Cheque":
                    cboFormaPago1.setSelectedIndex(5);
                    break;
            }
            String estado = model.getValueAt(selectedRow, 3).toString();
            switch (estado) {
                case "Pagado":
                    cboEstadoRemito1.setSelectedIndex(1);
                    break;
                case "Debe":
                    cboEstadoRemito1.setSelectedIndex(2);
                    break;
            }
            String cont = model.getValueAt(selectedRow, 4).toString();
            switch (cont) {
                case "CTG01":
                    cboContenedor.setSelectedIndex(1);
                    break;
                case "CTG02":
                    cboContenedor.setSelectedIndex(2);
                    break;
                case "CTG03":
                    cboContenedor.setSelectedIndex(3);
                    break;
                case "CTG04":
                    cboContenedor.setSelectedIndex(4);
                    break;
                case "CTG05":
                    cboContenedor.setSelectedIndex(5);
                    break;
                case "CTG06":
                    cboContenedor.setSelectedIndex(6);
                    break;
                case "CTG07":
                    cboContenedor.setSelectedIndex(7);
                    break;
                case "CTG08":
                    cboContenedor.setSelectedIndex(8);
                    break;
                case "CTG09":
                    cboContenedor.setSelectedIndex(9);
                    break;
                case "CTG010":
                    cboContenedor.setSelectedIndex(10);
                    break;
            }

            String camion = model.getValueAt(selectedRow, 8).toString();
            switch (camion) {
                case "1":
                    cboCamion1.setSelectedIndex(1);
                    break;
                case "2":
                    cboCamion1.setSelectedIndex(2);
                    break;
            }
            txtIdPedido.setText(model.getValueAt(selectedRow, 9).toString());
            txtImporte.setText(model.getValueAt(selectedRow, 10).toString());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tblModificarRemitoMouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {
            int idRemito = Integer.parseInt(txtIdRemito.getText());
            FormaPago pago = (FormaPago) cboFormaPago1.getSelectedItem();
            Contenedor contenedor = (Contenedor) cboContenedor.getSelectedItem();
            EstadoRemito estado = (EstadoRemito) cboEstadoRemito1.getSelectedItem();
            Camion camion = (Camion) cboCamion1.getSelectedItem();
            double importe = Double.parseDouble(txtImporte.getText());
            int idPedido = Integer.parseInt(txtIdPedido.getText());

            Remito r = new Remito(idRemito, pago.getIdFormaPago(), ((JTextField) dtFechaEntrega.getDateEditor().getUiComponent()).getText(), contenedor.getIdContenedor(), estado.getIdEstadoRemito(), camion.getIdCamion());
            g.modificarRemito(r);
            
            listadoFactura lf = new listadoFactura(importe,idPedido);
            g.modificarImporte(lf);
            //---
            cargarTablaRemito();
            cargarTablaModificarRemito();
            cargarTablaEliminarRemito();
            btnModificar.setEnabled(false);
            txtIdRemito.setText("");
            txtIdPedido.setText("");
            cboFormaPago1.setSelectedIndex(0);
            cboEstadoRemito1.setSelectedIndex(0);
            cboContenedor.setSelectedIndex(0);
            cboCamion1.setSelectedIndex(0);
            txtImporte.setText("");

            JOptionPane.showMessageDialog(this, "Remito Modificado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Revise los campos");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        try {
            buscarEntreFechaModificarRemito();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void tblEliminarRemitoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEliminarRemitoMouseClicked
        btnEliminar.setEnabled(true);
        try {
            int selectedRow = tblEliminarRemito.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tblEliminarRemito.getModel();
            txtIdEliminarRemito.setText(model.getValueAt(selectedRow, 0).toString());
            String cont = model.getValueAt(selectedRow, 4).toString();
            switch (cont) {
                case "CTG01":
                    cboEliminarContenedor.setSelectedIndex(1);
                    break;
                case "CTG02":
                    cboEliminarContenedor.setSelectedIndex(2);
                    break;
                case "CTG03":
                    cboEliminarContenedor.setSelectedIndex(3);
                    break;
                case "CTG04":
                    cboEliminarContenedor.setSelectedIndex(4);
                    break;
                case "CTG05":
                    cboEliminarContenedor.setSelectedIndex(5);
                    break;
                case "CTG06":
                    cboEliminarContenedor.setSelectedIndex(6);
                    break;
                case "CTG07":
                    cboEliminarContenedor.setSelectedIndex(7);
                    break;
                case "CTG08":
                    cboEliminarContenedor.setSelectedIndex(8);
                    break;
                case "CTG09":
                    cboEliminarContenedor.setSelectedIndex(9);
                    break;
                case "CTG010":
                    cboEliminarContenedor.setSelectedIndex(10);
                    break;
            }
            txtIdEliminarRemitoPedido.setText(model.getValueAt(selectedRow, 9).toString());

        } catch (Exception e) {
        }
    }//GEN-LAST:event_tblEliminarRemitoMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        Contenedor contenedor = (Contenedor) cboEliminarContenedor.getSelectedItem();
        int response = JOptionPane.showConfirmDialog(this, "Esta seguro de eliminar el Remito?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {

            try {
                abrirConexion();
                pst = con.prepareStatement("DELETE FROM Remito WHERE idRemito=?");
                pst.setInt(1, Integer.parseInt(txtIdEliminarRemito.getText()));

                int res = pst.executeUpdate();

                if (res > 0) {

                    cargarTablaRemito();
                    cargarTablaModificarRemito();
                    cargarTablaEliminarRemito();
                    cargarCodigoContenedor();
                    txtIdEliminarRemito.setText("");
                    txtIdEliminarRemitoPedido.setText("");
                    btnEliminar.setEnabled(false);

                }
                con.close();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Seleccionar un Remito");
            }

            Contenedor c = new Contenedor(contenedor.getCodigoContenedor());
            g.modificarContenedorNoDisponible(c);

        } else if (response == JOptionPane.NO_OPTION) {

        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtBuscarEliminarRemitoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarEliminarRemitoFocusGained
        if (txtBuscarEliminarRemito.getText().equals("Buscar Remito...")) {
            txtBuscarEliminarRemito.setText(null);
            txtBuscarEliminarRemito.requestFocus();
            removePlaceHolder(txtBuscarEliminarRemito);
        }
    }//GEN-LAST:event_txtBuscarEliminarRemitoFocusGained

    private void txtBuscarEliminarRemitoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarEliminarRemitoFocusLost
        if (txtBuscarEliminarRemito.getText().length() == 0) {
            addPlaceHolder(txtBuscarEliminarRemito);
            txtBuscarEliminarRemito.setText("Buscar Remito...");
        }
    }//GEN-LAST:event_txtBuscarEliminarRemitoFocusLost

    private void txtBuscarEliminarRemitoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarEliminarRemitoKeyReleased
        String query = txtBuscarEliminarRemito.getText();
        filterEliminarRemito(query);
    }//GEN-LAST:event_txtBuscarEliminarRemitoKeyReleased

    private void btnFiltrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrar1ActionPerformed
        try {
            buscarEntreFechaEliminarRemito();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnFiltrar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private PanelesBG.BackGround AgregarRemito;
    private PanelesBG.BackGround EliminarRemito;
    private PanelesBG.BackGround ModificarRemito;
    private PanelesBG.BackGround backGround1;
    private PanelesBG.BackGround backGround2;
    private PanelesBG.BackGround backGround3;
    private PanelesBG.BackGround backGround4;
    private javax.swing.JPanel bgRemito;
    private javax.swing.JButton btnCargarRemito;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnFiltarPedidosRemito;
    private javax.swing.JButton btnFiltarRemitos;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnFiltrar1;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cboCamion;
    private javax.swing.JComboBox<String> cboCamion1;
    private javax.swing.JComboBox<String> cboCodContenedor1;
    private javax.swing.JComboBox<String> cboContenedor;
    private javax.swing.JComboBox<String> cboEliminarContenedor;
    private javax.swing.JComboBox<String> cboEstadoRemito;
    private javax.swing.JComboBox<String> cboEstadoRemito1;
    private javax.swing.JComboBox<String> cboFormaPago;
    private javax.swing.JComboBox<String> cboFormaPago1;
    private com.toedter.calendar.JDateChooser dtDesde;
    private com.toedter.calendar.JDateChooser dtDesde1;
    private com.toedter.calendar.JDateChooser dtFechaEntrega;
    private com.toedter.calendar.JDateChooser dtHasta;
    private com.toedter.calendar.JDateChooser dtHasta1;
    private com.toedter.calendar.JDateChooser dtPedidoRemitoDesde;
    private com.toedter.calendar.JDateChooser dtPedidoRemitoHasta;
    private com.toedter.calendar.JDateChooser dtRemitoDesde;
    private com.toedter.calendar.JDateChooser dtRemitoHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblAgregarRemto;
    private javax.swing.JLabel lblEliminarRemito;
    private javax.swing.JLabel lblImporte;
    private javax.swing.JLabel lblImprimirRemito;
    private javax.swing.JLabel lblModificarRemito;
    private javax.swing.JPanel panelHeaderRemito;
    private javax.swing.JTable tblEliminarRemito;
    private javax.swing.JTable tblModificarRemito;
    private javax.swing.JTable tblPedido;
    private javax.swing.JTable tblRemito;
    private javax.swing.JTextField txtBuscarAgregarPedidoRemito;
    private javax.swing.JTextField txtBuscarAgregarRemito;
    private javax.swing.JTextField txtBuscarEliminarRemito;
    private javax.swing.JTextField txtBuscarModificarRemito;
    private javax.swing.JTextField txtFechaEntrega;
    private javax.swing.JTextField txtIdEliminarRemito;
    private javax.swing.JTextField txtIdEliminarRemitoPedido;
    private javax.swing.JTextField txtIdPedido;
    private javax.swing.JTextField txtIdRemito;
    private javax.swing.JTextField txtImporte;
    private javax.swing.JTextField txtRemitoContenedores;
    private javax.swing.JTextField txtRemitoDias;
    private javax.swing.JTextField txtidPedidoRemito;
    // End of variables declaration//GEN-END:variables
}
