package DTO;

public class DTOPedidosPorCliente {

    private String cliente;
    private int cantidadPedidos;

    public DTOPedidosPorCliente(String cliente, int cantidadPedidos) {
        this.cliente = cliente;
        this.cantidadPedidos = cantidadPedidos;
    }

    public int getCantidadPedidos() {
        return cantidadPedidos;
    }

    public void setCantidadPedidos(int cantidadPedidos) {
        this.cantidadPedidos = cantidadPedidos;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

}
