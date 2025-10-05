package pe.edu.upc.ecohabitproyecto.dtos;

import pe.edu.upc.ecohabitproyecto.entities.Dispositivo;

import java.math.BigDecimal;

public class CantConsumoDispDTO {
    private Integer idDispositivo; // Nuevo
    private Integer idUsuario;     // Nuevo
    private String nombreDispositivo;
    private BigDecimal totalConsumo;

    public Integer getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(Integer idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    // --- Getters y Setters existentes ---
    public String getNombreDispositivo() {
        return nombreDispositivo;
    }

    public void setNombreDispositivo(String nombreDispositivo) {
        this.nombreDispositivo = nombreDispositivo;
    }

    public BigDecimal getTotalConsumo() {
        return totalConsumo;
    }

    public void setTotalConsumo(BigDecimal totalConsumo) {
        this.totalConsumo = totalConsumo;
    }
}
