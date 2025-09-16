package pe.edu.upc.ecohabitproyecto.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

public class Contenido_EducativoDTO {
    private int id_contenidoEducativo;
    private String titulo;
    private String tipo;
    private String url;
    private String descripcion;
    private Date fecha_publicacion;

    public int getId_contenidoEducativo() {
        return id_contenidoEducativo;
    }

    public void setId_contenidoEducativo(int id_contenidoEducativo) {
        this.id_contenidoEducativo = id_contenidoEducativo;
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

    public Date getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(Date fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }
}
