package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Contenido_Educativo;
import pe.edu.upc.ecohabitproyecto.repositories.IContenido_EducativoRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IContenido_EducativoService;

import java.util.List;

@Service
public class Contenido_EducativoServiceImplement implements IContenido_EducativoService {
    @Autowired
    private IContenido_EducativoRepository contenido_educativoRepository;

    // Listar
    @Override
    public List<Contenido_Educativo> list() {
        return contenido_educativoRepository.findAll();
    }

    // Registrar
    @Override
    public void insert(Contenido_Educativo contenido_educativo) {
        contenido_educativoRepository.save(contenido_educativo);
    }

    // Eliminar
    @Override
    public void delete(int id) {
        contenido_educativoRepository.deleteById(id);
    }

    // Modificar
    @Override
    public void update(Contenido_Educativo contenido_educativo) {
        contenido_educativoRepository.save(contenido_educativo);
    }

    // Listar por id
    @Override
    public Contenido_Educativo listId(int id) {
        return contenido_educativoRepository.findById(id).orElse(null);
    }
}
