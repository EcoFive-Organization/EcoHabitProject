package pe.edu.upc.ecohabitproyecto.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import pe.edu.upc.ecohabitproyecto.entities.Metodo_Pago;
import pe.edu.upc.ecohabitproyecto.entities.Suscripcion;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Suscripcion_PagoDTO {
    private int id_suscripcion_pago;
    private BigDecimal monto;
    private Timestamp fecha;
    private String estado;
    private Suscripcion suscripcion;
    private Metodo_Pago metodo_pago;

    public int getId_suscripcion_pago() {
        return id_suscripcion_pago;
    }

    public void setId_suscripcion_pago(int id_suscripcion_pago) {
        this.id_suscripcion_pago = id_suscripcion_pago;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Suscripcion getSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(Suscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }

    public Metodo_Pago getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(Metodo_Pago metodo_pago) {
        this.metodo_pago = metodo_pago;
    }
}
