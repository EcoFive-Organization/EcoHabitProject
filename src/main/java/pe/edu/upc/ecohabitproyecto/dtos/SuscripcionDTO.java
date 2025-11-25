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
    private Usuario usuario;
    private PlanSuscripcion planSuscripcion;

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
