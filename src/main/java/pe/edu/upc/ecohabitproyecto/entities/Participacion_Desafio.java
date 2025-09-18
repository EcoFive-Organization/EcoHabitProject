package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="Desafio")

public class Participacion_Desafio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participacion_desafio_id")
    private long participacionDesafioId;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @Column(name = "idDesafio", nullable = false)
    private int idDesafio;

    @Column(name = "progreso", nullable = false, precision = 12, scale = 2)
    private BigDecimal progreso;

    @Column(name = "estado", length = 20)
    private String estado;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    public Participacion_Desafio() {}

    public Participacion_Desafio(long participacionDesafioId, Usuario usuario, int idDesafio, BigDecimal progreso, String estado, LocalDateTime fecha) {
        this.participacionDesafioId = participacionDesafioId;
        this.usuario = usuario;
        this.idDesafio = idDesafio;
        this.progreso = progreso;
        this.estado = estado;
        this.fecha = fecha;
    }

    public long getParticipacionDesafioId() {
        return participacionDesafioId;
    }

    public void setParticipacionDesafioId(long participacionDesafioId) {
        this.participacionDesafioId = participacionDesafioId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getIdDesafio() {
        return idDesafio;
    }

    public void setIdDesafio(int idDesafio) {
        this.idDesafio = idDesafio;
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
