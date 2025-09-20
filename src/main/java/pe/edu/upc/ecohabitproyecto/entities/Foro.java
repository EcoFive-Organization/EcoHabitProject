package pe.edu.upc.ecohabitproyecto.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "Foro")
public class Foro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idForo;

    @Column(name = "titulo",  nullable = false, length = 200)
    private String titulo;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    public Foro() {

    }

    public Foro(int idForo, String titulo, String descripcion) {
        this.idForo = idForo;
    public Foro(int idForo, String titulo, String descripcion) {
        this.idForo = idForo;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public int getIdForo() {
        return idForo;
    }

    public void setIdForo(int idForo) {
        this.idForo = idForo;
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
