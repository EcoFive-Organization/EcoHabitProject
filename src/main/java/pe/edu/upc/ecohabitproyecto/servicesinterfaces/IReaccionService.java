package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Comentario;
import pe.edu.upc.ecohabitproyecto.entities.Reaccion;

import java.util.List;

public interface IReaccionService {

    public List<Reaccion> list();

    public void insert(Reaccion reaccion);

    public Reaccion listId(int id);

    public void delete(int id);

    public void update(Reaccion reaccion);
}
