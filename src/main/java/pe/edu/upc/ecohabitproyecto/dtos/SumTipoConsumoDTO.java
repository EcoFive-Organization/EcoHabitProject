package pe.edu.upc.ecohabitproyecto.dtos;

import java.math.BigDecimal;

public class SumTipoConsumoDTO {
    private String tipo;
    private BigDecimal totalConsumo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getTotalConsumo() {
        return totalConsumo;
    }

    public void setTotalConsumo(BigDecimal totalConsumo) {
        this.totalConsumo = totalConsumo;
    }
}
