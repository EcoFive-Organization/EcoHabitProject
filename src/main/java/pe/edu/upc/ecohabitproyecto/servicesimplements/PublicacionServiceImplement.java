package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Publicacion;
import pe.edu.upc.ecohabitproyecto.repositories.IPublicacionRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IPublicacionService;

import java.util.List;

@Service
public class PublicacionServiceImplement implements IPublicacionService {

    @Autowired
    private IPublicacionRepository publicacionRepository;

    @Override
    public List<Publicacion> list(){
        return publicacionRepository.findAll();
    }

    @Override
    public void insert(Publicacion publicacion) {
        publicacionRepository.save(publicacion);
    }
}
