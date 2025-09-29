package pe.edu.upc.ecohabitproyecto.dtos;

public class CantidadReaccionesPublicacionDTO {
    private String titulo;
    private int cantidadReacciones;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCantidadReacciones() {
        return cantidadReacciones;
    }

    public void setCantidadReacciones(int cantidadReacciones) {
        this.cantidadReacciones = cantidadReacciones;
    }
}
