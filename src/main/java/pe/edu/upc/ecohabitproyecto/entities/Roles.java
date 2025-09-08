package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rol_id;

    @Column(name = "nombre_rol", nullable = false, length = 50)
    private String nombre_rol;

    public Roles() {
    }

    public Roles(int rol_id, String nombre_rol) {
        this.rol_id = rol_id;
        this.nombre_rol = nombre_rol;
    }

    public int getRol_id() {
        return rol_id;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }
}
