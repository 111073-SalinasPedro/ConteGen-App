package Model;

public class FormaPago {
    
    private int idFormaPago;
    private String formaPago;

    public FormaPago(int idFormaPago, String formaPago) {
        this.idFormaPago = idFormaPago;
        this.formaPago = formaPago;
    }

    public int getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(int idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    @Override
    public String toString() {
        return formaPago;
    }
    
    
}
