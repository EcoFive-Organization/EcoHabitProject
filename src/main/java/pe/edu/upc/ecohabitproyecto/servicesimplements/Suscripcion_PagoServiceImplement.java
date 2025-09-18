package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Suscripcion_Pago;
import pe.edu.upc.ecohabitproyecto.repositories.ISuscripcion_PagoRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.ISuscripcionService;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.ISuscripcion_PagoService;

import java.util.List;

@Service
public class Suscripcion_PagoServiceImplement implements ISuscripcion_PagoService {
    @Autowired //Notificar que va a usar dos metodos de los muchos que tiene
    private ISuscripcion_PagoRepository spR;

    @Override
    public List<Suscripcion_Pago> list() {
        return spR.findAll();
    }

    @Override
    public void insert(Suscripcion_Pago suscripcion_pago) {
        spR.save(suscripcion_pago);
    }
}
