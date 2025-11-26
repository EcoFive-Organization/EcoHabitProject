package pe.edu.upc.ecohabitproyecto.dtos;


public class PlanSuscripcionDTO {
    private Integer idPlanSuscripcion;
    private String nombre;
    private Double costo; // Mapea numeric(10,2)
    private String descripcion;
    private String paypalPlanId;

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

    public String getPaypalPlanId() {
        return paypalPlanId;
    }

    public void setPaypalPlanId(String paypalPlanId) {
        this.paypalPlanId = paypalPlanId;
    }
}
