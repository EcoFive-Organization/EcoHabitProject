package pe.edu.upc.ecohabitproyecto.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name="Consumo")
public class Consumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_consumo;

    @ManyToOne
    @JoinColumn(name = "idDispositivo", nullable = false)
    private Dispositivo dispositivo;

    @Column(name = "tipo", nullable = false, length = 20)
    private String tipo;

    @Column(name = "valor", nullable = false, precision = 12, scale = 2)
    private BigDecimal valor;

    @Column(name = "unidad", nullable = false, length = 20)
    private String unidad;

    @Column(name = "origenConsumo", nullable = false, length = 100)
    private String origenConsumo;

    @Column(name = "fecha", nullable = false)
    private Timestamp fecha;

    @Column(name = "umbral", nullable = false)
    private int umbral;

    public Consumo() {}

    public Consumo(int id_consumo, Dispositivo dispositivo, String tipo, BigDecimal valor, String unidad, String origenConsumo, Timestamp fecha, int umbral) {
        this.id_consumo = id_consumo;
        this.dispositivo = dispositivo;
        this.tipo = tipo;
        this.valor = valor;
        this.unidad = unidad;
        this.origenConsumo = origenConsumo;
        this.fecha = fecha;
        this.umbral = umbral;
    }

    public int getId_consumo() {
        return id_consumo;
    }

    public void setId_consumo(int id_consumo) {
        this.id_consumo = id_consumo;
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
