package pe.edu.upc.ecohabitproyecto.dtos;

public class LogroDTO {
    private Integer idLogro;
    private String nombre;
    private String descripcion;
    private Integer puntos;
    private Integer idRecompensa;
    private String estado;

    // Getters y setters
    public Integer getIdLogro() {
        return idLogro;
    }

    public void setIdLogro(Integer idLogro) {
        this.idLogro = idLogro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Integer getIdRecompensa() {
        return idRecompensa;
    }

    public void setIdRecompensa(Integer idRecompensa) {
        this.idRecompensa = idRecompensa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}