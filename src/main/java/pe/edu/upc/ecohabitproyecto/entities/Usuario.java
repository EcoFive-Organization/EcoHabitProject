package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "passwordHash", nullable = false, length = 150)
    private String passwordHash;
    @Column(name = "passwordHash", nullable = false)
    private String passwordHash;

    @Column(name = "fechaRegistro", nullable = true)
    private Timestamp fechaRegistro;

    @ManyToOne // varios usuarios pueden pertenecer a un rol
    @JoinColumn(name = "idRol", nullable = false)
    private Rol rol;
    @Column(name = "fechaRegistro")
    private Timestamp fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "idRol")
    private Rol rol;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombre, String apellido, String email, String passwordHash, Timestamp fechaRegistro, Rol rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.passwordHash = passwordHash;
        this.fechaRegistro = fechaRegistro;
        this.rol = rol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
