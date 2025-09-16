package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.*;

import java.util.Date;

public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_publicacion;

    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @Column(name = "contenido", nullable = false, length = 255)
    private String contenido;

    @Column(name = "privacidad",  nullable = false, length = 20)
    private String privacidad;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "vistas", nullable = false)
    private int vistas;

    @Column(name = "compartidos", nullable = false)
    private int compartidos;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "id_foro", nullable = false) //FK
    private Foro foro;

    @OneToMany
    @JoinColumn(name = "id_reaccion", nullable = false)
    private Reaccion reaccion;

    @OneToMany
    @JoinColumn(name = "id_comentario", nullable = false)
    private Comentario comentario;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;


    public Publicacion() {}

    public Publicacion(int id_publicacion, String titulo, String contenido, String privacidad, Date fecha, int vistas, int compartidos, Foro foro, Reaccion reaccion, Comentario comentario, Usuario usuario) {
        this.id_publicacion = id_publicacion;
        this.titulo = titulo;
        this.contenido = contenido;
        this.privacidad = privacidad;
        this.fecha = fecha;
        this.vistas = vistas;
        this.compartidos = compartidos;
        this.foro = foro;
        this.reaccion = reaccion;
        this.comentario = comentario;
        this.usuario = usuario;
    }

    public int getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(int id_publicacion) {
        this.id_publicacion = id_publicacion;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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

    public Reaccion getReaccion() {
        return reaccion;
    }

    public void setReaccion(Reaccion reaccion) {
        this.reaccion = reaccion;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
