package pe.edu.upc.ecohabitproyecto.dtos;

import java.util.Date;

public class ReaccionDTO {
    private int id_reaccion;
    private String tipo_reaccion;
    private Date fecha;

    public int getId_reaccion() {
        return id_reaccion;
    }

    public void setId_reaccion(int id_reaccion) {
        this.id_reaccion = id_reaccion;
    }

    public String getTipo_reaccion() {
        return tipo_reaccion;
    }

    public void setTipo_reaccion(String tipo_reaccion) {
        this.tipo_reaccion = tipo_reaccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
