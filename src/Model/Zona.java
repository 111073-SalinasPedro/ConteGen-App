package Model;

public class Zona {
    
    private int idZona;
    private String zona;

    public Zona(int idZona, String zona) {
        this.idZona = idZona;
        this.zona = zona;
    }

    public int getIdZona() {
        return idZona;
    }

    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    @Override
    public String toString() {
        return zona;
    }
    
    
    
}
