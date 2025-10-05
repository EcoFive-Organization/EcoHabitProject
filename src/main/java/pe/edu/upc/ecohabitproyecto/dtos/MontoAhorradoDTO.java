package pe.edu.upc.ecohabitproyecto.dtos;

import java.math.BigDecimal;

public class MontoAhorradoDTO {
    private String tipoConsumo;
    private BigDecimal montoAhorrado;

    public String getTipoConsumo() {
        return tipoConsumo;
    }

    public void setTipoConsumo(String tipoConsumo) {
        this.tipoConsumo = tipoConsumo;
    }

    public BigDecimal getMontoAhorrado() {
        return montoAhorrado;
    }

    public void setMontoAhorrado(BigDecimal montoAhorrado) {
        this.montoAhorrado = montoAhorrado;
    }
}