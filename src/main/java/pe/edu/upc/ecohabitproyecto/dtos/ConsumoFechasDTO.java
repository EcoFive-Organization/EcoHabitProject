package pe.edu.upc.ecohabitproyecto.dtos;

import java.math.BigDecimal;
import java.time.LocalTime;

public class ConsumoFechasDTO {
    private String tipo;
    private LocalTime fecha;
    private BigDecimal Totalvalor;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalTime fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotalvalor() {
        return Totalvalor;
    }

    public void setTotalvalor(BigDecimal totalvalor) {
        Totalvalor = totalvalor;
    }
}
