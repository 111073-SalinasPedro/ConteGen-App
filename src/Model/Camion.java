package Model;


public class Camion {
    
    private int idCamion;
    private String patente;
    private int idConductor;

    public Camion(int idCamion, String patente, int idConductor) {
        this.idCamion = idCamion;
        this.patente = patente;
        this.idConductor = idConductor;
    }
    
    

    public int getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(int idCamion) {
        this.idCamion = idCamion;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public int getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
    }

    @Override
    public String toString() {
        return "Camion:"+ idCamion;
    }
    
    
}
