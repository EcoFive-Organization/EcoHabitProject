package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="ParticipacionDesafio")

public class ParticipacionDesafio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int participacionDesafioId;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idDesafio")
    private Desafio desafio;

    @Column(name = "progreso", nullable = false, precision = 12, scale = 2)
    private BigDecimal progreso;

    @Column(name = "estado", length = 20)
    private String estado;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    public ParticipacionDesafio() {}

    public ParticipacionDesafio(int participacionDesafioId, Usuario usuario, Desafio desafio, BigDecimal progreso, String estado, LocalDateTime fecha) {
        this.participacionDesafioId = participacionDesafioId;
        this.usuario = usuario;
        this.desafio = desafio;
        this.progreso = progreso;
        this.estado = estado;
        this.fecha = fecha;
    }

    public int getParticipacionDesafioId() {
        return participacionDesafioId;
    }

    public void setParticipacionDesafioId(int participacionDesafioId) {
        this.participacionDesafioId = participacionDesafioId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Desafio getDesafio() {
        return desafio;
    }

    public void setDesafio(Desafio desafio) {
        this.desafio = desafio;
    }

    public BigDecimal getProgreso() {
        return progreso;
    }

    public void setProgreso(BigDecimal progreso) {
        this.progreso = progreso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
