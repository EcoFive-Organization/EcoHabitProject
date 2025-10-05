package pe.edu.upc.ecohabitproyecto.dtos;

import java.math.BigDecimal;

public class LogroDesbloqueadoDTO {
    private String mensaje;
    private String nombreLogro;
    private String recompensa;
    private BigDecimal nuevoSaldo;


    public LogroDesbloqueadoDTO(String mensaje, String nombreLogro, String recompensa, BigDecimal nuevoSaldo) {
        this.mensaje = mensaje;
        this.nombreLogro = nombreLogro;
        this.recompensa = recompensa;
        this.nuevoSaldo = nuevoSaldo;
    }


    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public String getNombreLogro() { return nombreLogro; }
    public void setNombreLogro(String nombreLogro) { this.nombreLogro = nombreLogro; }

    public String getRecompensa() { return recompensa; }
    public void setRecompensa(String recompensa) { this.recompensa = recompensa; }

    public BigDecimal getNuevoSaldo() { return nuevoSaldo; }
    public void setNuevoSaldo(BigDecimal nuevoSaldo) { this.nuevoSaldo = nuevoSaldo; }
}