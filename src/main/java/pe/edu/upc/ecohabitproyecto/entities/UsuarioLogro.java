package pe.edu.upc.ecohabitproyecto.entities;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "UsuarioLogro")

public class UsuarioLogro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usuarioLogroId;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @Column(name = "idLogro", nullable = false)
    private int idLogro;

    @Column(name = "fechaObtenido", nullable = false)
    private LocalDateTime fechaObtenido;

    public UsuarioLogro() {}

    public UsuarioLogro(int usuarioLogroId, Usuario usuario, int idLogro, LocalDateTime fechaObtenido) {
        this.usuarioLogroId = usuarioLogroId;
        this.usuario = usuario;
        this.idLogro = idLogro;
        this.fechaObtenido = fechaObtenido;
    }

    public int getUsuarioLogroId() {
        return usuarioLogroId;
    }

    public void setUsuarioLogroId(int usuarioLogroId) {
        this.usuarioLogroId = usuarioLogroId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getIdLogro() {
        return idLogro;
    }

    public void setIdLogro(int idLogro) {
        this.idLogro = idLogro;
    }

    public LocalDateTime getFechaObtenido() {
        return fechaObtenido;
    }

    public void setFechaObtenido(LocalDateTime fechaObtenido) {
        this.fechaObtenido = fechaObtenido;
    }
}
