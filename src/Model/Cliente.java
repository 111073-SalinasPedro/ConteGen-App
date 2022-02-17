package Model;

public class Cliente {

    private int idCliente;
    private String nombreCliente;
    private int dniCliente;
    private int telefonoCliente;
    private boolean habitual;

    public Cliente(int idCliente, String nombreCliente, int dniCliente, int telefonoCliente, boolean habitual) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.dniCliente = dniCliente;
        this.telefonoCliente = telefonoCliente;
        this.habitual = habitual;
    }

    public Cliente(String nombreCliente, int dniCliente, int telefonoCliente, boolean habitual) {
        this.idCliente = -1;
        this.nombreCliente = nombreCliente;
        this.dniCliente = dniCliente;
        this.telefonoCliente = telefonoCliente;
        this.habitual = habitual;
    }

    public Cliente(String nombreCliente, int dniCliente, int telefonoCliente) {
        this.idCliente = -1;
        this.nombreCliente = nombreCliente;
        this.dniCliente = dniCliente;
        this.telefonoCliente = telefonoCliente;
    }

    public Cliente(String nombreCliente, int dniCliente) {
        this.idCliente = -1;
        this.nombreCliente = nombreCliente;
        this.dniCliente = dniCliente;
    }

    public Cliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    
    
    

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public int getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(int dniCliente) {
        this.dniCliente = dniCliente;
    }

    public int getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(int telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public boolean isHabitual() {
        return habitual;
    }

    public void setHabitual(boolean habitual) {
        this.habitual = habitual;
    }

    @Override
    public String toString() {
        return nombreCliente;
    }

}
