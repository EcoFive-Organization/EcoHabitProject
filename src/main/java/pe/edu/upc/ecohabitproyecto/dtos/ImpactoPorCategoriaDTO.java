package pe.edu.upc.ecohabitproyecto.dtos;

import java.math.BigDecimal;

public class ImpactoPorCategoriaDTO {
    private String categoria;
    private BigDecimal impactoTotal;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getImpactoTotal() {
        return impactoTotal;
    }

    public void setImpactoTotal(BigDecimal impactoTotal) {
        this.impactoTotal = impactoTotal;
    }
}