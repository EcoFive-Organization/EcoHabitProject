package pe.edu.upc.ecohabitproyecto.dtos;

import jakarta.persistence.Column;

public class RolesDTO {
    private int rol_id;
    private String nombre_rol;

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
