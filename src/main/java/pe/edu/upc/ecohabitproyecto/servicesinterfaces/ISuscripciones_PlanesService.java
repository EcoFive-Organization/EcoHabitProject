package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Suscripciones_Planes;

import java.util.List;

public interface ISuscripciones_PlanesService {
    // Listar
    public List<Suscripciones_Planes> list();

    // Registrar
    public void insert(Suscripciones_Planes suscripciones_planes);

}
