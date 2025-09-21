package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.ContenidoEducativo;

import java.util.List;

public interface IContenidoEducativoService {

    // Listar
    public List<ContenidoEducativo>list();

    // Registrar
    public void registrarContenidoEducativo(ContenidoEducativo contenido_educativo);

    // Eliminar
    public void eliminarContenidoEducativo(int id);

    // Modificar
    public void modificarContenidoEducativo(ContenidoEducativo contenido_educativo);

    // Listar por id (importante para el delete)
    public ContenidoEducativo listId(int id);

    // Listar por tipo Lectura
    public List<String[]> getLecturasEducativas();

    // Listar por tipo Video
    public List<String[]> getVideosEducativos();

}
