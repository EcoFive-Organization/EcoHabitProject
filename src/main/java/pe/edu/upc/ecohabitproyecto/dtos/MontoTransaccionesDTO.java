package pe.edu.upc.ecohabitproyecto.dtos;

import java.math.BigDecimal;

public class MontoTransaccionesDTO {
    private String tipo;
    private BigDecimal totalmonto;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getTotalmonto() {
        return totalmonto;
    }

    public void setTotalmonto(BigDecimal totalmonto) {
        this.totalmonto = totalmonto;
    }
}
