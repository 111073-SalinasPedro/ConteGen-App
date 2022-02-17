package Model;

import java.util.Date;

public class DetallePedido {

    private int idDetallePedido;
    private String fechaEntrega;
    private String fechaRetiro;
    private String horarioEnrega;
    private String observaciones;
    private String direccion;
    private String barrio;
    private int dias;
    private int cantidadContenedores;
    private int idZona;
    private int idRemito;
    private int idEstado;
    private int idCliente;

    public DetallePedido(int idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    public DetallePedido(int idDetallePedido, String fechaEntrega, int cantidadContenedores) {
        this.idDetallePedido = idDetallePedido;
        this.fechaEntrega = fechaEntrega;
        this.cantidadContenedores = cantidadContenedores;
    }

    public DetallePedido(int idDetallePedido, int dias, int cantidadContenedores) {
        this.idDetallePedido = idDetallePedido;
        this.dias = dias;
        this.cantidadContenedores = cantidadContenedores;
    }
    
    public DetallePedido() {
    }

    public DetallePedido(int idDetallePedido, int idEstado) {
        this.idDetallePedido = idDetallePedido;
        this.idEstado = idEstado;
    }

    public DetallePedido(String fechaEntrega, String horarioEnrega, String observaciones, String direccion, String barrio, int dias, int cantidadContenedores, int idZona, int idEstado, int idCliente) {
        this.fechaEntrega = fechaEntrega;
        this.horarioEnrega = horarioEnrega;
        this.observaciones = observaciones;
        this.direccion = direccion;
        this.barrio = barrio;
        this.dias = dias;
        this.cantidadContenedores = cantidadContenedores;
        this.idZona = idZona;
        this.idEstado = idEstado;
        this.idCliente = idCliente;
    }

    public DetallePedido(int idDetallePedido, String fechaEntrega, String horarioEnrega, String observaciones, String direccion, String barrio, int dias, int cantidadContenedores, int idZona, int idEstado) {
        this.idDetallePedido = idDetallePedido;
        this.fechaEntrega = fechaEntrega;
        this.horarioEnrega = horarioEnrega;
        this.observaciones = observaciones;
        this.direccion = direccion;
        this.barrio = barrio;
        this.dias = dias;
        this.cantidadContenedores = cantidadContenedores;
        this.idZona = idZona;
        this.idEstado = idEstado;
    }

    public DetallePedido(int idDetallePedido, String fechaEntrega, String horarioEnrega, String observaciones, String direccion, String barrio, int dias, int cantidadContenedores, int idZona, int idEstado, int idCliente) {
        this.idDetallePedido = idDetallePedido;
        this.fechaEntrega = fechaEntrega;
        this.horarioEnrega = horarioEnrega;
        this.observaciones = observaciones;
        this.direccion = direccion;
        this.barrio = barrio;
        this.dias = dias;
        this.cantidadContenedores = cantidadContenedores;
        this.idZona = idZona;
        this.idEstado = idEstado;
        this.idCliente = idCliente;
    }
    
    
          
   
    
    public DetallePedido(String fechaEntrega, String fechaRetiro, String horarioEnrega, String observaciones, String direccion, String barrio, int dias, int cantidadContenedores, int idZona, int idRemito, int idEstado) {
        this.idDetallePedido = -1;
        this.fechaEntrega = fechaEntrega;
        this.fechaRetiro = fechaRetiro;
        this.horarioEnrega = horarioEnrega;
        this.observaciones = observaciones;
        this.direccion=direccion;
        this.barrio = barrio;
        this.dias = dias;
        this.cantidadContenedores = cantidadContenedores;
        this.idZona = idZona;
        this.idRemito = idRemito;
        this.idEstado = idEstado;
    }

    public DetallePedido(String fechaEntrega, String fechaRetiro, String horarioEnrega, String observaciones, String direccion, String barrio, int dias, int cantidadContenedores, int idZona,int idEstado) {
        this.idDetallePedido = -1;
        this.fechaEntrega = fechaEntrega;
        this.fechaRetiro = fechaRetiro;
        this.horarioEnrega = horarioEnrega;
        this.observaciones = observaciones;
        this.direccion=direccion;
        this.barrio = barrio;
        this.dias = dias;
        this.cantidadContenedores = cantidadContenedores;
        this.idZona = idZona;
        this.idEstado = idEstado;
    }

    public int getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(int idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }


    public String getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(String fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }

    public String getHorarioEnrega() {
        return horarioEnrega;
    }

    public void setHorarioEnrega(String horarioEnrega) {
        this.horarioEnrega = horarioEnrega;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public int getCantidadContenedores() {
        return cantidadContenedores;
    }

    public void setCantidadContenedores(int cantidadContenedores) {
        this.cantidadContenedores = cantidadContenedores;
    }

    public int getIdZona() {
        return idZona;
    }

    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }

    public int getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(int idRemito) {
        this.idRemito = idRemito;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    

    @Override
    public String toString() {
        return "Codigo: " + idDetallePedido ;
    }

    
}
