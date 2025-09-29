package pe.edu.upc.ecohabitproyecto.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Contenido_Educativo")
public class ContenidoEducativo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idContenidoEducativo;

    @Column(name = "titulo",  nullable = false, length = 200)
    private String titulo;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "url", nullable = false, length = 255)
    private String url;

    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;

    @Column(name = "fechaPublicacion", nullable = false)
    private LocalDate fechaPublicacion;

    public ContenidoEducativo() {}

    public ContenidoEducativo(int idContenidoEducativo, String titulo, String tipo, String url, String descripcion, LocalDate fechaPublicacion) {
        this.idContenidoEducativo = idContenidoEducativo;
        this.titulo = titulo;
        this.tipo = tipo;
        this.url = url;
        this.descripcion = descripcion;
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getIdContenidoEducativo() {
        return idContenidoEducativo;
    }

    public void setIdContenidoEducativo(int idContenidoEducativo) {
        this.idContenidoEducativo = idContenidoEducativo;
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

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
}
