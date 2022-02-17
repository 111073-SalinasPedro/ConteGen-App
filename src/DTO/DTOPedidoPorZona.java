package DTO;


public class DTOPedidoPorZona {
    
    private int cant;
    private String zona;

    public DTOPedidoPorZona(int cant, String zona) {
        this.cant = cant;
        this.zona = zona;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }
    
    
            
}
