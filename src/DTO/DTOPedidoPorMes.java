package DTO;

public class DTOPedidoPorMes {
    
   private int mes;
   private String nombre;
   private int cantidad;

    public DTOPedidoPorMes(int mes, String nombre, int cantidad) {
        this.mes = mes;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
   
   
}
