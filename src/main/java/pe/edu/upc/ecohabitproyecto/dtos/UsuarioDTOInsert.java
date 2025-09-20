package pe.edu.upc.ecohabitproyecto.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import pe.edu.upc.ecohabitproyecto.entities.Rol;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;

import java.sql.Timestamp;

public class UsuarioDTOInsert {
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String passwordHash;
    private Timestamp fechaRegistro;
    private Rol rol;
    private String passwordHash;
    private Timestamp fechaRegistro;

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
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Rol getRol() {
        return rol;
    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
