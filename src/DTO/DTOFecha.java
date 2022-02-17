package DTO;

public class DTOFecha {
    private String desdeFechaCliente;
    private String hastaFechaCliente;

    public DTOFecha(String desdeFechaCliente, String hastaFechaCliente) {
        this.desdeFechaCliente = desdeFechaCliente;
        this.hastaFechaCliente = hastaFechaCliente;
    }

    public String getDesdeFechaCliente() {
        return desdeFechaCliente;
    }

    public void setDesdeFechaCliente(String desdeFechaCliente) {
        this.desdeFechaCliente = desdeFechaCliente;
    }

    public String getHastaFechaCliente() {
        return hastaFechaCliente;
    }

    public void setHastaFechaCliente(String hastaFechaCliente) {
        this.hastaFechaCliente = hastaFechaCliente;
    }
    
    
}
