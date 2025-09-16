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

    @Column(name = "balance_puntos", nullable = false, precision = 12, scale = 2)
    private BigDecimal balance_puntos;

    public Billetera(int billetera_id, Usuario usuario, BigDecimal balance_puntos) {
        this.billetera_id = billetera_id;
        this.usuario = usuario;
        this.balance_puntos = balance_puntos;
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

    public BigDecimal getBalance_puntos() {
        return balance_puntos;
    }

    public void setBalance_puntos(BigDecimal balance_puntos) {
        this.balance_puntos = balance_puntos;
    }
}
