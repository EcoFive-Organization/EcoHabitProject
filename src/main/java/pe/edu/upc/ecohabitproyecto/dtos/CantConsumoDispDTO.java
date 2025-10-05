package pe.edu.upc.ecohabitproyecto.dtos;

import pe.edu.upc.ecohabitproyecto.entities.Dispositivo;

import java.math.BigDecimal;

public class CantConsumoDispDTO {
    private int id_consumo;
    private Integer dispositivo;
    private BigDecimal valor;

    public int getId_consumo() {
        return id_consumo;
    }

    public void setId_consumo(int id_consumo) {
        this.id_consumo = id_consumo;
    }

    public Integer getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(Integer dispositivo) {
        this.dispositivo = dispositivo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
