package pe.edu.upc.ecohabitproyecto.dtos;

import jakarta.persistence.*;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;

import java.math.BigDecimal;

public class BilleteraDTO {
    // Traer atributos de la carpeta entities
    private int billetera_id;
    private Usuario usuario;
    private BigDecimal saldo;

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
