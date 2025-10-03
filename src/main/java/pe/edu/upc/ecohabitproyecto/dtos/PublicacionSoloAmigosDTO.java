package pe.edu.upc.ecohabitproyecto.dtos;

import java.time.LocalDate;

public class PublicacionSoloAmigosDTO {
    private String titulo;
    private String contenido;
    private String privacidad;
    private int vistas;
    private int compartidos;
    private LocalDate fecha;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getPrivacidad() {
        return privacidad;
    }

    public void setPrivacidad(String privacidad) {
        this.privacidad = privacidad;
    }

    public int getVistas() {
        return vistas;
    }

    public void setVistas(int vistas) {
        this.vistas = vistas;
    }

    public int getCompartidos() {
        return compartidos;
    }

    public void setCompartidos(int compartidos) {
        this.compartidos = compartidos;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
