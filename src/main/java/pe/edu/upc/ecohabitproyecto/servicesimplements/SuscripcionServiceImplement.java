package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Suscripcion;
import pe.edu.upc.ecohabitproyecto.repositories.ISuscripcionRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.ISuscripcionService;

import java.util.List;

@Service
public class SuscripcionServiceImplement implements ISuscripcionService {
    @Autowired //Notificar que va a usar dos metodos de los muchos que tiene
    private ISuscripcionRepository sR;

    @Override
    public List<Suscripcion> list() {
        return sR.findAll();
    }

    @Override
    public void insert(Suscripcion suscripcion) {
        sR.save(suscripcion);
    }
}
