package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Logros")
public class Logros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int logros_id;

    @Column(name = "logro_nombre", nullable = false, length = 255)
    private String logro_nombre;

    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;

    @Column(name = "premio_puntos", nullable = false)
    private int premio_puntos;

    public Logros() {
    }

    public Logros(int logros_id, String logro_nombre, String descripcion, int premio_puntos) {
        this.logros_id = logros_id;
        this.logro_nombre = logro_nombre;
        this.descripcion = descripcion;
        this.premio_puntos = premio_puntos;
    }

    public int getLogros_id() {
        return logros_id;
    }

    public void setLogros_id(int logros_id) {
        this.logros_id = logros_id;
    }

    public String getLogro_nombre() {
        return logro_nombre;
    }

    public void setLogro_nombre(String logro_nombre) {
        this.logro_nombre = logro_nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPremio_puntos() {
        return premio_puntos;
    }

    public void setPremio_puntos(int premio_puntos) {
        this.premio_puntos = premio_puntos;
    }
}
