package pe.edu.upc.ecohabitproyecto.servicesimplements;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Alerta;
import pe.edu.upc.ecohabitproyecto.repositories.IAlertaRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IAlertaService;

import java.util.List;

@Service
public class AlertaServiceImplement implements IAlertaService {

    @Autowired
    private IAlertaRepository aS;

    @Override
    public List<Alerta> list() { return aS.findAll(); }

    @Override
    public void insert(Alerta alerta) { aS.save(alerta); }
}
