package pe.edu.upc.ecohabitproyecto.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "Recursos")
public class Recursos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recurso_id;

    @Column(name = "recurso_nombre", nullable = false, length = 50)
    private String recurso_nombre;

    public Recursos() {}

    public Recursos(int recurso_id, String recurso_nombre) {
        this.recurso_id = recurso_id;
        this.recurso_nombre = recurso_nombre;
    }

    public int getRecurso_id() {
        return recurso_id;
    }

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
