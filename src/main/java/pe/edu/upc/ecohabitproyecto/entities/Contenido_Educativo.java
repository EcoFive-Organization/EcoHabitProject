package pe.edu.upc.ecohabitproyecto.entities;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Contenido_Educativo")
public class Contenido_Educativo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_contenidoEducativo;

    @Column(name = "titulo",  nullable = false, length = 200)
    private String titulo;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "url", nullable = false, length = 255)
    private String url;

    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;

    @Column(name = "fecha_publicacion", nullable = false)
    private Date fecha_publicacion;

    public Contenido_Educativo() {}

    public Contenido_Educativo(int id_contenidoEducativo, String titulo, String tipo, String url, String descripcion, Date fecha_publicacion) {
        this.id_contenidoEducativo = id_contenidoEducativo;
        this.titulo = titulo;
        this.tipo = tipo;
        this.url = url;
        this.descripcion = descripcion;
        this.fecha_publicacion = fecha_publicacion;
    }

    public int getId_contenidoEducativo() {
        return id_contenidoEducativo;
    }

    public void setId_contenidoEducativo(int id_contenidoEducativo) {
        this.id_contenidoEducativo = id_contenidoEducativo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(Date fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }
}
