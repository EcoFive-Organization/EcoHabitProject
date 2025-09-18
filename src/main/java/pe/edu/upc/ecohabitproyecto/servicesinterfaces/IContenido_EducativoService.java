package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Contenido_Educativo;

import java.util.List;

public interface IContenido_EducativoService {

    // Listar
    public List<Contenido_Educativo>list();

    // Registrar
    public void insert(Contenido_Educativo contenido_educativo);

    // Eliminar
    public void delete(int id);

    // Modificar
    public void update(Contenido_Educativo contenido_educativo);

    // Listar por id (importante para el delete)
    public Contenido_Educativo listId(int id);

}
