package pe.edu.upc.ecohabitproyecto.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import pe.edu.upc.ecohabitproyecto.entities.Recompensa;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;

import java.sql.Timestamp;

public class UsuarioRecompensaDTO {
    private int idUsuarioRecompensa;
    private Timestamp fecha;
    private Recompensa recompensa;
    private Usuario usuario;

    public int getIdUsuarioRecompensa() {
        return idUsuarioRecompensa;
    }

    public void setIdUsuarioRecompensa(int idUsuarioRecompensa) {
        this.idUsuarioRecompensa = idUsuarioRecompensa;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public Recompensa getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(Recompensa recompensa) {
        this.recompensa = recompensa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
