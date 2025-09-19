package pe.edu.upc.ecohabitproyecto.dtos;

import pe.edu.upc.ecohabitproyecto.entities.Usuario;

import java.math.BigDecimal;

public class BilleteraDTO {
    // Traer atributos de la carpeta entities
    private int billetera_Id;
    private Usuario usuario;
    private BigDecimal saldo;

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
