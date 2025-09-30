package pe.edu.upc.ecohabitproyecto.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Publicacion")
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPublicacion;

    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @Column(name = "contenido", nullable = false)
    private String contenido;

    @Column(name = "privacidad",  nullable = false, length = 20)
    private String privacidad;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "vistas", nullable = false)
    private int vistas;

    @Column(name = "compartidos", nullable = false)
    private int compartidos;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "idForo", nullable = false) //FK
    @JsonIgnore
    private Foro foro;

    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reaccion> reacciones;

    @OneToMany(mappedBy = "publicacion", cascade =  CascadeType.ALL)
    @JsonIgnore
    private List<Comentario> comentarios;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    @JsonIgnore
    private Usuario usuario;


    public Publicacion() {}

    public Publicacion(int idPublicacion, String titulo, String contenido, String privacidad, LocalDate fecha, int vistas, int compartidos, Foro foro, List<Reaccion> reacciones, List<Comentario> comentarios, Usuario usuario) {
        this.idPublicacion = idPublicacion;
        this.titulo = titulo;
        this.contenido = contenido;
        this.privacidad = privacidad;
        this.fecha = fecha;
        this.vistas = vistas;
        this.compartidos = compartidos;
        this.foro = foro;
        this.reacciones = reacciones;
        this.comentarios = comentarios;
        this.usuario = usuario;
    }

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
