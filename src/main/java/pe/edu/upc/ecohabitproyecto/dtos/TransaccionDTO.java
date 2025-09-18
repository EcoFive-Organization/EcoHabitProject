package pe.edu.upc.ecohabitproyecto.dtos;

import jakarta.persistence.*;
import pe.edu.upc.ecohabitproyecto.entities.Billetera;

import java.math.BigDecimal;
import java.time.Instant;

public class TransaccionDTO {
    private int id_transaccion;
    private Billetera billetera;
    private String tipo;
    private BigDecimal monto;
    private Instant fecha;

    public int getId_transaccion() {
        return id_transaccion;
    }

    public void setId_transaccion(int id_transaccion) {
        this.id_transaccion = id_transaccion;
    }

    public Billetera getBilletera() {
        return billetera;
    }

    public void setBilletera(Billetera billetera) {
        this.billetera = billetera;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Instant getFecha() {
        return fecha;
    }

    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }
}
