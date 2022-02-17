package GestorBD;

import DTO.*;
import Model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class GestorBD {

    private String CONN = "jdbc:sqlserver://localhost:1433;databaseName=Contegen";
    private String USER = "sa";
    private String PASS = "123456";
    private Connection con;

    private void abrirConexion() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            con = DriverManager.getConnection(CONN, USER, PASS);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void cerrarConexion() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //----------------------Ventana Cliente----------------------
    public void agregarCliente(Cliente nuevo) {

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Cliente VALUES (?,?,?,?)");

            pstmt.setString(1, nuevo.getNombreCliente());
            pstmt.setInt(2, nuevo.getDniCliente());
            pstmt.setInt(3, nuevo.getTelefonoCliente());
            pstmt.setBoolean(4, nuevo.isHabitual());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public ArrayList<Cliente> listadoCliente() {
        ArrayList<Cliente> lista = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT idCliente, nombreCliente, dniCliente, telefonoCliente, habitual FROM Cliente");

            while (rs.next()) {

                int id = rs.getInt(1);
                String nombreCliente = rs.getString(2);
                int dniCliente = rs.getInt(3);
                int telefonoCliente = rs.getInt(4);
                boolean habitual = rs.getBoolean(5);

                Cliente c = new Cliente(id, nombreCliente, dniCliente, telefonoCliente, habitual);
                lista.add(c);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public ArrayList<listadoClienteHabitual> listadoClienteHabitual() {
        ArrayList<listadoClienteHabitual> lista = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT nombreCliente, dniCliente FROM Cliente\n"
                    + "WHERE habitual = 1");

            while (rs.next()) {

                String nombre = rs.getString(1);
                int dni = rs.getInt(2);

                listadoClienteHabitual h = new listadoClienteHabitual(nombre, dni);
                lista.add(h);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public int eliminarCliente(String idCliente) {

        String sql = "DELETE FROM Cliente WHERE idCliente = ?" + idCliente;
        int res = 0;
        try {
            PreparedStatement st = con.prepareStatement(sql);
            res = st.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
            }
            System.out.println(res);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return res;
    }

    public boolean modificarCliente(Cliente c) {
        boolean modificar = false;
        try {
            abrirConexion();
            String sql = "UPDATE Cliente SET nombreCliente = ?, dniCliente=?, telefonoCliente = ?, habitual = ? WHERE idCliente = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, c.getNombreCliente());
            st.setInt(2, c.getDniCliente());
            st.setInt(3, c.getTelefonoCliente());
            st.setBoolean(4, c.isHabitual());
//            st.setInt(5,c.getIdCliente());
            st.executeUpdate();
            modificar = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return modificar;
    }

    //--------------------Ventana Consulta-----------------------
    public ArrayList<DTOPedido> listadoPedidos() {
        ArrayList<DTOPedido> lista = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT p.idPedido,nombreCliente,direccion,barrio,zona,fechaEntrega,horario,dias,contenedores,observaciones,estado\n"
                    + "FROM Pedido p \n"
                    + "JOIN Cliente c ON c.idCliente=p.idCliente\n"
                    + "JOIN Zona z ON z.idZona=p.idZona\n"
                    + "JOIN Estado e ON e.idEstado=p.idEstado\n"
                    + "ORDER BY p.idPedido desc");

            while (rs.next()) {

                int id = rs.getInt(1);
                String nombreCliente = rs.getString(2);
                String direccion = rs.getString(3);
                String barrio = rs.getString(4);
                String zona = rs.getString(5);
                String fecha = rs.getString(6);
                String horario = rs.getString(7);
                int dias = rs.getInt(8);
                int cantidad = rs.getInt(9);
                String observaciones = rs.getString(10);
                String estado = rs.getString(11);

                DTOPedido c = new DTOPedido(id, nombreCliente, direccion, barrio, zona, fecha, horario, fecha, dias, cantidad, observaciones, estado);
                lista.add(c);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public DefaultTableModel mostrarListadoPedidos() {
        DefaultTableModel modelo = null;
        try {
            String titulo[] = {"id", "Cliente", "Direccion", "Barrio", "Zona", "Fecha de Entrega", "Horario", "Dias", "Cantidad", "Observaciones", "Estado"};
            String dts[] = new String[11];
            modelo = new DefaultTableModel(null, titulo);
            String sql = "SELECT p.idPedido,nombreCliente,direccion,barrio,zona,fechaEntrega,horario,dias,contenedores,observaciones,estado\n"
                    + "FROM Pedido p\n"
                    + "JOIN Cliente c ON c.idCliente=p.idCliente\n"
                    + "JOIN Zona z ON z.idZona=p.idZona\n"
                    + "JOIN Estado e ON e.idEstado=p.idEstado\n"
                    + "ORDER BY p.idPedido DESC";
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dts[0] = rs.getString(1);
                dts[1] = rs.getString(2);
                dts[2] = rs.getString(3);
                dts[3] = rs.getString(4);
                dts[4] = rs.getString(5);
                dts[5] = rs.getString(6);
                dts[6] = rs.getString(7);
                dts[7] = rs.getString(8);
                dts[8] = rs.getString(9);
                dts[9] = rs.getString(10);
                dts[10] = rs.getString(11);
                modelo.addRow(dts);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public List<DTOPedido> mostrarListadoPedidoDesdeHasta(String fecha1, String fecha2) {
        List<DTOPedido> resultado = new ArrayList<>();
        Connection con = null;
        try {
            con = DriverManager.getConnection(CONN, USER, PASS);
            CallableStatement cmd = con.prepareCall("USE[Contegen]\n"
                    + "EXEC [dbo].[SP_EntreFecha_ListadoPedido]\n"
                    + "	@fechaDesde = ?,\n"
                    + "	@fechaHasta = ?");
            cmd.setString(1, fecha1);
            cmd.setString(2, fecha2);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String cliente = rs.getString(2);
                String direccion = rs.getString(3);
                String barrio = rs.getString(4);
                String zona = rs.getString(5);
                String fecha = rs.getString(6);
                String horario = rs.getString(7);
                int dias = rs.getInt(8);
                int cantidad = rs.getInt(9);
                String observaciones = rs.getString(10);
                String estado = rs.getString(11);

                DTOPedido p = new DTOPedido(id, cliente, direccion, barrio, zona, fecha, horario, dias, cantidad, observaciones, estado);

                resultado.add(p);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultado;
    }

    public List<DTOPedido> buscarPedido(int idPedido) {
        List<DTOPedido> resultado = new ArrayList<>();
        Connection con = null;
        try {
            con = DriverManager.getConnection(CONN, USER, PASS);
            CallableStatement cmd = con.prepareCall("USE[Contegen]\n"
                    + "EXEC [dbo].[BuscarPedido]\n"
                    + "@idPedido = ?");
            cmd.setInt(1, idPedido);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String cliente = rs.getString(2);
                String direccion = rs.getString(3);
                String barrio = rs.getString(4);
                String zona = rs.getString(5);
                String fecha = rs.getString(6);
                String horario = rs.getString(7);
                int dias = rs.getInt(8);
                int cantidad = rs.getInt(9);
                String observaciones = rs.getString(10);
                String estado = rs.getString(11);
                DTOPedido p = new DTOPedido(id, cliente, direccion, barrio, zona, fecha, horario, dias, cantidad, observaciones, estado);
                resultado.add(p);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultado;
    }

    //---------------------Ventana Pedido------------------
    public ArrayList<Zona> obtenerTodosLasZonas() {

        ArrayList<Zona> lista = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Zona");

            while (rs.next()) {

                int idZona = rs.getInt(1);
                String zona = rs.getString(2);

                Zona z = new Zona(idZona, zona);
                lista.add(z);

            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public ArrayList<Estado> obtenerTodosLosEstados() {

        ArrayList<Estado> lista = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Estado");

            while (rs.next()) {

                int idEstado = rs.getInt(1);
                String estado = rs.getString(2);

                Estado e = new Estado(idEstado, estado);
                lista.add(e);

            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public ArrayList<Cliente> obtenerTodosLosClientes() {

        ArrayList<Cliente> lista = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Cliente ORDER BY idCliente DESC");

            while (rs.next()) {

                int idCliente = rs.getInt(1);
                String nombreCliente = rs.getString(2);
                int dniCliente = rs.getInt(3);
                int telefonoCliente = rs.getInt(4);
                boolean habitual = rs.getBoolean(5);

                Cliente c = new Cliente(idCliente, nombreCliente, dniCliente, telefonoCliente, habitual);
                lista.add(c);

            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public void agregarPedido(DetallePedido nuevo) {

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Pedido(fechaEntrega,horario,observaciones,direccion,barrio,dias,contenedores,idZona,idEstado,idCliente)\n"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?)");

            pstmt.setString(1, nuevo.getFechaEntrega());
            pstmt.setString(2, nuevo.getHorarioEnrega());
            pstmt.setString(3, nuevo.getObservaciones());
            pstmt.setString(4, nuevo.getDireccion());
            pstmt.setString(5, nuevo.getBarrio());
            pstmt.setInt(6, nuevo.getDias());
            pstmt.setInt(7, nuevo.getCantidadContenedores());
            pstmt.setInt(8, nuevo.getIdZona());
            pstmt.setInt(9, nuevo.getIdEstado());
            pstmt.setInt(10, nuevo.getIdCliente());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    //---------------------Ventana Remito------------------------
    public ArrayList<FormaPago> obtenerTodosLasFormaPago() {

        ArrayList<FormaPago> lista = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM FormaPago");

            while (rs.next()) {

                int idFormaPago = rs.getInt(1);
                String formaPago = rs.getString(2);

                FormaPago p = new FormaPago(idFormaPago, formaPago);
                lista.add(p);

            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public ArrayList<Contenedor> obtenerTodosLosContenedoresDisponibles() {

        ArrayList<Contenedor> lista = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Contenedor WHERE disponible=1");

            while (rs.next()) {

                int idContenedor = rs.getInt(1);
                String codigoContenedor = rs.getString(2);
                boolean dis = rs.getBoolean(3);

                Contenedor c = new Contenedor(idContenedor, codigoContenedor, dis);
                lista.add(c);

            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public ArrayList<Contenedor> obtenerTodosLosContenedores() {

        ArrayList<Contenedor> lista = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Contenedor");

            while (rs.next()) {

                int idContenedor = rs.getInt(1);
                String codigoContenedor = rs.getString(2);
                boolean dis = rs.getBoolean(3);

                Contenedor c = new Contenedor(idContenedor, codigoContenedor, dis);
                lista.add(c);

            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public ArrayList<EstadoRemito> obtenerTodosLosEstadosRemito() {

        ArrayList<EstadoRemito> lista = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM EstadoRemito");

            while (rs.next()) {

                int idEstadoRemitp = rs.getInt(1);
                String estado = rs.getString(2);

                EstadoRemito e = new EstadoRemito(idEstadoRemitp, estado);
                lista.add(e);

            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public ArrayList<Camion> obtenerTodosLosCamiones() {

        ArrayList<Camion> lista = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Camion");

            while (rs.next()) {

                int idCamion = rs.getInt(1);
                String patente = rs.getString(2);
                int idConductor = rs.getInt(3);

                Camion c = new Camion(idCamion, patente, idConductor);
                lista.add(c);

            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public ArrayList<DTOPedido> listadoPedidoRemito() {
        ArrayList<DTOPedido> lista = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT p.idPedido,fechaEntrega,nombreCliente,direccion,contenedores,dias\n"
                    + "FROM Pedido p JOIN Cliente c ON  p.idCliente=c.idCliente\n"
                    + "WHERE p.idEstado != 4\n"
                    + "ORDER BY 1 desc");

            while (rs.next()) {

                int pedido = rs.getInt(1);
                String fecha = rs.getString(2);
                String nombre = rs.getString(3);
                String direccion = rs.getString(4);
                int cont = rs.getInt(5);
                int dias = rs.getInt(6);

                DTOPedido d = new DTOPedido(pedido, nombre, direccion, fecha, dias, cont);
                lista.add(d);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public ArrayList<DTORemito> listadoRemito() {
        ArrayList<DTORemito> lista = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT r.idRemito,fecha,formaPago,estado,codigoContenedor,dias,contenedores,r.idPedido,idCamion,importe\n"
                    + "FROM Remito r \n"
                    + "JOIN FormaPago f ON r.idFormaPago=f.idFormaPago\n"
                    + "JOIN Contenedor c ON c.idContenedor=r.idContenedor\n"
                    + "JOIN EstadoRemito e On e.idEstadoRemito=r.idEstadoRemito\n"
                    + "JOIN Pedido p ON r.idPedido= p.idPedido\n"
                    + "JOIN Factura fa ON p.idPedido=fa.idPedido\n"
                    + "ORDER BY 1 desc");

            while (rs.next()) {

                int remito = rs.getInt(1);
                String fecha = rs.getString(2);
                String pago = rs.getString(3);
                String estado = rs.getString(4);
                String codigo = rs.getString(5);
                int dias = rs.getInt(6);
                int contenedor = rs.getInt(7);
                int pedido = rs.getInt(8);
                int camion = rs.getInt(9);
                double importe = rs.getDouble(10);

                DTORemito r = new DTORemito(pedido, remito, pago, fecha, codigo, estado, importe, contenedor, camion, dias);
                lista.add(r);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public void agregarImporte(listadoFactura nuevo) {

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Factura VALUES (?,?)");

            pstmt.setDouble(1, nuevo.getImporte());
            pstmt.setInt(2, nuevo.getIdPedido());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void agregarRemito(Remito nuevo) {

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Remito(fecha,idFormaPago,idContenedor,idEstadoRemito,idPedido,idCamion)"
                    + " VALUES (?,?,?,?,?,?)");

            pstmt.setString(1, nuevo.getFecha());
            pstmt.setInt(2, nuevo.getIdFormaPago());
            pstmt.setInt(3, nuevo.getIdContenedor());
            pstmt.setInt(4, nuevo.getIdEstadoRemito());
            pstmt.setInt(5, nuevo.getIdPedido());
            pstmt.setInt(6, nuevo.getIdCamion());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void modificarContenedorDisponilbe(Contenedor c) {

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Contenedor SET disponible=0 WHERE codigoContenedor=?");
            pstmt.setString(1, c.getCodigoContenedor());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public DefaultTableModel mostrarTodosLosPedidos() {
        DefaultTableModel modelo = null;
        try {
            String titulo[] = {"IdPedido", "Fecha", "Contenedores", "Dias"};
            String dts[] = new String[4];
            modelo = new DefaultTableModel(null, titulo);
            String sql = "SELECT idPedido,fechaEntrega,contenedores,dias\n"
                    + "FROM Pedido\n"
                    + "ORDER BY 1 DESC";
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dts[0] = rs.getString(1);
                dts[1] = rs.getString(2);
                dts[2] = rs.getString(3);
                dts[3] = rs.getString(4);
                modelo.addRow(dts);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public List<DTOPedido> mostrarPedidoRemitoDesdeHasta(String fecha1, String fecha2) {
        List<DTOPedido> resultado = new ArrayList<>();
        Connection con = null;
        try {
            con = DriverManager.getConnection(CONN, USER, PASS);
            CallableStatement cmd = con.prepareCall("USE[Contegen]\n"
                    + "EXEC [dbo].[SP_EntreFecha_PedidoRemito]\n"
                    + "@fechaDesde = ?,\n"
                    + "@fechaHasta = ?");
            cmd.setString(1, fecha1);
            cmd.setString(2, fecha2);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                int idPedido = rs.getInt(1);
                String fecha = rs.getString(2);
                String cliente = rs.getString(3);
                String direccion = rs.getString(4);
                int contenedores = rs.getInt(5);
                int dias = rs.getInt(6);

                DTOPedido r = new DTOPedido(idPedido, cliente, direccion, fecha, dias, contenedores);
                resultado.add(r);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultado;
    }

    //--------------------
    public List<DTORemito> mostrarRemitoDesdeHasta(String fecha1, String fecha2) {
        List<DTORemito> resultado = new ArrayList<>();
        Connection con = null;
        try {
            con = DriverManager.getConnection(CONN, USER, PASS);
            CallableStatement cmd = con.prepareCall("USE[Contegen]\n"
                    + "EXEC [dbo].[SP_EntreFecha_Remito]\n"
                    + "@fechaDesde = ?,\n"
                    + "@fechaHasta = ?");
            cmd.setString(1, fecha1);
            cmd.setString(2, fecha2);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                int idRemito = rs.getInt(1);
                String fecha = rs.getString(2);
                String formaPago = rs.getString(3);
                String estado = rs.getString(4);
                String codContenedor = rs.getString(5);
                int dias = rs.getInt(6);
                int contenedores = rs.getInt(7);
                Double importe = rs.getDouble(8);
                int idPedido = rs.getInt(9);
                int camion = rs.getInt(10);

                DTORemito r = new DTORemito(idPedido, idRemito, formaPago, fecha, codContenedor, estado, importe, contenedores, camion, dias);
                resultado.add(r);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultado;
    }

    //----------------------Editar Pedido-----------------------------------
    public ArrayList<DTOPedido> listadoPedido() {
        ArrayList<DTOPedido> lista = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT p.idPedido,direccion,barrio,z.zona,fechaEntrega,horario,dias,contenedores,observaciones,e.estado\n"
                    + "FROM Pedido p \n"
                    + "JOIN Zona z ON z.idZona=p.idZona\n"
                    + "JOIN Estado e ON e.idEstado=p.idEstado\n"
                    + "ORDER BY p.idPedido DESC");

            while (rs.next()) {

                int id = rs.getInt(1);
                String direccion = rs.getString(2);
                String barrio = rs.getString(3);
                String zona = rs.getString(4);
                String fEntrega = rs.getString(5);
                String horario = rs.getString(6);
                int dias = rs.getInt(7);
                int cant = rs.getInt(8);
                String observaciones = rs.getString(9);
                String estado = rs.getString(10);

                DTOPedido l = new DTOPedido(id, direccion, barrio, zona, fEntrega, horario, dias, cant, observaciones, estado);
                lista.add(l);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public boolean deletePedido(DetallePedido pedido) {
        boolean eliminar = false;
        try {
            abrirConexion();
            String sql = "DELETE Pedido \n"
                    + "	FROM Pedido INNER JOIN Factura \n"
                    + "	ON Pedido.idPedido = Factura.idPedido \n"
                    + "	WHERE Pedido.idPedido = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, pedido.getIdDetallePedido());
            pstmt.executeUpdate();
            eliminar = true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return eliminar;
    }

    public boolean modificarPedido(DetallePedido pedido) {
        boolean modificar = false;
        try {
            abrirConexion();
            String sql = "UPDATE Pedido SET fechaEntrega=?,horario=?,observaciones=?,direccion=?,barrio=?,dias=?,contenedores=?,idZona=?,idEstado=?\n"
                    + "WHERE idPedido=?";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, pedido.getFechaEntrega());
            pstmt.setString(2, pedido.getHorarioEnrega());
            pstmt.setString(3, pedido.getObservaciones());
            pstmt.setString(4, pedido.getDireccion());
            pstmt.setString(5, pedido.getBarrio());
            pstmt.setInt(6, pedido.getDias());
            pstmt.setInt(7, pedido.getCantidadContenedores());
            pstmt.setInt(8, pedido.getIdZona());
            pstmt.setInt(9, pedido.getIdEstado());
            pstmt.setInt(10, pedido.getIdDetallePedido());

            pstmt.executeUpdate();
            modificar = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return modificar;
    }

    public List<DTOPedido> mostrarPedidoModificarDesdeHasta(String fecha1, String fecha2) {
        List<DTOPedido> resultado = new ArrayList<>();
        Connection con = null;
        try {
            con = DriverManager.getConnection(CONN, USER, PASS);
            CallableStatement cmd = con.prepareCall("USE[Contegen]\n"
                    + "EXEC [dbo].[SP_EntreFecha_ModificarPedido]\n"
                    + "	@FechaDesde = ?,\n"
                    + "	@FechaHasta = ?");
            cmd.setString(1, fecha1);
            cmd.setString(2, fecha2);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String direccion = rs.getString(2);
                String barrio = rs.getString(3);
                String zona = rs.getString(4);
                String fecha = rs.getString(5);
                String horario = rs.getString(6);
                int dias = rs.getInt(7);
                int cantidad = rs.getInt(8);
                String observaciones = rs.getString(9);
                String estado = rs.getString(10);

                DTOPedido p = new DTOPedido(id, direccion, barrio, zona, fecha, horario, dias, cantidad, observaciones, estado);
                resultado.add(p);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultado;
    }

    //------------------------------Ventana Estado---
    public ArrayList<DTOEstado> listadoPedidoEstado() {
        ArrayList<DTOEstado> lista = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT idPedido, estado\n"
                    + "FROM Pedido p JOIN Estado e ON p.idEstado=e.idEstado ");

            while (rs.next()) {

                int pedido = rs.getInt(1);
                String estado = rs.getString(2);

                DTOEstado d = new DTOEstado(pedido, estado);
                lista.add(d);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public void modificarEstadoPedido(DetallePedido pedido) {
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Pedido SET idEstado=?\n"
                    + "WHERE idPedido=?");
            pstmt.setInt(1, pedido.getIdEstado());
            pstmt.setInt(2, pedido.getIdDetallePedido());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    //-----------------------------Ventana Modificar Remito---
    public boolean modificarRemito(Remito r) {
        boolean modificar = false;
        try {
            abrirConexion();
            String sql = "UPDATE Remito SET fecha = ?, idFormaPago=?, idContenedor = ?, idEstadoRemito= ?,idCamion= ? WHERE idRemito = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, r.getFecha());
            st.setInt(2, r.getIdFormaPago());
            st.setInt(3, r.getIdContenedor());
            st.setInt(4, r.getIdEstadoRemito());
            st.setInt(5, r.getIdCamion());
            st.setInt(6, r.getIdRemito());
            st.executeUpdate();
            modificar = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return modificar;
    }

    public List<DTORemito> mostrarRemitoModificarDesdeHasta(String fecha1, String fecha2) {
        List<DTORemito> resultado = new ArrayList<>();
        Connection con = null;
        try {
            con = DriverManager.getConnection(CONN, USER, PASS);
            CallableStatement cmd = con.prepareCall("USE[Contegen]\n"
                    + "EXEC [dbo].[SP_EntreFecha_ModificarRemito]\n"
                    + "@FechaDesde = ?,\n"
                    + "@FechaHasta = ?");
            cmd.setString(1, fecha1);
            cmd.setString(2, fecha2);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                int idRemito = rs.getInt(1);
                String fecha = rs.getString(2);
                String formaPago = rs.getString(3);
                String estadoR = rs.getString(4);
                String cod = rs.getString(5);
                int dias = rs.getInt(6);
                int cant = rs.getInt(7);
                String estadoP = rs.getString(8);
                double importe = rs.getDouble(9);
                int camion = rs.getInt(10);
                int idPedido = rs.getInt(11);

                DTORemito r = new DTORemito(idRemito, idPedido, formaPago, fecha, cod, estadoR, importe, cant, camion, dias, estadoP);
                resultado.add(r);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultado;
    }

    public ArrayList<DTORemito> listadoModificarRemito() {
        ArrayList<DTORemito> lista = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT r.idRemito,fecha,formaPago,e.estado,codigoContenedor,dias,contenedores,t.estado,idCamion,r.idPedido,importe\n"
                    + "FROM Remito r \n"
                    + "JOIN FormaPago f ON r.idFormaPago=f.idFormaPago\n"
                    + "JOIN Contenedor c ON c.idContenedor=r.idContenedor\n"
                    + "JOIN EstadoRemito e On e.idEstadoRemito=r.idEstadoRemito\n"
                    + "JOIN Pedido p ON r.idPedido=p.idPedido\n"
                    + "JOIN Estado t ON p.idEstado=t.idEstado\n"
                    + "JOIN Factura fa ON p.idPedido=fa.idPedido\n"
                    + "ORDER BY 1 desc");

            while (rs.next()) {

                int remito = rs.getInt(1);
                String fecha = rs.getString(2);
                String pago = rs.getString(3);
                String estado = rs.getString(4);
                String codigo = rs.getString(5);
                int dias = rs.getInt(6);
                int contenedor = rs.getInt(7);
                String estadoP = rs.getString(8);
                int camion = rs.getInt(9);
                int pedido = rs.getInt(10);
                double importe = rs.getDouble(11);

                DTORemito r = new DTORemito(pedido, remito, pago, fecha, codigo, estado, importe, contenedor, camion, dias, estadoP);
                lista.add(r);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public boolean modificarImporte(listadoFactura f) {
        boolean modificar = false;
        try {
            abrirConexion();
            String sql = "UPDATE Factura SET importe = ? WHERE idPedido = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setDouble(1, f.getImporte());
            st.setInt(2, f.getIdPedido());

            st.executeUpdate();
            modificar = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return modificar;
    }

    //----------------------------- Buscar Remito------------
    public ArrayList<DTORemito> listadoBuscarRemito() {
        ArrayList<DTORemito> lista = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(" SELECT r.idRemito, estado FROM Remito r \n"
                    + "JOIN EstadoRemito e ON r.idEstadoRemito=e.idEstadoRemito");
            while (rs.next()) {

                int idRemito = rs.getInt(1);
                String estado = rs.getString(2);

                DTORemito r = new DTORemito(idRemito, estado);
                lista.add(r);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    //----------------------BTN Finalizar Pedido----------
    public void modificarContenedorNoDisponible(Contenedor c) {
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Contenedor SET disponible=1 WHERE codigoContenedor=?");

            pstmt.setString(1, c.getCodigoContenedor());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void finalizarPedido(DetallePedido p) {

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Pedido SET idEstado=4  WHERE idPedido=?");
            pstmt.setInt(1, p.getIdDetallePedido());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //-----------------------------Reporte
    public ArrayList<DTOPedidoPorZona> pedidosPorZona() {
        ArrayList<DTOPedidoPorZona> resultado = new ArrayList<>();

        try {

            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select COUNT(*), zona \n"
                    + "FROM Zona z JOIN Pedido p ON p.idZona=z.idZona\n"
                    + "GROUP BY zona");

            while (rs.next()) {
                int cant = rs.getInt(1);
                String zona = rs.getString(2);

                DTOPedidoPorZona dto = new DTOPedidoPorZona(cant, zona);
                resultado.add(dto);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return resultado;
    }

    public int cantidadContenedoresDisponibles() {

        int resultado = 0;

        try {

            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(disponible) FROM Contenedor \n"
                    + "WHERE disponible=1");

            if (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return resultado;
    }

    public ArrayList<Conductor> listaConductor() {
        ArrayList<Conductor> resultado = new ArrayList<>();

        try {

            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT nombreConductor,fechaNacimiento,dniConductor,telefonoConductor,idCamion\n"
                    + " FROM Conductor c JOIN Camion a ON c.idConductor=a.idConductor");

            while (rs.next()) {
                String nombre = rs.getString(1);
                String nac = rs.getString(2);
                int dni = rs.getInt(3);
                int tel = rs.getInt(4);
                int camion = rs.getInt(5);

                Conductor dto = new Conductor(nombre, nac, dni, tel, camion);

                resultado.add(dto);

            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return resultado;
    }

    public ArrayList<DTOPedidoPorMes> pedidosPorMes() {
        ArrayList<DTOPedidoPorMes> resultado = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select DISTINCT datename([MONTH],fechaEntrega) as m,count(*),MONTH(fechaEntrega) as [m_#]\n"
                    + "from Pedido\n"
                    + "Group by datename([MONTH],fechaEntrega),MONTH(fechaEntrega)\n"
                    + "order by [m_#]");
            while (rs.next()) {
                String nombre = rs.getString(1);
                int cantidad = rs.getInt(2);
                int mes = rs.getInt(3);

                DTOPedidoPorMes d = new DTOPedidoPorMes(mes, nombre, cantidad);

                resultado.add(d);

            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultado;
    }

    public List<DTOPedidoPorZona> mostrarPedidosPorZonaDesdeHasta(String fecha1, String fecha2) {
        List<DTOPedidoPorZona> resultado = new ArrayList<>();
        Connection con = null;
        try {
            con = DriverManager.getConnection(CONN, USER, PASS);
            CallableStatement cmd = con.prepareCall("USE[Contegen]\n"
                    + "EXEC [dbo].[SP_EntreFecha_PedidoPorZona]\n"
                    + "	@fechaDesde = ?,\n"
                    + "	@fechaHasta = ?");
            cmd.setString(1, fecha1);
            cmd.setString(2, fecha2);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                String zona = rs.getString(1);
                int cantidad = rs.getInt(2);
                DTOPedidoPorZona d = new DTOPedidoPorZona(cantidad, zona);
                resultado.add(d);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultado;
    }

    public List<DTOPedidoPorMes> mostrarPedidosPorMesDesdeHasta(String fecha1, String fecha2) {
        List<DTOPedidoPorMes> resultado = new ArrayList<>();
        Connection con = null;
        try {
            con = DriverManager.getConnection(CONN, USER, PASS);
            CallableStatement cmd = con.prepareCall("USE[Contegen]\n"
                    + "EXEC [dbo].[SP_EntreFecha_PedidoPorMes]\n"
                    + "	@fechaDesde = ?,\n"
                    + "	@fechaHasta = ?");
            cmd.setString(1, fecha1);
            cmd.setString(2, fecha2);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                String mes = rs.getString(1);
                int nombre = rs.getInt(2);
                int cantidad = rs.getInt(3);
                DTOPedidoPorMes d = new DTOPedidoPorMes(nombre, mes, cantidad);
                resultado.add(d);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultado;
    }

    //---------------------------Reporte Cliente
    public ArrayList<DTOPedidosPorCliente> listadoPedidoPorCliente() {
        ArrayList<DTOPedidosPorCliente> lista = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT nombreCliente, COUNT(p.idCliente)\n"
                    + "FROM Cliente c JOIN Pedido p ON c.idCliente=p.idCliente\n"
                    + "GROUP BY  nombreCliente\n"
                    + "ORDER BY 2 DESC");

            while (rs.next()) {

                String cliente = rs.getString(1);
                int cant = rs.getInt(2);

                DTOPedidosPorCliente c = new DTOPedidosPorCliente(cliente, cant);
                lista.add(c);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public List<DTOPedidosPorCliente> mostrarPedidosPorClienteDesdeHasta(String fecha1, String fecha2) {
        List<DTOPedidosPorCliente> resultado = new ArrayList<>();
        Connection con = null;
        try {
            con = DriverManager.getConnection(CONN, USER, PASS);
            CallableStatement cmd = con.prepareCall("USE[Contegen]\n"
                    + "EXEC [dbo].[SP_EntreFecha_PedidoPorCliente]\n"
                    + "@fechaDesde = ?,\n"
                    + "@fechaHasta = ?");
            cmd.setString(1, fecha1);
            cmd.setString(2, fecha2);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                String cliente = rs.getString(1);
                int cantidad = rs.getInt(2);

                DTOPedidosPorCliente d = new DTOPedidosPorCliente(cliente, cantidad);
                resultado.add(d);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultado;
    }

    //---------------------------Tabla Imprimir
    public ArrayList<DTOImprimir> tablaRemitoimprimir() {
        ArrayList<DTOImprimir> lista = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT r.idRemito,fecha,formaPago,direccion,codigoContenedor,dias,contenedores,r.idPedido,idCamion,importe\n"
                    + "FROM Remito r \n"
                    + "JOIN FormaPago f ON r.idFormaPago=f.idFormaPago\n"
                    + "JOIN Contenedor c ON c.idContenedor=r.idContenedor\n"
                    + "JOIN EstadoRemito e On e.idEstadoRemito=r.idEstadoRemito\n"
                    + "JOIN Pedido p ON r.idPedido= p.idPedido\n"
                    + "JOIN Factura fa ON p.idPedido=fa.idPedido\n"
                    + "ORDER BY 1 desc");

            while (rs.next()) {

                int remito = rs.getInt(1);
                String fecha = rs.getString(2);
                String pago = rs.getString(3);
                String direccion = rs.getString(4);
                String codigo = rs.getString(5);
                int dias = rs.getInt(6);
                int contenedor = rs.getInt(7);
                int pedido = rs.getInt(8);
                int camion = rs.getInt(9);
                double importe = rs.getDouble(10);

                DTOImprimir r = new DTOImprimir(remito, fecha, pago, direccion, codigo, dias, contenedor, pedido, camion, importe);
                lista.add(r);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }
}
