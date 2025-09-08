package pe.edu.upc.ecohabitproyecto.dtos;

public class LogrosDTO {
    private int logros_id;
    private String logro_nombre;
    private String descripcion;
    private int premio_puntos;

    public int getLogros_id() {
        return logros_id;
    }

    public void setLogros_id(int logros_id) {
        this.logros_id = logros_id;
    }

    public String getLogro_nombre() {
        return logro_nombre;
    }

    public void setLogro_nombre(String logro_nombre) {
        this.logro_nombre = logro_nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPremio_puntos() {
        return premio_puntos;
    }

    public void setPremio_puntos(int premio_puntos) {
        this.premio_puntos = premio_puntos;
    }
}
