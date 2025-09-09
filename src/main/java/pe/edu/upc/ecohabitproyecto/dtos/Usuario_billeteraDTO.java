package pe.edu.upc.ecohabitproyecto.dtos;
import jakarta.persistence.Column;

public class Usuario_billeteraDTO {
    // Traer atributos de la carpeta entities
    private int billetera_id;
    private int usuario_id;
    private int balance_puntos;

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
