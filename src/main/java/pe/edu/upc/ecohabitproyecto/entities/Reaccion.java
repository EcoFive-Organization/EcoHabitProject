package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

public class Reaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_reaccion;

    @Column(name = "tipo_reaccion",  nullable = false, length = 20)
    private String tipo_reaccion;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    public Reaccion() {}

    public Reaccion(int id_reaccion, String tipo_reaccion, Date fecha) {
        this.id_reaccion = id_reaccion;
        this.tipo_reaccion = tipo_reaccion;
        this.fecha = fecha;
    }

    public int getId_reaccion() {
        return id_reaccion;
    }

    public void setId_reaccion(int id_reaccion) {
        this.id_reaccion = id_reaccion;
    }

    public String getTipo_reaccion() {
        return tipo_reaccion;
    }

    public void setTipo_reaccion(String tipo_reaccion) {
        this.tipo_reaccion = tipo_reaccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
