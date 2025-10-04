package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.PlanSuscripcion;
import pe.edu.upc.ecohabitproyecto.repositories.IPlanSuscripcionRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IPlanSuscripcionService;

import java.util.List;

@Service
public class PlanSuscripcionServiceImplement implements IPlanSuscripcionService {
    @Autowired
    private IPlanSuscripcionRepository psR;

    @Override
    public List<PlanSuscripcion> list() {
        return psR.findAll();
    }
}
