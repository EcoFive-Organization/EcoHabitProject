package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario_billetera")
public class Usuario_billetera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billetera_id;

    @Column(name = "usuario_id",  nullable = false, length = 10)
    private int usuario_id;

    @Column(name = "balance_puntos", nullable = false)
    private int balance_puntos;

    public Usuario_billetera(int billetera_id, int usuario_id, int balance_puntos) {
        this.billetera_id = billetera_id;
        this.usuario_id = usuario_id;
        this.balance_puntos = balance_puntos;
    }

    public Usuario_billetera() {}

    public int getBilletera_id() {
        return billetera_id;
    }

    public void setBilletera_id(int billetera_id) {
        this.billetera_id = billetera_id;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getBalance_puntos() {
        return balance_puntos;
    }

    public void setBalance_puntos(int balance_puntos) {
        this.balance_puntos = balance_puntos;
    }
}
