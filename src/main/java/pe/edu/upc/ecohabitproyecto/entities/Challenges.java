package pe.edu.upc.ecohabitproyecto.entities;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Challenges")
public class Challenges {

    /*A cada atributo de la clase se le agrega el @Column, a excepción de la primary key*/
    // Anotación para decirle al programa la primary key con @Id
    // Aplica la propiedad Identity (el id aumenta automaticamente de 1 en 1) con @GeneratedValue

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int desafio_id;

    // nullable = false, para que no permita nulos
    // length = 50, establece 50 caracteres para un string
    @Column(name = "desafio_nombre", nullable = false, length = 255)
    private String desafio_nombre;

    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;

    @Column(name = "inicio_fecha", nullable = false)
    @Temporal(TemporalType.DATE) // Para que en la BD y PostMan aparezcan como solo fecha
    private Date inicio_fecha;

    @Column(name = "fin_fecha",  nullable = false)
    @Temporal(TemporalType.DATE) // Para que en la BD y PostMan aparezcan como solo fecha
    private Date fin_fecha;

    @Column(name = "premios_puntos",  nullable = false)
    private int premios_puntos;

    public Challenges() {}

    public Challenges(int desafio_id, String desafio_nombre, String descripcion, int premios_puntos, Date fin_fecha, Date inicio_fecha) {
        this.desafio_id = desafio_id;
        this.desafio_nombre = desafio_nombre;
        this.descripcion = descripcion;
        this.premios_puntos = premios_puntos;
        this.fin_fecha = fin_fecha;
        this.inicio_fecha = inicio_fecha;
    }

    public int getDesafio_id() {
        return desafio_id;
    }

    public void setDesafio_id(int desafio_id) {
        this.desafio_id = desafio_id;
    }

    public String getDesafio_nombre() {
        return desafio_nombre;
    }

    public void setDesafio_nombre(String desafio_nombre) {
        this.desafio_nombre = desafio_nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getInicio_fecha() {
        return inicio_fecha;
    }

    public void setInicio_fecha(Date inicio_fecha) {
        this.inicio_fecha = inicio_fecha;
    }

    public Date getFin_fecha() {
        return fin_fecha;
    }

    public void setFin_fecha(Date fin_fecha) {
        this.fin_fecha = fin_fecha;
    }

    public int getPremios_puntos() {
        return premios_puntos;
    }

    public void setPremios_puntos(int premios_puntos) {
        this.premios_puntos = premios_puntos;
    }
}
