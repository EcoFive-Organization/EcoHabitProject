package pe.edu.upc.ecohabitproyecto.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "Suscripcion")
public class Suscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSuscripcion;

    @Column(name = "fechaInicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fechaFin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idPlanSuscripcion", nullable = false) //FK
    @JsonIgnore
    private PlanSuscripcion planSuscripcion;

    public Suscripcion() {

    }

    public Suscripcion(PlanSuscripcion planSuscripcion, Usuario usuario, String estado, LocalDate fechaFin, LocalDate fechaInicio, int idSuscripcion) {
        this.planSuscripcion = planSuscripcion;
        this.usuario = usuario;
        this.estado = estado;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.idSuscripcion = idSuscripcion;
    }

    public int getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(int idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PlanSuscripcion getPlanSuscripcion() {
        return planSuscripcion;
    }

    public void setPlanSuscripcion(PlanSuscripcion planSuscripcion) {
        this.planSuscripcion = planSuscripcion;
    }
}
