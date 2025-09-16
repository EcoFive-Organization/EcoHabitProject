package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Publicacion;

import java.util.List;

public interface IPublicacionService {

    public List<Publicacion> list();

    public void insert(Publicacion publicacion);
}
