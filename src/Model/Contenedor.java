package Model;

public class Contenedor {

    private int idContenedor;
    private String codigoContenedor;
    private boolean disponible;

    public Contenedor(int idContenedor, String codigoContenedor, boolean disponible) {
        this.idContenedor = idContenedor;
        this.codigoContenedor = codigoContenedor;
        this.disponible = disponible;
    }

    public Contenedor(int idContenedor, String codigoContenedor) {
        this.idContenedor = idContenedor;
        this.codigoContenedor = codigoContenedor;
    }

    public Contenedor(String codigoContenedor) {
        this.codigoContenedor = codigoContenedor;
    }

    public int getIdContenedor() {
        return idContenedor;
    }

    public void setIdContenedor(int idContenedor) {
        this.idContenedor = idContenedor;
    }

    public String getCodigoContenedor() {
        return codigoContenedor;
    }

    public void setCodigoContenedor(String codigoContenedor) {
        this.codigoContenedor = codigoContenedor;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return codigoContenedor;
    }

}
