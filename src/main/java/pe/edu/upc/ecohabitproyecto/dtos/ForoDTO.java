package pe.edu.upc.ecohabitproyecto.dtos;

import jakarta.persistence.Column;

public class ForoDTO {
    private int id_foro;
    private String titulo;
    private String descripcion;

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
