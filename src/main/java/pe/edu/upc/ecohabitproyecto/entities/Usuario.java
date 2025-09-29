package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "passwordHash", nullable = false, length = 150)
    private String passwordHash;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsuario")
    private List<Rol> roles;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsuario")
    private List<Dispositivo> dispositivos;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsuario")
    private List<UsuarioRecompensa> usuarioRecompensas;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsuario")
    private List<Suscripcion> suscripciones;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsuario")
    private List<UsuarioLogro> usuarioLogros;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsuario")
    private List<MetodoPago> metodoPagos;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsuario")
    private List<Publicacion> publicaciones;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsuario")
    private List<Comentario> comentarios;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsuario")
    private List<Reaccion> reacciones;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsuario")
    private List<ParticipacionDesafio> participacionDesafios;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsuario")
    private List<Billetera> billeteras;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombre, String email, String passwordHash, Boolean enabled, List<Rol> roles, List<Dispositivo> dispositivos, List<UsuarioRecompensa> usuarioRecompensas, List<Suscripcion> suscripciones, List<UsuarioLogro> usuarioLogros, List<MetodoPago> metodoPagos, List<Publicacion> publicaciones, List<Comentario> comentarios, List<Reaccion> reacciones, List<ParticipacionDesafio> participacionDesafios, List<Billetera> billeteras) {
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
}
