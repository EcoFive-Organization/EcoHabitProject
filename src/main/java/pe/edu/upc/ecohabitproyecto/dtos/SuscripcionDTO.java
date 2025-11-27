package pe.edu.upc.ecohabitproyecto.dtos;

import pe.edu.upc.ecohabitproyecto.entities.PlanSuscripcion;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;

import java.sql.Date;
import java.time.LocalDate;

public class SuscripcionDTO {
    private int idSuscripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;
    private String paypalSuscripcionId;

    // Usamos Integer, NO objetos Usuario/Plan
    private Integer idUsuario;
    private Integer idPlanSuscripcion;

    private String nombreUsuario;
    private String nombrePlan;

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

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdPlanSuscripcion() {
        return idPlanSuscripcion;
    }

    public void setIdPlanSuscripcion(Integer idPlanSuscripcion) {
        this.idPlanSuscripcion = idPlanSuscripcion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }
}
