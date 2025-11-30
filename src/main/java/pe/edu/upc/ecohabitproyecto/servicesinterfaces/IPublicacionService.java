package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.dtos.HistorialPublicacionDTO;
import pe.edu.upc.ecohabitproyecto.entities.Publicacion;

import java.util.List;

public interface IPublicacionService {

    public List<Publicacion> list();

    // Registrar
    public void crearPublicacion(Publicacion publicacion);

    // Listar por id
    public Publicacion listId(int id);

    // Eliminar
    public void eliminarPublicacion(int id);

    // Modificar
    public void modificarPublicacion(Publicacion publicacion);

    // Cantidad de Reacciones por Titulo de Publicacion
    public List<String[]> getCantidadReacciones(int idUsuario);

    // Buscar por Solo Amigos
    public List<String[]> soloAmigosPublicacion();

    // Buscar por ID (Historial)
    public List<String[]> buscarID(int nUsuario);

    List<Publicacion> listByNombreUsuario(String nombre);


}
