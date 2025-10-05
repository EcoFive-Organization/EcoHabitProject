package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "desafio_amigo")
public class DesafioAmigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDesafioAmigo;

    // Usuario que crea el desafío
    @ManyToOne
    @JoinColumn(name = "id_usuario_creador", nullable = false)
    private Usuario creador;

    @Column(name = "meta", nullable = false, length = 200)
    private String meta; // Ejemplo: "Reducir electricidad 5%"

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "estado", nullable = false, length = 20)
    private String estado = "ACTIVO"; // Estado inicial

    // Relación con los amigos invitados (usuarios)
    @ManyToMany
    @JoinTable(
            name = "desafio_amigo_usuario",
            joinColumns = @JoinColumn(name = "id_desafio_amigo"),
            inverseJoinColumns = @JoinColumn(name = "id_usuario")
    )
    private Set<Usuario> amigosInvitados = new HashSet<>();

    // Constructor vacío
    public DesafioAmigo() {}

    // Constructor con parámetros
    public DesafioAmigo(int idDesafioAmigo, Usuario creador, String meta, LocalDateTime fechaCreacion, String estado) {
        this.idDesafioAmigo = idDesafioAmigo;
        this.creador = creador;
        this.meta = meta;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdDesafioAmigo() {
        return idDesafioAmigo;
    }

    public void setIdDesafioAmigo(int idDesafioAmigo) {
        this.idDesafioAmigo = idDesafioAmigo;
    }

    public Usuario getCreador() {
        return creador;
    }

    public void setCreador(Usuario creador) {
        this.creador = creador;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Set<Usuario> getAmigosInvitados() {
        return amigosInvitados;
    }

    public void setAmigosInvitados(Set<Usuario> amigosInvitados) {
        this.amigosInvitados = amigosInvitados;
    }
}