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

    @Column(name = "paypalSuscripcionId", nullable = false, length = 100)
    private String paypalSuscripcionId;

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

    public Suscripcion(int idSuscripcion, LocalDate fechaInicio, LocalDate fechaFin, String estado, String paypalSuscripcionId, Usuario usuario, PlanSuscripcion planSuscripcion) {
        this.idSuscripcion = idSuscripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.paypalSuscripcionId = paypalSuscripcionId;
        this.usuario = usuario;
        this.planSuscripcion = planSuscripcion;
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

    public String getPaypalSuscripcionId() {
        return paypalSuscripcionId;
    }

    public void setPaypalSuscripcionId(String paypalSuscripcionId) {
        this.paypalSuscripcionId = paypalSuscripcionId;
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
