package pe.edu.upc.ecohabitproyecto.dtos;

import jakarta.persistence.Column;

public class RecursosDTO {

    // Traer los atributos de la clase, que está en la carpeta emtities
    private int recurso_id;
    private String recurso_nombre;

    public int getRecurso_id() {
        return recurso_id;
    }

    // Como son de tipo private, se añade get and set
    public void setRecurso_id(int recurso_id) {
        this.recurso_id = recurso_id;
    }

    public String getRecurso_nombre() {
        return recurso_nombre;
    }

    public void setRecurso_nombre(String recurso_nombre) {
        this.recurso_nombre = recurso_nombre;
    }
}
