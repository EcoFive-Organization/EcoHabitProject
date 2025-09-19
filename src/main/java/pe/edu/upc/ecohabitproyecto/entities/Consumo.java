package pe.edu.upc.ecohabitproyecto.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name="Consumo")
public class Consumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idConsumo;

    @ManyToOne
    @JoinColumn(name = "idDispositivo")
    private Dispositivo dispositivo;

    @Column(name = "tipo", nullable = false, length = 20)
    private String tipo;

    @Column(name = "valor", nullable = false, precision = 12, scale = 2)
    private BigDecimal valor;

    @Column(name = "unidad", nullable = true, length = 20)
    private String unidad;

    @Column(name = "origenConsumo", nullable = true, length = 100)
    private String origenConsumo;

    @Column(name = "fecha", nullable = false)
    private Timestamp fecha;

    @Column(name = "umbral", nullable = false)
    private int umbral;

    public Consumo(int idConsumo, Dispositivo dispositivo, String tipo, BigDecimal valor, String unidad, String origenConsumo, Timestamp fecha, int umbral) {
        this.idConsumo = idConsumo;
        this.dispositivo = dispositivo;
        this.tipo = tipo;
        this.valor = valor;
        this.unidad = unidad;
        this.origenConsumo = origenConsumo;
        this.fecha = fecha;
        this.umbral = umbral;
    }

    public Consumo() {}

    public int getIdConsumo() {
        return idConsumo;
    }

    public void setIdConsumo(int idConsumo) {
        this.idConsumo = idConsumo;
    }

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getOrigenConsumo() {
        return origenConsumo;
    }

    public void setOrigenConsumo(String origenConsumo) {
        this.origenConsumo = origenConsumo;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public int getUmbral() {
        return umbral;
    }

    public void setUmbral(int umbral) {
        this.umbral = umbral;
    }
}
