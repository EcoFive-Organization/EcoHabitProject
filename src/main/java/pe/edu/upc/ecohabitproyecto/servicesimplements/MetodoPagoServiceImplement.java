package pe.edu.upc.ecohabitproyecto.servicesimplements;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.MetodoPago;
import pe.edu.upc.ecohabitproyecto.repositories.IMetodoPagoRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IMetodoPagoService;

import java.time.LocalDate;
import java.util.List;

@Service
public class MetodoPagoServiceImplement implements IMetodoPagoService {
    @Autowired //Notificar que va a usar dos metodos de los muchos que tiene
    private IMetodoPagoRepository mR;

    @Override
    public List<MetodoPago> list() {
        return mR.findAll();
    }

    @Override
    @Transactional
    public void insert(MetodoPago metodoPago) {
        // Forzamos el ID a 0 (o null si usas Integer) para que Hibernate sepa que es un INSERT, no un UPDATE.
        metodoPago.setIdMetodoPago(null);

        // Asegurar estado activo por defecto
        if (metodoPago.getActivo() == null) {
            metodoPago.setActivo(true);
        }

        mR.save(metodoPago);
    }
}
