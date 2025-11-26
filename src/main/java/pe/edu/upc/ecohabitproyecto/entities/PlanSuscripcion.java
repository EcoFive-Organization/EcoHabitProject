package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "PlanSuscripcion")
public class PlanSuscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlanSuscripcion;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "costo", nullable = false)
    private Double costo; // Mapea numeric(10,2)

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Column(name = "activo")
    private Boolean activo;

    @Column(name = "paypalPlanId", nullable = false, length = 100)
    private String paypalPlanId;

    public PlanSuscripcion() {

    }

    public PlanSuscripcion(Integer idPlanSuscripcion, String nombre, Double costo, String descripcion, Boolean activo, String paypalPlanId) {
        this.idPlanSuscripcion = idPlanSuscripcion;
        this.nombre = nombre;
        this.costo = costo;
        this.descripcion = descripcion;
        this.activo = activo;
        this.paypalPlanId = paypalPlanId;
    }

    public Integer getIdPlanSuscripcion() {
        return idPlanSuscripcion;
    }

    public void setIdPlanSuscripcion(Integer idPlanSuscripcion) {
        this.idPlanSuscripcion = idPlanSuscripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getPaypalPlanId() {
        return paypalPlanId;
    }

    public void setPaypalPlanId(String paypalPlanId) {
        this.paypalPlanId = paypalPlanId;
    }
}
