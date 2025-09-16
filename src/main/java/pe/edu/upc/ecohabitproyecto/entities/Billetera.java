package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Billetera")
public class Billetera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billetera_id;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @Column(name = "saldo", nullable = false, precision = 12, scale = 2)
    private BigDecimal saldo;

    public Billetera(int billetera_id, Usuario usuario, BigDecimal saldo) {
        this.billetera_id = billetera_id;
        this.usuario = usuario;
        this.saldo = saldo;
    }

    public Billetera() {}

    public int getBilletera_id() {
        return billetera_id;
    }

    public void setBilletera_id(int billetera_id) {
        this.billetera_id = billetera_id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getSaldo() { return saldo; }

    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }
}
