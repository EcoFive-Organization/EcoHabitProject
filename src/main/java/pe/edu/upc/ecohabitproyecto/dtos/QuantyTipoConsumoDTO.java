package pe.edu.upc.ecohabitproyecto.dtos;

import java.math.BigDecimal;

public class QuantyTipoConsumoDTO {
    private String tipo;
    private BigDecimal quantityValor;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getQuantityValor() {
        return quantityValor;
    }

    public void setQuantityValor(BigDecimal quantityValor) {
        this.quantityValor = quantityValor;
    }
}
