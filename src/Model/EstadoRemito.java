package Model;

public class EstadoRemito {
    
    private int idEstadoRemito;
    private String estadoRemito;

    public EstadoRemito(int idEstadoRemito, String estadoRemito) {
        this.idEstadoRemito = idEstadoRemito;
        this.estadoRemito = estadoRemito;
    }

    public int getIdEstadoRemito() {
        return idEstadoRemito;
    }

    public void setIdEstadoRemito(int idEstadoRemito) {
        this.idEstadoRemito = idEstadoRemito;
    }

    public String getEstadoRemito() {
        return estadoRemito;
    }

    public void setEstadoRemito(String estadoRemito) {
        this.estadoRemito = estadoRemito;
    }

    @Override
    public String toString() {
        return estadoRemito;
    }
    
    
}
