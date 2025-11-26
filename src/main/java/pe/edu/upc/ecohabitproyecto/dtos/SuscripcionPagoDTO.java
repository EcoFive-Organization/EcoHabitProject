package pe.edu.upc.ecohabitproyecto.dtos;

import pe.edu.upc.ecohabitproyecto.entities.MetodoPago;
import pe.edu.upc.ecohabitproyecto.entities.Suscripcion;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SuscripcionPagoDTO {
    private int idSuscripcionPago;
    private BigDecimal monto;
    private LocalDateTime fecha;
    private String estado;
    private String referenciaExterna;
    private Suscripcion suscripcion;
    private MetodoPago metodoPago;

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

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getReferenciaExterna() {
        return referenciaExterna;
    }

    public void setReferenciaExterna(String referenciaExterna) {
        this.referenciaExterna = referenciaExterna;
    }

    public Suscripcion getSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(Suscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }
}
