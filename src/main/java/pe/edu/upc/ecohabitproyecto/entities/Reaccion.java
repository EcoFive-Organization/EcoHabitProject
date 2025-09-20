package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Reaccion")
public class Reaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReaccion;

    @Column(name = "tipoReaccion",  nullable = false, length = 20)
    private String tipoReaccion;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idPublicacion", nullable = false)
    private Publicacion publicacion;

    public Reaccion() {}

    public Reaccion(int idReaccion, String tipoReaccion, Date fecha, Usuario usuario, Publicacion publicacion) {
        this.idReaccion = idReaccion;
        this.tipoReaccion = tipoReaccion;
        this.fecha = fecha;
        this.usuario = usuario;
        this.publicacion = publicacion;
    }

    public int getIdReaccion() {
        return idReaccion;
    }

    public void setIdReaccion(int idReaccion) {
        this.idReaccion = idReaccion;
    }

    public String getTipoReaccion() {
        return tipoReaccion;
    }

    public void setTipoReaccion(String tipoReaccion) {
        this.tipoReaccion = tipoReaccion;
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
