package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Publicacion;

import java.util.List;

public interface IPublicacionService {

    public List<Publicacion> list();

    public void insert(Publicacion publicacion);

    public Publicacion listId(int id);

    public void delete(int id);

    public void update(Publicacion publicacion);

    // Cantidad de Reacciones por Titulo de Publicacion
    public List<String[]> getCantidadReacciones();

}
