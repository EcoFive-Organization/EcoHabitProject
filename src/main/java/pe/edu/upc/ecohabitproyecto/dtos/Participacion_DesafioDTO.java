package pe.edu.upc.ecohabitproyecto.dtos;

import pe.edu.upc.ecohabitproyecto.entities.Usuario;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Participacion_DesafioDTO {
    private long participacionDesafioId;
    private Usuario usuario;
    private int idDesafio;
    private BigDecimal progreso;
    private String estado;
    private LocalDateTime fecha;

    public Participacion_DesafioDTO() {}

    public long getParticipacionDesafioId() {
        return participacionDesafioId;
    }

    public void setParticipacionDesafioId(long participacionDesafioId) {
        this.participacionDesafioId = participacionDesafioId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getIdDesafio() {
        return idDesafio;
    }

    public void setIdDesafio(int idDesafio) {
        this.idDesafio = idDesafio;
    }

    public BigDecimal getProgreso() {
        return progreso;
    }

    public void setProgreso(BigDecimal progreso) {
        this.progreso = progreso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
