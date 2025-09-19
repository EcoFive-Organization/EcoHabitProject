package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Billetera")
public class Billetera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billetera_Id;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @Column(name = "saldo", nullable = false, precision = 12, scale = 2)
    private BigDecimal saldo;

    public Billetera() {}

    public Billetera(int billetera_Id, Usuario usuario, BigDecimal saldo) {
        this.billetera_Id = billetera_Id;
        this.usuario = usuario;
        this.saldo = saldo;
    }

    public int getBilletera_Id() {
        return billetera_Id;
    }

    public void setBilletera_Id(int billetera_Id) {
        this.billetera_Id = billetera_Id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
