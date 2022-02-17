package DTO;

public class DTOImprimir {
    private int idRemito;
    private String fecha;
    private String pago;
    private String direcciom;
    private String codigo;
    private int dias;
    private int cantidad;
    private int idPedido;
    private int camion;
    private double importe;

    public DTOImprimir(int idRemito, String fecha, String pago, String direcciom, String codigo, int dias, int cantidad, int idPedido, int camion, double importe) {
        this.idRemito = idRemito;
        this.fecha = fecha;
        this.pago = pago;
        this.direcciom = direcciom;
        this.codigo = codigo;
        this.dias = dias;
        this.cantidad = cantidad;
        this.idPedido = idPedido;
        this.camion = camion;
        this.importe = importe;
    }

    public int getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(int idRemito) {
        this.idRemito = idRemito;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public String getDirecciom() {
        return direcciom;
    }

    public void setDirecciom(String direcciom) {
        this.direcciom = direcciom;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getCamion() {
        return camion;
    }

    public void setCamion(int camion) {
        this.camion = camion;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
    
    

}
