package pe.edu.upc.ecohabitproyecto.dtos;

import pe.edu.upc.ecohabitproyecto.entities.Publicacion;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;

import java.time.LocalDate;

public class ReaccionDTO {
    private int idReaccion;
    private String tipoReaccion;
    private LocalDate fecha;
    private Usuario usuario;
    private Publicacion publicacion;

    public int getIdReaccion() {
        return idReaccion;
    }

    public void setIdReaccion(int idReaccion) {
        this.idReaccion = idReaccion;
    }

    public String getTipoReaccion() {
        return tipoReaccion;
    }

    public void setTipoReaccion(String tipoReaccion) {
        this.tipoReaccion = tipoReaccion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }
}
