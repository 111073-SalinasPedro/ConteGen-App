package DTO;

public class DTOPedido {

    private int idPedido;
    private String nombreCliente;
    private String direccion;
    private String barrio;
    private String zona;
    private String fechaEntrega;
    private String horario;
    private String fechaRetiro;
    private int dias;
    private int cant;
    private String observaciones;
    private String estado;
    private int remito;
    private int cantidadPedidos;

    public DTOPedido(String fechaEntrega, int cantidadPedidos) {
        this.fechaEntrega = fechaEntrega;
        this.cantidadPedidos = cantidadPedidos;
    }

    public DTOPedido(String nombreCliente, String direccion, String barrio, String zona, String fechaEntrega, String horario, String fechaRetiro, int dias, int cant, String observaciones, String estado, int remito) {
        this.nombreCliente = nombreCliente;
        this.direccion = direccion;
        this.barrio = barrio;
        this.zona = zona;
        this.fechaEntrega = fechaEntrega;
        this.horario = horario;
        this.fechaRetiro = fechaRetiro;
        this.dias = dias;
        this.cant = cant;
        this.observaciones = observaciones;
        this.estado = estado;
        this.remito = remito;
    }

    public DTOPedido(int idPedido, String direccion, String barrio, String zona, String fechaEntrega, String horario, int dias, int cant, String observaciones, String estado) {
        this.idPedido = idPedido;
        this.direccion = direccion;
        this.barrio = barrio;
        this.zona = zona;
        this.fechaEntrega = fechaEntrega;
        this.horario = horario;
        this.dias = dias;
        this.cant = cant;
        this.observaciones = observaciones;
        this.estado = estado;
    }

    public DTOPedido(int idPedido, String nombreCliente, String direccion, String fechaEntrega, int dias, int cant) {
        this.idPedido = idPedido;
        this.nombreCliente = nombreCliente;
        this.direccion = direccion;
        this.fechaEntrega = fechaEntrega;
        this.dias = dias;
        this.cant = cant;
    }
    
   
    
public DTOPedido(int idPedido, String nombreCliente, String direccion, String barrio, String zona, String fechaEntrega, String horario, String fechaRetiro, int dias, int cant, String observaciones, String estado, int remito) {
        this.idPedido = idPedido;
        this.nombreCliente = nombreCliente;
        this.direccion = direccion;
        this.barrio = barrio;
        this.zona = zona;
        this.fechaEntrega = fechaEntrega;
        this.horario = horario;
        this.fechaRetiro = fechaRetiro;
        this.dias = dias;
        this.cant = cant;
        this.observaciones = observaciones;
        this.estado = estado;
        this.remito = remito;
    }

    public DTOPedido(int idPedido, String nombreCliente, String direccion, String barrio, String zona, String fechaEntrega, String horario, int dias, int cant, String observaciones, String estado) {
        this.idPedido = idPedido;
        this.nombreCliente = nombreCliente;
        this.direccion = direccion;
        this.barrio = barrio;
        this.zona = zona;
        this.fechaEntrega = fechaEntrega;
        this.horario = horario;
        this.dias = dias;
        this.cant = cant;
        this.observaciones = observaciones;
        this.estado = estado;

    }

    public DTOPedido(String direccion, String barrio, String zona, String fechaEntrega, String horario, String fechaRetiro, int dias, int cant, String observaciones, String estado) {
        this.direccion = direccion;
        this.barrio = barrio;
        this.zona = zona;
        this.fechaEntrega = fechaEntrega;
        this.horario = horario;
        this.fechaRetiro = fechaRetiro;
        this.dias = dias;
        this.cant = cant;
        this.observaciones = observaciones;
        this.estado = estado;
    }

    public DTOPedido(int idPedido, String nombreCliente, String direccion, String barrio, String zona, String fechaEntrega, String horario, String fechaRetiro, int dias, int cant, String observaciones, String estado) {
        this.idPedido = idPedido;
        this.nombreCliente = nombreCliente;
        this.direccion = direccion;
        this.barrio = barrio;
        this.zona = zona;
        this.fechaEntrega = fechaEntrega;
        this.horario = horario;
        this.fechaRetiro = fechaRetiro;
        this.dias = dias;
        this.cant = cant;
        this.observaciones = observaciones;
        this.estado = estado;
    }

    public DTOPedido(int idPedido, String zona) {
        this.idPedido = idPedido;
        this.zona = zona;
    }

    public DTOPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(String fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getRemito() {
        return remito;
    }

    public void setRemito(int remito) {
        this.remito = remito;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public int getCantidadPedidos() {
        return cantidadPedidos;
    }

    public void setCantidadPedidos(int cantidadPedidos) {
        this.cantidadPedidos = cantidadPedidos;
    }

}
