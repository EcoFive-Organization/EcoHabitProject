package pe.edu.upc.ecohabitproyecto.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "email", nullable = false, length = 150, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 150)
    private String passwordHash;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    // 🔹 Relaciones
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Rol> roles;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Dispositivo> dispositivos;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<UsuarioRecompensa> usuarioRecompensas;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Suscripcion> suscripciones;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<UsuarioLogro> usuarioLogros;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<MetodoPago> metodoPagos;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Publicacion> publicaciones;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comentario> comentarios;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reaccion> reacciones;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ParticipacionDesafio> participacionDesafios;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Billetera> billeteras;

    // 🔹 Constructor vacío
    public Usuario() {}

    // 🔹 Constructor con parámetros
    public Usuario(Integer idUsuario, String nombre, String email, String passwordHash, Boolean enabled,
                   List<Rol> roles, List<Dispositivo> dispositivos, List<UsuarioRecompensa> usuarioRecompensas,
                   List<Suscripcion> suscripciones, List<UsuarioLogro> usuarioLogros, List<MetodoPago> metodoPagos,
                   List<Publicacion> publicaciones, List<Comentario> comentarios, List<Reaccion> reacciones,
                   List<ParticipacionDesafio> participacionDesafios, List<Billetera> billeteras) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.passwordHash = passwordHash;
        this.enabled = enabled;
        this.roles = roles;
        this.dispositivos = dispositivos;
        this.usuarioRecompensas = usuarioRecompensas;
        this.suscripciones = suscripciones;
        this.usuarioLogros = usuarioLogros;
        this.metodoPagos = metodoPagos;
        this.publicaciones = publicaciones;
        this.comentarios = comentarios;
        this.reacciones = reacciones;
        this.participacionDesafios = participacionDesafios;
        this.billeteras = billeteras;
    }

    // 🔹 Getters y Setters
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public List<Dispositivo> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(List<Dispositivo> dispositivos) {
        this.dispositivos = dispositivos;
    }

    public List<UsuarioRecompensa> getUsuarioRecompensas() {
        return usuarioRecompensas;
    }

    public void setUsuarioRecompensas(List<UsuarioRecompensa> usuarioRecompensas) {
        this.usuarioRecompensas = usuarioRecompensas;
    }

    public List<Suscripcion> getSuscripciones() {
        return suscripciones;
    }

    public void setSuscripciones(List<Suscripcion> suscripciones) {
        this.suscripciones = suscripciones;
    }

    public List<UsuarioLogro> getUsuarioLogros() {
        return usuarioLogros;
    }

    public void setUsuarioLogros(List<UsuarioLogro> usuarioLogros) {
        this.usuarioLogros = usuarioLogros;
    }

    public List<MetodoPago> getMetodoPagos() {
        return metodoPagos;
    }

    public void setMetodoPagos(List<MetodoPago> metodoPagos) {
        this.metodoPagos = metodoPagos;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Reaccion> getReacciones() {
        return reacciones;
    }

    public void setReacciones(List<Reaccion> reacciones) {
        this.reacciones = reacciones;
    }

    public List<ParticipacionDesafio> getParticipacionDesafios() {
        return participacionDesafios;
    }

    public void setParticipacionDesafios(List<ParticipacionDesafio> participacionDesafios) {
        this.participacionDesafios = participacionDesafios;
    }

    public List<Billetera> getBilleteras() {
        return billeteras;
    }

    public void setBilleteras(List<Billetera> billeteras) {
        this.billeteras = billeteras;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return idUsuario != null && idUsuario.equals(usuario.getIdUsuario());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}