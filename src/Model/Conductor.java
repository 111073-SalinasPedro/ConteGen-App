package Model;


public class Conductor {
    private int idConductor;
    private String nombreConductor;
    private String fechaNacimiento;
    private int dniConductor;
    private int telefonoConductor;
    private int idCamion;

    public Conductor(int idConductor, String nombreConductor, String fechaNacimiento, int dniConductor, int telefonoConductor) {
        this.idConductor = idConductor;
        this.nombreConductor = nombreConductor;
        this.fechaNacimiento = fechaNacimiento;
        this.dniConductor = dniConductor;
        this.telefonoConductor = telefonoConductor;
    }

    public Conductor(String nombreConductor, String fechaNacimiento, int dniConductor, int telefonoConductor) {
        this.nombreConductor = nombreConductor;
        this.fechaNacimiento = fechaNacimiento;
        this.dniConductor = dniConductor;
        this.telefonoConductor = telefonoConductor;
    }

    public Conductor(String nombreConductor, String fechaNacimiento, int dniConductor, int telefonoConductor, int idCamion) {
        this.nombreConductor = nombreConductor;
        this.fechaNacimiento = fechaNacimiento;
        this.dniConductor = dniConductor;
        this.telefonoConductor = telefonoConductor;
        this.idCamion = idCamion;
    }
    
    

    public int getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(int idCamion) {
        this.idCamion = idCamion;
    }
    
    

    public int getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
    }

    public String getNombreConductor() {
        return nombreConductor;
    }

    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getDniConductor() {
        return dniConductor;
    }

    public void setDniConductor(int dniConductor) {
        this.dniConductor = dniConductor;
    }

    public int getTelefonoConductor() {
        return telefonoConductor;
    }

    public void setTelefonoConductor(int telefonoConductor) {
        this.telefonoConductor = telefonoConductor;
    }
    
    
}
