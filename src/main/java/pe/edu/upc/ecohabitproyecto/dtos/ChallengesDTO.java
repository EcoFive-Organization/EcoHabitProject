package pe.edu.upc.ecohabitproyecto.dtos;

import jakarta.persistence.Column;

import java.util.Date;

public class ChallengesDTO {

    // Atributos de la carpeta entities

    private int desafio_id;
    private String desafio_nombre;
    private String descripcion;
    private Date inicio_fecha;
    private Date fin_fecha;
    private int premios_puntos;


    // Se añade get y set porque son de tipo private

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
