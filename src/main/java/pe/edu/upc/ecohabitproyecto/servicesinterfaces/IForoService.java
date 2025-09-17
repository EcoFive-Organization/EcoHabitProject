package pe.edu.upc.ecohabitproyecto.servicesinterfaces;


import pe.edu.upc.ecohabitproyecto.entities.Foro;

import java.util.List;

public interface IForoService {

    public List<Foro> list();

    public void insert(Foro foro);

    public Foro listId(Integer id);

    public void delete(int id);

    public void update(Foro foro);

}
