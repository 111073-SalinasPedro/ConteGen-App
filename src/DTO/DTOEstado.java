
package DTO;

public class DTOEstado {
    private int idPedido;
    private String estado;

    public DTOEstado(int idPedido, String estado) {
        this.idPedido = idPedido;
        this.estado = estado;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
            
}
