package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.ContenidoEducativo;

import java.util.List;

public interface IContenidoEducativoService {

    // Listar
    public List<ContenidoEducativo>list();

    // Registrar
    public void insert(ContenidoEducativo contenido_educativo);

    // Eliminar
    public void delete(int id);

    // Modificar
    public void update(ContenidoEducativo contenido_educativo);

    // Listar por id (importante para el delete)
    public ContenidoEducativo listId(int id);

}
