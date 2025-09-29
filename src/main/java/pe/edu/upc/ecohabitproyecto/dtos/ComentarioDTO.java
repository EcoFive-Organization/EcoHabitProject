package pe.edu.upc.ecohabitproyecto.dtos;

import pe.edu.upc.ecohabitproyecto.entities.Publicacion;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;

import java.time.LocalDate;

public class ComentarioDTO {
    private int idComentario;
    private String contenido;
    private LocalDate fecha;
    private Usuario usuario;
    private Publicacion publicacion;

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }
}
