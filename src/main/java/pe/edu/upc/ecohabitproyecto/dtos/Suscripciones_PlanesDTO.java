package pe.edu.upc.ecohabitproyecto.dtos;

import jakarta.persistence.Column;

public class Suscripciones_PlanesDTO {
    // Traer atributos de la carpeta entities
    private int plan_id;
    private String plan_nomb;
    private float precio;
    private String descripcion;

    // Se añade get y set porque están como private
    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public String getPlan_nomb() {
        return plan_nomb;
    }

    public void setPlan_nomb(String plan_nomb) {
        this.plan_nomb = plan_nomb;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
