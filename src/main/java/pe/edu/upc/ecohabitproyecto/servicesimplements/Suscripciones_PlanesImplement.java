package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Suscripciones_Planes;
import pe.edu.upc.ecohabitproyecto.repositories.ISuscripciones_PlanesRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.ISuscripciones_PlanesService;

import java.util.List;

@Service // importante añadir
public class Suscripciones_PlanesImplement implements ISuscripciones_PlanesService {

    @Autowired
    private ISuscripciones_PlanesRepository suscripciones_PlanesRepository;

    @Override
    public List<Suscripciones_Planes> list(){
        // Listar
        return suscripciones_PlanesRepository.findAll();
    }

    @Override
    public void insert(Suscripciones_Planes suscripciones_Planes){
        // Registrar
        suscripciones_PlanesRepository.save(suscripciones_Planes);
    }
}
