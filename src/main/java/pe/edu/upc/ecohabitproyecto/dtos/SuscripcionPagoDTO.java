package pe.edu.upc.ecohabitproyecto.dtos;

import pe.edu.upc.ecohabitproyecto.entities.MetodoPago;
import pe.edu.upc.ecohabitproyecto.entities.Suscripcion;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class SuscripcionPagoDTO {
    private int idSuscripcionPago;
    private BigDecimal monto;
    private Timestamp fecha;
    private String estado;
    private Suscripcion suscripcion;
    private MetodoPago metodo_pago;

    public int getIdSuscripcionPago() {
        return idSuscripcionPago;
    }

    public void setIdSuscripcionPago(int idSuscripcionPago) {
        this.idSuscripcionPago = idSuscripcionPago;
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

    public MetodoPago getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(MetodoPago metodo_pago) {
        this.metodo_pago = metodo_pago;
    }
}
