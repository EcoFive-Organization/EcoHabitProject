package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Comentario;

import java.util.List;

public interface IComentarioService {
    public List<Comentario> list();

    // Registrar
    public void crearComentario(Comentario comentario);

    public Comentario listId(int id);

    // Eliminar
    public void eliminarComentario(int id);

    // Modificar
    public void modificarComentario(Comentario comentario);

}
