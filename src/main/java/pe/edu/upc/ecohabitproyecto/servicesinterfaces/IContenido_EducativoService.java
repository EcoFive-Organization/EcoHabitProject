package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Contenido_Educativo;

import java.util.List;

public interface IContenido_EducativoService {

    public List<Contenido_Educativo>list();

    public void insert(Contenido_Educativo contenido_educativo);

}
