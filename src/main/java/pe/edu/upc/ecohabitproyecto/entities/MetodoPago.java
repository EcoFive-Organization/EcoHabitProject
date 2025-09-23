package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "MetodoPago")
public class MetodoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMetodoPago;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "detalles", nullable = false, length = 100)
    private String detalles;

    @Column(name = "activo", nullable = false, length = 150)
    private boolean activo;

    @Column(name = "fechaRegistro", nullable = false)
    private Timestamp fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    public MetodoPago() {

    }

    public MetodoPago(int idMetodoPago, String tipo, String detalles, boolean activo, Timestamp fechaRegistro, Usuario usuario) {
        this.idMetodoPago = idMetodoPago;
        this.tipo = tipo;
        this.detalles = detalles;
        this.activo = activo;
        this.fechaRegistro = fechaRegistro;
        this.usuario = usuario;
    }

    public int getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(int idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
