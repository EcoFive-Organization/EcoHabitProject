package pe.edu.upc.ecohabitproyecto.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class DesafioAmigoDTO {

    private int idDesafioAmigo;
    private int idCreador; // ID del usuario que crea el desafío
    private String meta;   // Ejemplo: "Reducir electricidad 5%"
    private LocalDateTime fechaCreacion;
    private String estado;

    // Lista de IDs de amigos invitados
    private List<Integer> amigosIds;

    // Constructor vacío
    public DesafioAmigoDTO() {}

    // Constructor con parámetros
    public DesafioAmigoDTO(int idDesafioAmigo, int idCreador, String meta,
                           LocalDateTime fechaCreacion, String estado, List<Integer> amigosIds) {
        this.idDesafioAmigo = idDesafioAmigo;
        this.idCreador = idCreador;
        this.meta = meta;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.amigosIds = amigosIds;
    }


    public int getIdDesafioAmigo() {
        return idDesafioAmigo;
    }

    public void setIdDesafioAmigo(int idDesafioAmigo) {
        this.idDesafioAmigo = idDesafioAmigo;
    }

    public int getIdCreador() {
        return idCreador;
    }

    public void setIdCreador(int idCreador) {
        this.idCreador = idCreador;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Integer> getAmigosIds() {
        return amigosIds;
    }

    public void setAmigosIds(List<Integer> amigosIds) {
        this.amigosIds = amigosIds;
    }
}