package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.*;

import java.util.Date;

public class Reaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_reaccion;

    @Column(name = "tipo_reaccion",  nullable = false, length = 20)
    private String tipo_reaccion;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_publicacion", nullable = false)
    private Publicacion publicacion;


    public Reaccion() {}

    public Reaccion(int id_reaccion, String tipo_reaccion, Date fecha, Usuario usuario, Publicacion publicacion) {
        this.id_reaccion = id_reaccion;
        this.tipo_reaccion = tipo_reaccion;
        this.fecha = fecha;
        this.usuario = usuario;
        this.publicacion = publicacion;
    }

    public int getId_reaccion() {
        return id_reaccion;
    }

    public void setId_reaccion(int id_reaccion) {
        this.id_reaccion = id_reaccion;
    }

    public String getTipo_reaccion() {
        return tipo_reaccion;
    }

    public void setTipo_reaccion(String tipo_reaccion) {
        this.tipo_reaccion = tipo_reaccion;
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
