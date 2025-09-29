package pe.edu.upc.ecohabitproyecto.dtos;

import pe.edu.upc.ecohabitproyecto.entities.*;
import pe.edu.upc.ecohabitproyecto.entities.Foro;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;

import java.time.LocalDate;
import java.util.List;

public class PublicacionDTO {
    private int idPublicacion;
    private String titulo;
    private String contenido;
    private String privacidad;
    private LocalDate fecha;
    private int vistas;
    private int compartidos;
    private Foro foro;
    private List<Reaccion> reacciones;
    private List<Comentario> comentarios;
    private Usuario usuario;

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getPrivacidad() {
        return privacidad;
    }

    public void setPrivacidad(String privacidad) {
        this.privacidad = privacidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getVistas() {
        return vistas;
    }

    public void setVistas(int vistas) {
        this.vistas = vistas;
    }

    public int getCompartidos() {
        return compartidos;
    }

    public void setCompartidos(int compartidos) {
        this.compartidos = compartidos;
    }

    public Foro getForo() {
        return foro;
    }

    public void setForo(Foro foro) {
        this.foro = foro;
    }

    public List<Reaccion> getReacciones() {
        return reacciones;
    }

    public void setReacciones(List<Reaccion> reacciones) {
        this.reacciones = reacciones;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
