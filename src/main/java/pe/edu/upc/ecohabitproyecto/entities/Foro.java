package pe.edu.upc.ecohabitproyecto.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "Foro")
public class Foro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_foro;

    @Column(name = "titulo",  nullable = false, length = 200)
    private String titulo;

    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;

    public Foro() {}

    public Foro(int id_foro, String titulo, String descripcion) {
        this.id_foro = id_foro;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public int getId_foro() {
        return id_foro;
    }

    public void setId_foro(int id_foro) {
        this.id_foro = id_foro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
