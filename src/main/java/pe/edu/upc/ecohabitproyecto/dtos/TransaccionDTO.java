package pe.edu.upc.ecohabitproyecto.dtos;

import pe.edu.upc.ecohabitproyecto.entities.Billetera;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransaccionDTO {
    private int idTransaccion;
    private Billetera billetera;
    private String tipo;
    private BigDecimal montoPuntos;
    private LocalDateTime fecha;
    private int montoDineroReal;
    private String emailDestino;
    private String referenciaPaypal;

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
