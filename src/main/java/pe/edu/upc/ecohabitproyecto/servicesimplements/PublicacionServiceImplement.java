package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.dtos.HistorialPublicacionDTO;
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
    public void crearPublicacion(Publicacion publicacion) {
        publicacionRepository.save(publicacion);
    }

    @Override
    public Publicacion listId(int id) {
        return publicacionRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarPublicacion(int id) {
        publicacionRepository.deleteById(id);
    }

    @Override
    public void modificarPublicacion(Publicacion publicacion) {
        publicacionRepository.save(publicacion);
    }

    @Override
    public List<String[]> getCantidadReacciones() {
        return publicacionRepository.getCantidadReacciones();
    }

    @Override
    public List<String[]> soloAmigosPublicacion() {
        return publicacionRepository.soloAmigosPublicacion();
    }

    @Override
    public List<String[]> buscarID(int nUsuario) {
        return publicacionRepository.buscarID(nUsuario);
    }


}
