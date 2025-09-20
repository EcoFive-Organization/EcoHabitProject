package pe.edu.upc.ecohabitproyecto.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "Transaccion")
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTransaccion;

    @ManyToOne
    @JoinColumn(name = "idBilletera", nullable = false)
    @JoinColumn(name = "idBilletera")
    private Billetera billetera;

    @Column(name = "tipo", nullable = false, length = 20)
    private String tipo;

    @Column(name = "monto", nullable = false, precision = 12, scale = 2)
    private BigDecimal monto;

    @Column(name = "fecha", nullable = false)
    private Timestamp fecha;

    public Transaccion() {}

    public Transaccion(int idTransaccion, Billetera billetera, String tipo, BigDecimal monto, Timestamp fecha) {
        this.idTransaccion = idTransaccion;
        this.billetera = billetera;
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = fecha;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
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

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
}
