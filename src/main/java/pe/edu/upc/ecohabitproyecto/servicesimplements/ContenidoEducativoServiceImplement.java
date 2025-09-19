package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.ContenidoEducativo;
import pe.edu.upc.ecohabitproyecto.repositories.IContenidoEducativoRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IContenidoEducativoService;

import java.util.List;

@Service
public class ContenidoEducativoServiceImplement implements IContenidoEducativoService {
    @Autowired
    private IContenidoEducativoRepository contenido_educativoRepository;

    // Listar
    @Override
    public List<ContenidoEducativo> list() {
        return contenido_educativoRepository.findAll();
    }

    // Registrar
    @Override
    public void insert(ContenidoEducativo contenido_educativo) {
        contenido_educativoRepository.save(contenido_educativo);
    }

    // Eliminar
    @Override
    public void delete(int id) {
        contenido_educativoRepository.deleteById(id);
    }

    // Modificar
    @Override
    public void update(ContenidoEducativo contenido_educativo) {
        contenido_educativoRepository.save(contenido_educativo);
    }

    // Listar por id
    @Override
    public ContenidoEducativo listId(int id) {
        return contenido_educativoRepository.findById(id).orElse(null);
    }
}
