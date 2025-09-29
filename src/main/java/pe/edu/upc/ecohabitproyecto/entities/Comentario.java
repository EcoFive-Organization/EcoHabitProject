package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComentario;

    @Column(name = "contenido", nullable = false)
    private String contenido;

    @Column(name = "fecha",  nullable = false)
    private LocalDate fecha;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idPublicacion", nullable = false)
    private Publicacion publicacion;

    public Comentario() {}

    public Comentario(int idComentario, String contenido, LocalDate fecha, Usuario usuario, Publicacion publicacion) {
        this.idComentario = idComentario;
        this.contenido = contenido;
        this.fecha = fecha;
        this.usuario = usuario;
        this.publicacion = publicacion;
    }

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
