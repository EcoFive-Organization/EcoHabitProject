package pe.edu.upc.ecohabitproyecto.dtos;

import java.math.BigDecimal;

public class ImpactoEcologicoMensualDTO {
    private String mesAnio; // Ej: "2025-09"
    private BigDecimal impactoTotal;

    public String getMesAnio() {
        return mesAnio;
    }

    public void setMesAnio(String mesAnio) {
        this.mesAnio = mesAnio;
    }

    public BigDecimal getImpactoTotal() {
        return impactoTotal;
    }

    public void setImpactoTotal(BigDecimal impactoTotal) {
        this.impactoTotal = impactoTotal;
    }
}
