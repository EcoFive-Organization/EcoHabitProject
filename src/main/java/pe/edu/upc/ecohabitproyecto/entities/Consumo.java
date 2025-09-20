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
    @JoinColumn(name = "id_Dispositivo", nullable = false)
    private Dispositivo dispositivo;

    @Column(name = "tipo", nullable = false, length = 20)
    private String tipo;

    @Column(name = "valor", nullable = false, precision = 12, scale = 2)
    private BigDecimal valor;

    @Column(name = "unidad", nullable = false, length = 20)
    private String unidad;

    @Column(name = "origen_consumo", nullable = false, length = 100)
    private String origen_consumo;

    @Column(name = "fecha", nullable = false)
    private Timestamp fecha;

    @Column(name = "umbral", nullable = false)
    private int umbral;

    public Consumo(int id_consumo, Dispositivo dispositivo, String tipo, BigDecimal valor, String unidad, String origen_consumo, Timestamp fecha, int umbral) {
        this.id_consumo = id_consumo;
        this.dispositivo = dispositivo;
        this.tipo = tipo;
        this.valor = valor;
        this.unidad = unidad;
        this.origen_consumo = origen_consumo;
        this.fecha = fecha;
        this.umbral = umbral;
    }

    public Consumo() {}

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

    public String getOrigen_consumo() {
        return origen_consumo;
    }

    public void setOrigen_consumo(String origen_consumo) {
        this.origen_consumo = origen_consumo;
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
