package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Billetera")
public class Billetera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBilletera;

    @OneToOne
    @JoinColumn(name = "idUsuario", nullable = false, unique = true)
    private Usuario usuario;

    @Column(name = "saldo", nullable = false, precision = 12, scale = 2)
    private BigDecimal saldo;

    public Billetera() {}

    public Billetera(int idBilletera, Usuario usuario, BigDecimal saldo) {
        this.idBilletera = idBilletera;
        this.usuario = usuario;
        this.saldo = saldo;
    }

    public int getIdBilletera() {
        return idBilletera;
    }

    public void setIdBilletera(int idBilletera) {
        this.idBilletera = idBilletera;
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
