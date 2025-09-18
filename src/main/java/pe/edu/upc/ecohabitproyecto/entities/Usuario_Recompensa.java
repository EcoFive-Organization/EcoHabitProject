package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "Usuario_Recompensa")
public class Usuario_Recompensa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario_recompensa;

    @Column(name = "fecha",nullable = true)
    private Timestamp fecha;

    @ManyToOne
    @JoinColumn(name = "id_recompensa")
    private Recompensa recompensa;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Usuario_Recompensa(int id_usuario_recompensa, Timestamp fecha, Recompensa recompensa, Usuario usuario) {
        this.id_usuario_recompensa = id_usuario_recompensa;
        this.fecha = fecha;
        this.recompensa = recompensa;
        this.usuario = usuario;
    }

    public Usuario_Recompensa() {

    }

    public int getId_usuario_recompensa() {
        return id_usuario_recompensa;
    }

    public void setId_usuario_recompensa(int id_usuario_recompensa) {
        this.id_usuario_recompensa = id_usuario_recompensa;
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
