package pe.edu.upc.ecohabitproyecto.dtos;

import pe.edu.upc.ecohabitproyecto.entities.Billetera;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class CantidadTransaccionesDTO {
    private String tipo;
    private BigDecimal montoTotal;
    private Long cantidadTransacciones;
    private Long cantidadBilleterasUnicas;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Long getCantidadTransacciones() {
        return cantidadTransacciones;
    }

    public void setCantidadTransacciones(Long cantidadTransacciones) {
        this.cantidadTransacciones = cantidadTransacciones;
    }

    public Long getCantidadBilleterasUnicas() {
        return cantidadBilleterasUnicas;
    }

    public void setCantidadBilleterasUnicas(Long cantidadBilleterasUnicas) {
        this.cantidadBilleterasUnicas = cantidadBilleterasUnicas;
    }
}
