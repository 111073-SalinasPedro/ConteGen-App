package Paneles;

import DTO.listadoClienteHabitual;
import GestorBD.GestorBD;
import Model.*;
import Ventana.VentanaReporteCliente;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class PanelCliente extends javax.swing.JPanel {

    GestorBD g;
    PreparedStatement pst = null;
    ResultSet st = null;
    private String CONN = "jdbc:sqlserver://localhost:1433;databaseName=Contegen";
    private String USER = "sa";
    private String PASS = "123456";
    private Connection con;
    DefaultTableModel modeloListadoAgregarCliente = new DefaultTableModel();
    DefaultTableModel modeloListadoModificarCliente = new DefaultTableModel();
    DefaultTableModel modeloListadoEliminarCliente = new DefaultTableModel();

    public PanelCliente() {
        initComponents();
        g = new GestorBD();
        //---
        modeloListadoAgregarCliente.addColumn("Nombre");
        modeloListadoAgregarCliente.addColumn("Documento");
        modeloListadoAgregarCliente.addColumn("Telefono");
        tblAgregarCliente.setModel(modeloListadoAgregarCliente);
        cargarTablaAgregarCliente();
        cargarListaClienteHabitual();
        //---
        modeloListadoModificarCliente.addColumn("ID");
        modeloListadoModificarCliente.addColumn("Cliente");
        modeloListadoModificarCliente.addColumn("Documento");
        modeloListadoModificarCliente.addColumn("Telefono");
        tblModificarCliente.setModel(modeloListadoAgregarCliente);
        cargarTablaModificarCliente();
        //---
        modeloListadoEliminarCliente.addColumn("ID");
        modeloListadoEliminarCliente.addColumn("Cliente");
        modeloListadoEliminarCliente.addColumn("Documento");
        modeloListadoEliminarCliente.addColumn("Telefono");
        tblEliminarCliente.setModel(modeloListadoEliminarCliente);
        cargarTablaEliminarCliente();
        //---
        txtNombre1.setEditable(false);
        txtDni1.setEditable(false);
        txtId1.setEditable(false);
        btnEliminar.setEnabled(false);
        btnModificar.setEnabled(false);
        panelEliminarCliente.setVisible(false);
        panelModificarCliente.setVisible(false);
        //---
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); //Alinear Texto Centrado
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int x = 0; x < 3; x++) {
            tblAgregarCliente.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }
        tblAgregarCliente.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblAgregarCliente.getColumnModel().getColumn(1).setPreferredWidth(20);
        tblAgregarCliente.getColumnModel().getColumn(2).setPreferredWidth(40);
        //---
        for (int x = 0; x < 4; x++) {
            tblModificarCliente.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }
        tblModificarCliente.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblModificarCliente.getColumnModel().getColumn(1).setPreferredWidth(30);
        tblModificarCliente.getColumnModel().getColumn(2).setPreferredWidth(40);
        tblModificarCliente.getColumnModel().getColumn(2).setPreferredWidth(40);
        //--
        for (int x = 0; x < 4; x++) {
            tblEliminarCliente.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }
        tblEliminarCliente.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblEliminarCliente.getColumnModel().getColumn(1).setPreferredWidth(30);
        tblEliminarCliente.getColumnModel().getColumn(2).setPreferredWidth(40);
        tblEliminarCliente.getColumnModel().getColumn(2).setPreferredWidth(40);

    }

    private void abrirConexion() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            con = DriverManager.getConnection(CONN, USER, PASS);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //----AgregarCliente
    private void cargarTablaAgregarCliente() {
        ArrayList<Cliente> lista = g.listadoCliente();
        modeloListadoAgregarCliente.setRowCount(0);
        for (Cliente c : lista) {
            modeloListadoAgregarCliente.addRow(new Object[]{
                c.getNombreCliente(),
                c.getDniCliente(),
                c.getTelefonoCliente()});
        }
        tblAgregarCliente.setModel(modeloListadoAgregarCliente);
    }

    private void cargarListaClienteHabitual() {

        ArrayList<listadoClienteHabitual> lista = g.listadoClienteHabitual();
        DefaultListModel<listadoClienteHabitual> model = new DefaultListModel<listadoClienteHabitual>();
        for (listadoClienteHabitual h : lista) {
            model.addElement(h);
        }
        lstClienteHabitual.setModel(model);

    }

    private void filterAgregarCliente(String query) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modeloListadoAgregarCliente);
        tblAgregarCliente.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarAgregarCliente.getText(), 0));
    }

    //ModificarCliente
    private void cargarTablaModificarCliente() {
        ArrayList<Cliente> lista = g.listadoCliente();
        modeloListadoModificarCliente.setRowCount(0);
        for (Cliente c : lista) {
            modeloListadoModificarCliente.addRow(new Object[]{
                c.getIdCliente(),
                c.getNombreCliente(),
                c.getDniCliente(),
                c.getTelefonoCliente(),});
        }
        tblModificarCliente.setModel(modeloListadoModificarCliente);
    }

    private void filterModificarCliente(String query) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modeloListadoModificarCliente);
        tblModificarCliente.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarModificarCliente.getText(), 0));
    }

    //EliminarCliente
    private void cargarTablaEliminarCliente() {
        ArrayList<Cliente> lista = g.listadoCliente();
        modeloListadoEliminarCliente.setRowCount(0);
        for (Cliente c : lista) {
            modeloListadoEliminarCliente.addRow(new Object[]{
                c.getIdCliente(),
                c.getNombreCliente(),
                c.getDniCliente(),
                c.getTelefonoCliente()});
        }
        tblEliminarCliente.setModel(modeloListadoEliminarCliente);
    }

    private void filterEliminarCliente(String query) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modeloListadoEliminarCliente);
        tblEliminarCliente.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscarEliminarCliente.getText(), 0));
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

        panelOpciones = new javax.swing.JPanel();
        backGround1 = new PanelesBG.BackGround();
        lblAgregarCliente = new javax.swing.JLabel();
        backGround2 = new PanelesBG.BackGround();
        lblModificarCliente = new javax.swing.JLabel();
        backGround3 = new PanelesBG.BackGround();
        lblEliminarCliente = new javax.swing.JLabel();
        panelBg = new javax.swing.JPanel();
        panelAgregarCliente = new PanelesBG.Bg();
        txtNombreCliente = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        chkHabitual = new javax.swing.JCheckBox();
        txtTelefono = new javax.swing.JTextField();
        txtDocumento = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstClienteHabitual = new javax.swing.JList();
        btnCargarCliente = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAgregarCliente = new javax.swing.JTable();
        txtBuscarAgregarCliente = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnReporteCliente = new javax.swing.JButton();
        panelModificarCliente = new PanelesBG.Bg();
        jLabel7 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtDni = new javax.swing.JTextField();
        txtTelefono1 = new javax.swing.JTextField();
        chkHabitual1 = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblModificarCliente = new javax.swing.JTable();
        txtBuscarModificarCliente = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        panelEliminarCliente = new PanelesBG.Bg();
        jLabel11 = new javax.swing.JLabel();
        txtId1 = new javax.swing.JTextField();
        txtNombre1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtDni1 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblEliminarCliente = new javax.swing.JTable();
        txtBuscarEliminarCliente = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1100, 697));

        panelOpciones.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        backGround1.setColor1(new java.awt.Color(102, 102, 102));
        backGround1.setColor2(new java.awt.Color(92, 184, 92));

        lblAgregarCliente.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        lblAgregarCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblAgregarCliente.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\user_add.png")); // NOI18N
        lblAgregarCliente.setText("AGREGAR CLIENTE");
        lblAgregarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAgregarCliente.setIconTextGap(10);
        lblAgregarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAgregarClienteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout backGround1Layout = new javax.swing.GroupLayout(backGround1);
        backGround1.setLayout(backGround1Layout);
        backGround1Layout.setHorizontalGroup(
            backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backGround1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAgregarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                .addContainerGap())
        );
        backGround1Layout.setVerticalGroup(
            backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backGround1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAgregarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        backGround2.setColor1(new java.awt.Color(102, 102, 102));
        backGround2.setColor2(new java.awt.Color(240, 173, 78));

        lblModificarCliente.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        lblModificarCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblModificarCliente.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\user_edit.png")); // NOI18N
        lblModificarCliente.setText("MODIFICAR CLIENTE");
        lblModificarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblModificarCliente.setIconTextGap(10);
        lblModificarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblModificarClienteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout backGround2Layout = new javax.swing.GroupLayout(backGround2);
        backGround2.setLayout(backGround2Layout);
        backGround2Layout.setHorizontalGroup(
            backGround2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backGround2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblModificarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                .addContainerGap())
        );
        backGround2Layout.setVerticalGroup(
            backGround2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backGround2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblModificarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        backGround3.setColor1(new java.awt.Color(102, 102, 102));
        backGround3.setColor2(new java.awt.Color(217, 83, 79));

        lblEliminarCliente.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        lblEliminarCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblEliminarCliente.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\user_delete.png")); // NOI18N
        lblEliminarCliente.setText("ELIMINAR CLIENTE");
        lblEliminarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblEliminarCliente.setIconTextGap(10);
        lblEliminarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEliminarClienteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout backGround3Layout = new javax.swing.GroupLayout(backGround3);
        backGround3.setLayout(backGround3Layout);
        backGround3Layout.setHorizontalGroup(
            backGround3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backGround3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblEliminarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                .addContainerGap())
        );
        backGround3Layout.setVerticalGroup(
            backGround3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backGround3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEliminarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelOpcionesLayout = new javax.swing.GroupLayout(panelOpciones);
        panelOpciones.setLayout(panelOpcionesLayout);
        panelOpcionesLayout.setHorizontalGroup(
            panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcionesLayout.createSequentialGroup()
                .addComponent(backGround1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backGround2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backGround3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelOpcionesLayout.setVerticalGroup(
            panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backGround2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(backGround3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(backGround1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelAgregarCliente.setBackground(new java.awt.Color(255, 168, 11));
        panelAgregarCliente.setForeground(new java.awt.Color(255, 255, 255));

        txtNombreCliente.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        txtNombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreClienteKeyTyped(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setText("Nombre Cliente");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Documento");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Telefono");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setText("Habitual");

        txtTelefono.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        txtDocumento.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        txtDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDocumentoKeyTyped(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel19.setText("Clientes Habituales");

        lstClienteHabitual.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(lstClienteHabitual);

        btnCargarCliente.setBackground(new java.awt.Color(65, 241, 24));
        btnCargarCliente.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        btnCargarCliente.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\add.png")); // NOI18N
        btnCargarCliente.setText("CARGAR");
        btnCargarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCargarCliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCargarCliente.setIconTextGap(10);
        btnCargarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarClienteActionPerformed(evt);
            }
        });

        tblAgregarCliente.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblAgregarCliente.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblAgregarCliente);

        txtBuscarAgregarCliente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtBuscarAgregarCliente.setForeground(new java.awt.Color(204, 204, 204));
        txtBuscarAgregarCliente.setText("Buscar Cliente...");
        txtBuscarAgregarCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarAgregarClienteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarAgregarClienteFocusLost(evt);
            }
        });
        txtBuscarAgregarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarAgregarClienteKeyReleased(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\search.png")); // NOI18N

        btnReporteCliente.setBackground(new java.awt.Color(102, 153, 255));
        btnReporteCliente.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        btnReporteCliente.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\report.png")); // NOI18N
        btnReporteCliente.setText("REPORTE");
        btnReporteCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReporteCliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnReporteCliente.setIconTextGap(10);
        btnReporteCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAgregarClienteLayout = new javax.swing.GroupLayout(panelAgregarCliente);
        panelAgregarCliente.setLayout(panelAgregarClienteLayout);
        panelAgregarClienteLayout.setHorizontalGroup(
            panelAgregarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAgregarClienteLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panelAgregarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCargarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReporteCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(60, 60, 60)
                .addGroup(panelAgregarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkHabitual)
                    .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelAgregarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelAgregarClienteLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscarAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(60, 60, 60))
        );
        panelAgregarClienteLayout.setVerticalGroup(
            panelAgregarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgregarClienteLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(panelAgregarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAgregarClienteLayout.createSequentialGroup()
                        .addGroup(panelAgregarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBuscarAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelAgregarClienteLayout.createSequentialGroup()
                        .addGroup(panelAgregarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(panelAgregarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(panelAgregarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelAgregarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(chkHabitual))
                        .addGap(44, 44, 44)
                        .addComponent(btnCargarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReporteCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        panelModificarCliente.setBackground(new java.awt.Color(255, 168, 11));
        panelModificarCliente.setForeground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setText("Id");

        txtId.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtId.setEnabled(false);

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtDni.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniKeyTyped(evt);
            }
        });

        txtTelefono1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTelefono1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefono1KeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setText("Habitual");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Telefono");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Dni");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("Nombre");

        btnModificar.setBackground(new java.awt.Color(255, 255, 0));
        btnModificar.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\update.png")); // NOI18N
        btnModificar.setText("GRABAR");
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnModificar.setIconTextGap(10);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        tblModificarCliente.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblModificarCliente.setModel(new javax.swing.table.DefaultTableModel(
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
        tblModificarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblModificarClienteMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblModificarCliente);

        txtBuscarModificarCliente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtBuscarModificarCliente.setForeground(new java.awt.Color(204, 204, 204));
        txtBuscarModificarCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscarModificarCliente.setText("Buscar Cliente...");
        txtBuscarModificarCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarModificarClienteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarModificarClienteFocusLost(evt);
            }
        });
        txtBuscarModificarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarModificarClienteKeyReleased(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\search.png")); // NOI18N

        javax.swing.GroupLayout panelModificarClienteLayout = new javax.swing.GroupLayout(panelModificarCliente);
        panelModificarCliente.setLayout(panelModificarClienteLayout);
        panelModificarClienteLayout.setHorizontalGroup(
            panelModificarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelModificarClienteLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panelModificarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelModificarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(panelModificarClienteLayout.createSequentialGroup()
                            .addGroup(panelModificarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(118, 118, 118)
                            .addGroup(panelModificarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                .addComponent(txtDni)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panelModificarClienteLayout.createSequentialGroup()
                            .addGroup(panelModificarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(118, 118, 118)
                            .addGroup(panelModificarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(chkHabitual1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTelefono1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))))
                    .addComponent(btnModificar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addGroup(panelModificarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelModificarClienteLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscarModificarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(60, 60, 60))
        );
        panelModificarClienteLayout.setVerticalGroup(
            panelModificarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelModificarClienteLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(panelModificarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelModificarClienteLayout.createSequentialGroup()
                        .addGroup(panelModificarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelModificarClienteLayout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelModificarClienteLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtBuscarModificarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelModificarClienteLayout.createSequentialGroup()
                        .addGroup(panelModificarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(panelModificarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelModificarClienteLayout.createSequentialGroup()
                                .addGroup(panelModificarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addComponent(jLabel2)
                                .addGap(11, 11, 11)))
                        .addGap(18, 18, 18)
                        .addGroup(panelModificarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelModificarClienteLayout.createSequentialGroup()
                                .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(chkHabitual1))
                            .addGroup(panelModificarClienteLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel8)))
                        .addGap(36, 36, 36)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(118, Short.MAX_VALUE))
        );

        panelEliminarCliente.setBackground(new java.awt.Color(255, 168, 11));
        panelEliminarCliente.setForeground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setText("Id");

        txtId1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtNombre1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setText("Nombre");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setText("Dni");

        txtDni1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtDni1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDni1KeyTyped(evt);
            }
        });

        tblEliminarCliente.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblEliminarCliente.setModel(new javax.swing.table.DefaultTableModel(
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
        tblEliminarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEliminarClienteMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblEliminarCliente);

        txtBuscarEliminarCliente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtBuscarEliminarCliente.setForeground(new java.awt.Color(204, 204, 204));
        txtBuscarEliminarCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscarEliminarCliente.setText("Buscar Cliente...");
        txtBuscarEliminarCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarEliminarClienteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarEliminarClienteFocusLost(evt);
            }
        });
        txtBuscarEliminarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarEliminarClienteKeyReleased(evt);
            }
        });

        jLabel16.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\search.png")); // NOI18N

        btnEliminar.setBackground(new java.awt.Color(255, 51, 51));
        btnEliminar.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setIcon(new javax.swing.ImageIcon("C:\\Users\\WIN10\\Desktop\\Facultad\\PS\\Icons\\delete.png")); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEliminar.setIconTextGap(12);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelEliminarClienteLayout = new javax.swing.GroupLayout(panelEliminarCliente);
        panelEliminarCliente.setLayout(panelEliminarClienteLayout);
        panelEliminarClienteLayout.setHorizontalGroup(
            panelEliminarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEliminarClienteLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panelEliminarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEliminarClienteLayout.createSequentialGroup()
                        .addGroup(panelEliminarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(panelEliminarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtId1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                            .addComponent(txtDni1)))
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelEliminarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelEliminarClienteLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscarEliminarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(60, 60, 60))
        );
        panelEliminarClienteLayout.setVerticalGroup(
            panelEliminarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEliminarClienteLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(panelEliminarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEliminarClienteLayout.createSequentialGroup()
                        .addGroup(panelEliminarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtId1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(panelEliminarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelEliminarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtDni1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelEliminarClienteLayout.createSequentialGroup()
                        .addGroup(panelEliminarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelEliminarClienteLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(txtBuscarEliminarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(119, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBgLayout = new javax.swing.GroupLayout(panelBg);
        panelBg.setLayout(panelBgLayout);
        panelBgLayout.setHorizontalGroup(
            panelBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelAgregarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelModificarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelEliminarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBgLayout.setVerticalGroup(
            panelBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelAgregarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelModificarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelEliminarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteKeyTyped
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreClienteKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        int key = evt.getKeyChar();
        boolean numero = key >= 48 && key <= 57;
        if (!numero) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtDocumentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocumentoKeyTyped
        int key = evt.getKeyChar();
        boolean numero = key >= 48 && key <= 57;
        if (!numero) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDocumentoKeyTyped

    private void btnCargarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarClienteActionPerformed

        try {
            String nombre = txtNombreCliente.getText();
            int dni = Integer.parseInt(txtDocumento.getText());
            int telefono = Integer.parseInt(txtTelefono.getText());
            boolean habitual = chkHabitual.isSelected();

            Cliente c = new Cliente(nombre, dni, telefono, habitual);
            g.agregarCliente(c);
            cargarTablaAgregarCliente();
            cargarTablaModificarCliente();
            cargarTablaEliminarCliente();
            cargarListaClienteHabitual();
            JOptionPane.showMessageDialog(this, "Carga de Cliente Exitosa");
            txtNombreCliente.setText("");
            txtDocumento.setText("");
            txtTelefono.setText("");
            if (chkHabitual.isSelected()) {
                chkHabitual.setSelected(false);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Revise los campos");
        }

    }//GEN-LAST:event_btnCargarClienteActionPerformed

    private void txtBuscarAgregarClienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarAgregarClienteFocusGained
        if (txtBuscarAgregarCliente.getText().equals("Buscar Cliente...")) {
            txtBuscarAgregarCliente.setText(null);
            txtBuscarAgregarCliente.requestFocus();
            removePlaceHolder(txtBuscarAgregarCliente);
        }
    }//GEN-LAST:event_txtBuscarAgregarClienteFocusGained

    private void txtBuscarAgregarClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarAgregarClienteFocusLost
        if (txtBuscarAgregarCliente.getText().length() == 0) {
            addPlaceHolder(txtBuscarAgregarCliente);
            txtBuscarAgregarCliente.setText("Buscar Cliente...");
        }
    }//GEN-LAST:event_txtBuscarAgregarClienteFocusLost

    private void txtBuscarAgregarClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarAgregarClienteKeyReleased
        String query = txtBuscarAgregarCliente.getText();
        filterAgregarCliente(query);
    }//GEN-LAST:event_txtBuscarAgregarClienteKeyReleased

    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyTyped
        int key = evt.getKeyChar();
        boolean numero = key >= 48 && key <= 57;
        if (!numero) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDniKeyTyped

    private void txtTelefono1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefono1KeyTyped
        int key = evt.getKeyChar();
        boolean numero = key >= 48 && key <= 57;
        if (!numero) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTelefono1KeyTyped

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

        try {
            abrirConexion();
            pst = con.prepareStatement("UPDATE Cliente SET nombreCliente = ?, dniCliente=?, telefonoCliente = ? WHERE idCliente = ?");
            pst.setString(1, txtNombre.getText());
            pst.setString(2, txtDni.getText());
            pst.setString(3, txtTelefono1.getText());
            pst.setString(4, txtId.getText());

            int res = pst.executeUpdate();

            if (res > 0) {
                
                JOptionPane.showMessageDialog(null, "Cliente Modificado");
                cargarTablaModificarCliente();
                cargarTablaEliminarCliente();
                cargarTablaAgregarCliente();
                txtDni.setText("");
                txtNombre.setText("");
                txtId.setText("");
                txtTelefono1.setText("");
                btnModificar.setEnabled(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Revise los campos");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void tblModificarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblModificarClienteMouseClicked
        btnModificar.setEnabled(true);
        int selectedRow = tblModificarCliente.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tblModificarCliente.getModel();
        txtId.setText(model.getValueAt(selectedRow, 0).toString());
        txtNombre.setText(model.getValueAt(selectedRow, 1).toString());
        txtDni.setText(model.getValueAt(selectedRow, 2).toString());
        txtTelefono1.setText(model.getValueAt(selectedRow, 3).toString());
        //        String habitual = model.getValueAt(selectedRow, 4).toString();
        //        switch (habitual) {
        //            case "true":
        //                chkHabitual.setSelected(true);
        //                break;
        //            case "false":
        //                chkHabitual.setSelected(false);
        //                break;
        //        }

        txtNombre.setVisible(true);
        txtDni.setVisible(true);
        txtTelefono.setVisible(true);

    }//GEN-LAST:event_tblModificarClienteMouseClicked

    private void txtBuscarModificarClienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarModificarClienteFocusGained
        if (txtBuscarModificarCliente.getText().equals("Buscar Cliente...")) {
            txtBuscarModificarCliente.setText(null);
            txtBuscarModificarCliente.requestFocus();
            removePlaceHolder(txtBuscarModificarCliente);
        }

    }//GEN-LAST:event_txtBuscarModificarClienteFocusGained

    private void txtBuscarModificarClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarModificarClienteFocusLost
        if (txtBuscarModificarCliente.getText().length() == 0) {
            addPlaceHolder(txtBuscarModificarCliente);
            txtBuscarModificarCliente.setText("Buscar Cliente...");
        }
    }//GEN-LAST:event_txtBuscarModificarClienteFocusLost

    private void txtBuscarModificarClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarModificarClienteKeyReleased
        String query = txtBuscarModificarCliente.getText();
        filterModificarCliente(query);
    }//GEN-LAST:event_txtBuscarModificarClienteKeyReleased

    private void txtDni1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDni1KeyTyped
        int key = evt.getKeyChar();
        boolean numero = key >= 48 && key <= 57;
        if (!numero) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDni1KeyTyped

    private void tblEliminarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEliminarClienteMouseClicked
        btnEliminar.setEnabled(true);
        int selectedRow = tblEliminarCliente.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tblEliminarCliente.getModel();
        txtId1.setText(model.getValueAt(selectedRow, 0).toString());
        txtNombre1.setText(model.getValueAt(selectedRow, 1).toString());
        txtDni1.setText(model.getValueAt(selectedRow, 2).toString());

    }//GEN-LAST:event_tblEliminarClienteMouseClicked

    private void txtBuscarEliminarClienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarEliminarClienteFocusGained
        if (txtBuscarEliminarCliente.getText().equals("Buscar Cliente...")) {
            txtBuscarEliminarCliente.setText(null);
            txtBuscarEliminarCliente.requestFocus();
            removePlaceHolder(txtBuscarEliminarCliente);
        }
    }//GEN-LAST:event_txtBuscarEliminarClienteFocusGained

    private void txtBuscarEliminarClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarEliminarClienteFocusLost
        if (txtBuscarEliminarCliente.getText().length() == 0) {
            addPlaceHolder(txtBuscarEliminarCliente);
            txtBuscarEliminarCliente.setText("Buscar Cliente...");
        }
    }//GEN-LAST:event_txtBuscarEliminarClienteFocusLost

    private void txtBuscarEliminarClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarEliminarClienteKeyReleased
        String query = txtBuscarEliminarCliente.getText();
        filterEliminarCliente(query);
    }//GEN-LAST:event_txtBuscarEliminarClienteKeyReleased

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        int response = JOptionPane.showConfirmDialog(this, "Esta seguro de eliminar el Cliente?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            try {
                abrirConexion();
                pst = con.prepareStatement("DELETE FROM Cliente WHERE idCliente=?");
                pst.setInt(1, Integer.parseInt(txtId1.getText()));

                int res = pst.executeUpdate();

                if (res > 0) {
                    cargarTablaEliminarCliente();
                    cargarTablaAgregarCliente();
                    cargarTablaModificarCliente();
                    txtId1.setText("");
                    txtNombre1.setText("");
                    txtDni1.setText("");
                    btnEliminar.setEnabled(false);
                }
                con.close();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Seleccionar un Cliente");
            }

        } else if (response == JOptionPane.NO_OPTION) {

        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void lblAgregarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAgregarClienteMouseClicked
        panelAgregarCliente.setVisible(true);
        panelModificarCliente.setVisible(false);
        panelEliminarCliente.setVisible(false);
    }//GEN-LAST:event_lblAgregarClienteMouseClicked

    private void lblModificarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblModificarClienteMouseClicked
        panelAgregarCliente.setVisible(false);
        panelModificarCliente.setVisible(true);
        panelEliminarCliente.setVisible(false);
    }//GEN-LAST:event_lblModificarClienteMouseClicked

    private void lblEliminarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEliminarClienteMouseClicked
        panelAgregarCliente.setVisible(false);
        panelModificarCliente.setVisible(false);
        panelEliminarCliente.setVisible(true);
    }//GEN-LAST:event_lblEliminarClienteMouseClicked

    private void btnReporteClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteClienteActionPerformed
        VentanaReporteCliente vr = new VentanaReporteCliente();
        vr.setVisible(true);
    }//GEN-LAST:event_btnReporteClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private PanelesBG.BackGround backGround1;
    private PanelesBG.BackGround backGround2;
    private PanelesBG.BackGround backGround3;
    private javax.swing.JButton btnCargarCliente;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnReporteCliente;
    private javax.swing.JCheckBox chkHabitual;
    private javax.swing.JCheckBox chkHabitual1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblAgregarCliente;
    private javax.swing.JLabel lblEliminarCliente;
    private javax.swing.JLabel lblModificarCliente;
    private javax.swing.JList lstClienteHabitual;
    private PanelesBG.Bg panelAgregarCliente;
    private javax.swing.JPanel panelBg;
    private PanelesBG.Bg panelEliminarCliente;
    private PanelesBG.Bg panelModificarCliente;
    private javax.swing.JPanel panelOpciones;
    private javax.swing.JTable tblAgregarCliente;
    private javax.swing.JTable tblEliminarCliente;
    private javax.swing.JTable tblModificarCliente;
    private javax.swing.JTextField txtBuscarAgregarCliente;
    private javax.swing.JTextField txtBuscarEliminarCliente;
    private javax.swing.JTextField txtBuscarModificarCliente;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtDni1;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtId1;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombre1;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefono1;
    // End of variables declaration//GEN-END:variables
}
