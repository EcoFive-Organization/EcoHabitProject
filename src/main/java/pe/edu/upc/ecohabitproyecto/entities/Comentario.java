package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_comentario;

    @Column(name = "contenido", nullable = false, length = 255)
    private String contenido;

    @Column(name = "fecha",  nullable = false)
    private Date fecha;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_publicacion", nullable = false)
    private Publicacion publicacion;

    public Comentario() {}

    public Comentario(int id_comentario, String contenido, Date fecha, Usuario usuario, Publicacion publicacion) {
        this.id_comentario = id_comentario;
        this.contenido = contenido;
        this.fecha = fecha;
        this.usuario = usuario;
        this.publicacion = publicacion;
    }

    public int getId_comentario() {
        return id_comentario;
    }

    public void setId_comentario(int id_comentario) {
        this.id_comentario = id_comentario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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
