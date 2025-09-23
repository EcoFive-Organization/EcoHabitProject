package pe.edu.upc.ecohabitproyecto.dtos;

import pe.edu.upc.ecohabitproyecto.entities.Consumo;

import java.sql.Timestamp;

public class CantidadAlarmasDTO {
    private int idAlerta;
    private Consumo consumo;
    private String mensaje;
    private Timestamp fecha;

}
