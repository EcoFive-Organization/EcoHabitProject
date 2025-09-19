package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.SuscripcionPago;
import pe.edu.upc.ecohabitproyecto.repositories.ISuscripcionPagoRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.ISuscripcionPagoService;

import java.util.List;

@Service
public class SuscripcionPagoServiceImplement implements ISuscripcionPagoService {
    @Autowired //Notificar que va a usar dos metodos de los muchos que tiene
    private ISuscripcionPagoRepository spR;

    @Override
    public List<SuscripcionPago> list() {
        return spR.findAll();
    }

    @Override
    public void insert(SuscripcionPago suscripcion_pago) {
        spR.save(suscripcion_pago);
    }
}
