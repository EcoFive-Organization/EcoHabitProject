package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "Metodo_Pago")
public class Metodo_Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_metodo_pago;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "detalles", nullable = false, length = 100)
    private String detalles;

    @Column(name = "activo", nullable = true, length = 150)
    private boolean activo;

    @Column(name = "fecha_registro", nullable = true)
    private Timestamp fecha_registro;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Metodo_Pago(int id_metodo_pago, String tipo, String detalles, boolean activo, Timestamp fecha_registro, Usuario usuario) {
        this.id_metodo_pago = id_metodo_pago;
        this.tipo = tipo;
        this.detalles = detalles;
        this.activo = activo;
        this.fecha_registro = fecha_registro;
        this.usuario = usuario;
    }

    public Metodo_Pago() {

    }

    public int getId_metodo_pago() {
        return id_metodo_pago;
    }

    public void setId_metodo_pago(int id_metodo_pago) {
        this.id_metodo_pago = id_metodo_pago;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Timestamp getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Timestamp fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
