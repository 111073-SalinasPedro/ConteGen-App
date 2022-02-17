package DTO;

public class DTORemito {

    private int idPedido;
    private int idRemito;
    private String cliente;
    private String formaPago;
    private String fecha;
    private String contenedor;
    private String estado;
    private double importe;
    private int cantContenedores;
    private int camion;
    private int dias;
    private String estadoPedido;
    

    public DTORemito(int idRemito, String estado) {
        this.idRemito = idRemito;
        this.estado = estado;
    }

    public DTORemito(int idRemito, String formaPago, String fecha, String contenedor, String estado) {
        this.idRemito = idRemito;
        this.formaPago = formaPago;
        this.fecha = fecha;
        this.contenedor = contenedor;
        this.estado = estado;
    }
    
    public DTORemito(int idPedido, String formaPago, String fecha, String contenedor) {
        this.idPedido = idPedido;
        this.formaPago = formaPago;
        this.fecha = fecha;
        this.contenedor = contenedor;
    }

    
    public DTORemito(int idPedido, int idRemito, String cliente, String formaPago, String fecha, String contenedor) {
        this.idPedido = idPedido;
        this.idRemito = idRemito;
        this.cliente = cliente;
        this.formaPago = formaPago;
        this.fecha = fecha;
        this.contenedor = contenedor;
    }

    public DTORemito(int idPedido, int idRemito, String formaPago, String fecha, String contenedor, String estado, double importe, int cantContenedores, int camion, int dias) {
        this.idPedido = idPedido;
        this.idRemito = idRemito;
        this.formaPago = formaPago;
        this.fecha = fecha;
        this.contenedor = contenedor;
        this.estado = estado;
        this.importe = importe;
        this.cantContenedores = cantContenedores;
        this.camion = camion;
        this.dias = dias;
    }

    public DTORemito(int idPedido, int idRemito, String formaPago, String fecha, String contenedor, String estado, double importe, int cantContenedores, int camion, int dias, String estadoPedido) {
        this.idPedido = idPedido;
        this.idRemito = idRemito;
        this.formaPago = formaPago;
        this.fecha = fecha;
        this.contenedor = contenedor;
        this.estado = estado;
        this.importe = importe;
        this.cantContenedores = cantContenedores;
        this.camion = camion;
        this.dias = dias;
        this.estadoPedido = estadoPedido;
    }
    
    
            
    
    

    public int getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(int idRemito) {
        this.idRemito = idRemito;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    
    
    
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
    
   
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getContenedor() {
        return contenedor;
    }

    public void setContenedor(String contenedor) {
        this.contenedor = contenedor;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public int getCantContenedores() {
        return cantContenedores;
    }

    public void setCantContenedores(int cantContenedores) {
        this.cantContenedores = cantContenedores;
    }

    public int getCamion() {
        return camion;
    }

    public void setCamion(int camion) {
        this.camion = camion;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }
    
    

    

}
