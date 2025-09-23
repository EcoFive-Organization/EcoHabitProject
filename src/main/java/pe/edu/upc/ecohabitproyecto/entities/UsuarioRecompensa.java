package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "UsuarioRecompensa")
public class UsuarioRecompensa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuarioRecompensa;

    @Column(name = "fecha", nullable = false)
    private Timestamp fecha;

    @ManyToOne
    @JoinColumn(name = "idRecompensa", nullable = false)
    private Recompensa recompensa;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    public UsuarioRecompensa() {

    }

    public UsuarioRecompensa(int idUsuarioRecompensa, Timestamp fecha, Recompensa recompensa, Usuario usuario) {
        this.idUsuarioRecompensa = idUsuarioRecompensa;
        this.fecha = fecha;
        this.recompensa = recompensa;
        this.usuario = usuario;
    }

    public int getIdUsuarioRecompensa() {
        return idUsuarioRecompensa;
    }

    public void setIdUsuarioRecompensa(int idUsuarioRecompensa) {
        this.idUsuarioRecompensa = idUsuarioRecompensa;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public Recompensa getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(Recompensa recompensa) {
        this.recompensa = recompensa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
