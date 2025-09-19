package pe.edu.upc.ecohabitproyecto.entities;


import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "Dispositivo")
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_dispositivo;

    @ManyToOne
    @JoinColumn(name = "id_Usuario")
    private Usuario usuario;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "tipo", nullable = true, length = 50)
    private String tipo;

    @Column(name = "ubicacion", nullable = true, length = 100)
    private String ubicacion;

    @Column(name = "fecha_registro", nullable = false)
    private Timestamp fecha_registro;

    public Dispositivo(int id_dispositivo, Usuario usuario, String nombre, String tipo, String ubicacion, Timestamp fecha_registro) {
        this.id_dispositivo = id_dispositivo;
        this.usuario = usuario;
        this.nombre = nombre;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.fecha_registro = fecha_registro;
    }

    public Dispositivo() {}

    public int getId_dispositivo() {
        return id_dispositivo;
    }

    public void setId_dispositivo(int id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
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

    public Timestamp getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Timestamp fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
}
