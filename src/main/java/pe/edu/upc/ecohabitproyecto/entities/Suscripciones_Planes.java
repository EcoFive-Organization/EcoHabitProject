package pe.edu.upc.ecohabitproyecto.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "Suscripciones_Planes")
public class Suscripciones_Planes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int plan_id;

    @Column(name = "plan_nomb",  nullable = false, length = 100)
    private String plan_nomb;

    @Column(name = "precio", nullable = false)
    private float precio;

    @Column(name = "descripcion",  nullable = false, length = 200)
    private String descripcion;

    public Suscripciones_Planes() {}

    public Suscripciones_Planes(int plan_id, String plan_nomb, float precio, String descripcion) {
        this.plan_id = plan_id;
        this.plan_nomb = plan_nomb;
        this.precio = precio;
        this.descripcion = descripcion;
    }

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
