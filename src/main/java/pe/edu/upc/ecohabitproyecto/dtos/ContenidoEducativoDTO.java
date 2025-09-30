package pe.edu.upc.ecohabitproyecto.dtos;

import java.time.LocalDate;

public class ContenidoEducativoDTO {
    private int idContenidoEducativo;
    private String titulo;
    private String tipo;
    private String url;
    private String descripcion;
    private LocalDate fechaPublicacion;

    public int getIdContenidoEducativo() {
        return idContenidoEducativo;
    }

    public void setIdContenidoEducativo(int idContenidoEducativo) {
        this.idContenidoEducativo = idContenidoEducativo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
}
