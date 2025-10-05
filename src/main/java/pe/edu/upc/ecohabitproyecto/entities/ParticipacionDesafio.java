package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "participacion_desafio")
public class ParticipacionDesafio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int participacionDesafioId;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_desafio", nullable = false)
    private Desafio desafio;

    @Column(name = "progreso", nullable = false, precision = 12, scale = 2)
    private BigDecimal progreso = BigDecimal.ZERO; // inicia en 0

    @Column(name = "estado", nullable = false, length = 20)
    private String estado = "ACTIVO"; // por defecto activo

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha = LocalDateTime.now(); // fecha de unión

    // Constructor vacío
    public ParticipacionDesafio() {}

    // Getters y Setters
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