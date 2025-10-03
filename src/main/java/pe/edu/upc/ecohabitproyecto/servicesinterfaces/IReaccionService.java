package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Comentario;
import pe.edu.upc.ecohabitproyecto.entities.Reaccion;

import java.util.List;

public interface IReaccionService {

    public List<Reaccion> list();

    public void crearReaccion(Reaccion reaccion);

    public Reaccion listId(int id);

    public void eliminarReaccion(int id);

    public void modificarReaccion(Reaccion reaccion);
}
