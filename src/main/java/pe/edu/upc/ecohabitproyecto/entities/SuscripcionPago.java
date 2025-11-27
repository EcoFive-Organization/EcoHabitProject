package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "SuscripcionPago")
public class SuscripcionPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSuscripcionPago;

    @Column(name = "monto", precision = 12, scale = 2)
    private BigDecimal monto;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "referenciaExterna", nullable = false, length = 100)
    private String referenciaExterna;

    @ManyToOne
    @JoinColumn(name = "idSuscripcion", nullable = false)
    private Suscripcion suscripcion;

    @ManyToOne
    @JoinColumn(name = "idMetodoPago", nullable = false)
    private MetodoPago metodoPago;

    public SuscripcionPago() {

    }

    public SuscripcionPago(int idSuscripcionPago, BigDecimal monto, LocalDate fecha, String estado, String referenciaExterna, Suscripcion suscripcion, MetodoPago metodoPago) {
        this.idSuscripcionPago = idSuscripcionPago;
        this.monto = monto;
        this.fecha = fecha;
        this.estado = estado;
        this.referenciaExterna = referenciaExterna;
        this.suscripcion = suscripcion;
        this.metodoPago = metodoPago;
    }

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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
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
