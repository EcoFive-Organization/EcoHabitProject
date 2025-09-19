package pe.edu.upc.ecohabitproyecto.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "Alerta")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAlerta;

    @ManyToOne
    @JoinColumn(name = "idConsumo")
    private Consumo consumo;

    @Column(name = "tipoAlerta", nullable = false, length = 50)
    private String tipoAlerta;

    @Lob
    @Column(name = "mensaje", nullable = false)
    private String mensaje;

    @Column(name = "fecha", nullable = false)
    private Timestamp fecha;

    @Column(name = "leida", nullable = false)
    private boolean leida;

    public Alerta() {}

    public Alerta(int idAlerta, Consumo consumo, String tipoAlerta, String mensaje, Timestamp fecha, boolean leida) {
        this.idAlerta = idAlerta;
        this.consumo = consumo;
        this.tipoAlerta = tipoAlerta;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.leida = leida;
    }

    public int getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(int idAlerta) {
        this.idAlerta = idAlerta;
    }

    public Consumo getConsumo() {
        return consumo;
    }

    public void setConsumo(Consumo consumo) {
        this.consumo = consumo;
    }

    public String getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(String tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
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
