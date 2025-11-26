package pe.edu.upc.ecohabitproyecto.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "Transaccion")
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTransaccion;

    @ManyToOne
    @JoinColumn(name = "idBilletera", nullable = false)
    private Billetera billetera;

    @Column(name = "tipo", nullable = false, length = 20)
    private String tipo;

    @Column(name = "montoPuntos", nullable = false, precision = 12, scale = 2)
    private BigDecimal montoPuntos;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "montoDineroReal", nullable = false)
    private int montoDineroReal;

    @Column(name = "emailDestino", nullable = false, length = 100)
    private String emailDestino;

    @Column(name = "referenciaPaypal", nullable = false, length = 100)
    private String referenciaPaypal;

    public Transaccion() {}

    public Transaccion(int idTransaccion, Billetera billetera, String tipo, BigDecimal montoPuntos, LocalDateTime fecha, int montoDineroReal, String emailDestino, String referenciaPaypal) {
        this.idTransaccion = idTransaccion;
        this.billetera = billetera;
        this.tipo = tipo;
        this.montoPuntos = montoPuntos;
        this.fecha = fecha;
        this.montoDineroReal = montoDineroReal;
        this.emailDestino = emailDestino;
        this.referenciaPaypal = referenciaPaypal;
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

    public BigDecimal getMontoPuntos() {
        return montoPuntos;
    }

    public void setMontoPuntos(BigDecimal montoPuntos) {
        this.montoPuntos = montoPuntos;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public int getMontoDineroReal() {
        return montoDineroReal;
    }

    public void setMontoDineroReal(int montoDineroReal) {
        this.montoDineroReal = montoDineroReal;
    }

    public String getEmailDestino() {
        return emailDestino;
    }

    public void setEmailDestino(String emailDestino) {
        this.emailDestino = emailDestino;
    }

    public String getReferenciaPaypal() {
        return referenciaPaypal;
    }

    public void setReferenciaPaypal(String referenciaPaypal) {
        this.referenciaPaypal = referenciaPaypal;
    }
}
