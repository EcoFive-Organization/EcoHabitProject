package pe.edu.upc.ecohabitproyecto.dtos;

public class MetodoPagoDTO {
    private int idMetodoPago;
    private String tipo;
    private String detalles; // <-- alineado con la columna de la BD

    public int getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(int idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
}