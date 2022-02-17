package Model;

public class Remito {
    private int idRemito;
    private int idFormaPago;
    private String fecha;
    private int idContenedor;
    private int idEstadoRemito;
    private int idPedido;
    private int idCamion;

    public Remito(int idRemito, int idFormaPago, String fecha, int idContenedor) {
        this.idRemito = idRemito;
        this.idFormaPago = idFormaPago;
        this.fecha = fecha;
        this.idContenedor = idContenedor;
    }

    public Remito(int idRemito, int idFormaPago, String fecha, int idContenedor, int idEstadoRemito) {
        this.idRemito = idRemito;
        this.idFormaPago = idFormaPago;
        this.fecha = fecha;
        this.idContenedor = idContenedor;
        this.idEstadoRemito = idEstadoRemito;
    }

    public Remito(int idFormaPago, String fecha, int idContenedor, int idEstadoRemito, int idPedido) {        
        this.idFormaPago = idFormaPago;
        this.fecha = fecha;
        this.idContenedor = idContenedor;
        this.idEstadoRemito = idEstadoRemito;
        this.idPedido = idPedido;
    }

    public Remito(int idFormaPago, String fecha, int idContenedor, int idEstadoRemito, int idPedido, int idCamion) {
        this.idFormaPago = idFormaPago;
        this.fecha = fecha;
        this.idContenedor = idContenedor;
        this.idEstadoRemito = idEstadoRemito;
        this.idPedido = idPedido;
        this.idCamion = idCamion;
    }

    public Remito(int idRemito, int idFormaPago, String fecha, int idContenedor, int idEstadoRemito, int idCamion) {
        this.idRemito = idRemito;
        this.idFormaPago = idFormaPago;
        this.fecha = fecha;
        this.idContenedor = idContenedor;
        this.idEstadoRemito = idEstadoRemito;
        this.idCamion = idCamion;
    }
    
    

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
    
    public Remito(int idFormaPago, String fecha, int idContenedor) {
        this.idFormaPago = idFormaPago;
        this.fecha = fecha;
        this.idContenedor = idContenedor;
    }

    public Remito(int idRemito) {
        this.idRemito = idRemito;
    }

    public int getIdEstadoRemito() {
        return idEstadoRemito;
    }

    public void setIdEstadoRemito(int idEstadoRemito) {
        this.idEstadoRemito = idEstadoRemito;
    }
    
    public int getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(int idRemito) {
        this.idRemito = idRemito;
    }

    public int getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(int idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdContenedor() {
        return idContenedor;
    }

    public void setIdContenedor(int idContenedor) {
        this.idContenedor = idContenedor;
    }

    public int getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(int idCamion) {
        this.idCamion = idCamion;
    }
    
    
    

    
    
    
    
    
}
