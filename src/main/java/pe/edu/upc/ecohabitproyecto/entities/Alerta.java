package pe.edu.upc.ecohabitproyecto.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "Alerta")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_alerta;

    @ManyToOne
    @JoinColumn(name = "id_Consumo")
    private Consumo consumo;

    @Column(name = "tipo_alerta", nullable = false, length = 50)
    private String tipo_alerta;

    @Lob
    @Column(name = "mensaje", nullable = false)
    private String mensaje;

    @Column(name = "fecha", nullable = false)
    private Timestamp fecha;

    @Column(name = "leida", nullable = false)
    private boolean leida;

    public Alerta() {}

    public Alerta(int id_alerta, Consumo consumo, String tipo_alerta, String mensaje, Timestamp fecha, boolean leida) {
        this.id_alerta = id_alerta;
        this.consumo = consumo;
        this.tipo_alerta = tipo_alerta;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.leida = leida;
    }

    public int getId_alerta() {
        return id_alerta;
    }

    public void setId_alerta(int id_alerta) {
        this.id_alerta = id_alerta;
    }

    public Consumo getConsumo() {
        return consumo;
    }

    public void setConsumo(Consumo consumo) {
        this.consumo = consumo;
    }

    public String getTipo_alerta() {
        return tipo_alerta;
    }

    public void setTipo_alerta(String tipo_alerta) {
        this.tipo_alerta = tipo_alerta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public boolean isLeida() {
        return leida;
    }

    public void setLeida(boolean leida) {
        this.leida = leida;
    }
}
