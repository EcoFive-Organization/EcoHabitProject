package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_comentario;

    @Column(name = "contenido", nullable = false, length = 255)
    private String contenido;

    @Column(name = "fecha",  nullable = false)
    private Date fecha;

}
