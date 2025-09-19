package pe.edu.upc.ecohabitproyecto.dtos;

import pe.edu.upc.ecohabitproyecto.entities.Usuario;

import java.time.LocalDateTime;

public class UsuarioLogroDTO {
    private int usuarioLogroId;
    private Usuario usuario;
    private int idLogro;
    private LocalDateTime fechaObtenido;

    public int getUsuarioLogroId() {
        return usuarioLogroId;
    }

    public void setUsuarioLogroId(int usuarioLogroId) {
        this.usuarioLogroId = usuarioLogroId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getIdLogro() {
        return idLogro;
    }

    public void setIdLogro(int idLogro) {
        this.idLogro = idLogro;
    }

    public LocalDateTime getFechaObtenido() {
        return fechaObtenido;
    }

    public void setFechaObtenido(LocalDateTime fechaObtenido) {
        this.fechaObtenido = fechaObtenido;
    }
}
