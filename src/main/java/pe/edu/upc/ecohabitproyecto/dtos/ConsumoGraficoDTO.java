package pe.edu.upc.ecohabitproyecto.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ConsumoGraficoDTO {
    private LocalDate fecha;
    private String tipo; // Agua, Electricidad, Gas
    private BigDecimal total;

    // Constructores, Getters y Setters
    public ConsumoGraficoDTO(LocalDate fecha, String tipo, BigDecimal total) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.total = total;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
