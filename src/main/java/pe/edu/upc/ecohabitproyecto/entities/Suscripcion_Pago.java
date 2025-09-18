package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;


@Entity
@Table(name = "Suscripcion_Pago")
public class Suscripcion_Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_suscripcion_pago;

    @Column(name = "monto", precision = 12, scale = 2)
    private BigDecimal monto;

    @Column(name = "fecha",nullable = true)
    private Timestamp fecha;

    @Column(name = "estado", nullable = true, length = 20)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_suscripcion")
    private Suscripcion suscripcion;

    @ManyToOne
    @JoinColumn(name = "id_metodo_pago")
    private Metodo_Pago metodo_pago;

    public Suscripcion_Pago(int id_suscripcion_pago, BigDecimal monto, Timestamp fecha, String estado, Suscripcion suscripcion, Metodo_Pago metodo_pago) {
        this.id_suscripcion_pago = id_suscripcion_pago;
        this.monto = monto;
        this.fecha = fecha;
        this.estado = estado;
        this.suscripcion = suscripcion;
        this.metodo_pago = metodo_pago;
    }

    public Suscripcion_Pago() {

    }

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
