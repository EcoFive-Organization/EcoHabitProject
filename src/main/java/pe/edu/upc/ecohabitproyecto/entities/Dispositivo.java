package pe.edu.upc.ecohabitproyecto.entities;


import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "Dispositivo")
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDispositivo;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "tipo", nullable = true, length = 50)
    private String tipo;

    @Column(name = "ubicacion", nullable = true, length = 100)
    private String ubicacion;

    @Column(name = "fechaRegistro", nullable = false)
    private Timestamp fechaRegistro;

    public Dispositivo(int idDispositivo, Usuario usuario, String nombre, String tipo, String ubicacion, Timestamp fechaRegistro) {
        this.idDispositivo = idDispositivo;
        this.usuario = usuario;
        this.nombre = nombre;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.fechaRegistro = fechaRegistro;
    }

    public Dispositivo() {}

    public int getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(int idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
