package Model;

public class listadoFactura {

    private int idFactura;
    private double importe;
    private String cliente;
    private int cant;
    private int dias;
    private int idPedido;

    public listadoFactura(int cant, int dias) {
        this.cant = cant;
        this.dias = dias;
    }

    public listadoFactura(double importe, int idPedido) {
        this.importe = importe;
        this.idPedido = idPedido;
    }

    public listadoFactura(double importe) {
        this.importe = importe;
    }

    public listadoFactura() {
    }

    public listadoFactura(int idFactura, double importe, int idPedido) {
        this.idFactura = idFactura;
        this.importe = importe;
        this.idPedido = idPedido;
    }
    
    

    public listadoFactura(double importe, String cliente, int cant, int dias, int idPedido) {
        this.importe = importe;
        this.cliente = cliente;
        this.cant = cant;
        this.dias = dias;
        this.idPedido = idPedido;
    }

    public listadoFactura(int cant, int dias, int idPedido) {
        this.cant = cant;
        this.dias = dias;
        this.idPedido = idPedido;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
    
    
//
//    public double calcularImporte() {
//
//        double resultado = 0;
//
//        resultado = (3000 * cant) * dias;
//
//        return resultado;
//    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

}
