package pe.edu.upc.ecohabitproyecto.dtos;

public class CantidadConsumoDTO {
    private String tipo;
    private Long cantidad;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
}
