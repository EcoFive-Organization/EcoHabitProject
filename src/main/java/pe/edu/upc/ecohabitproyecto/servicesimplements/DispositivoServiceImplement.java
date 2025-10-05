package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Dispositivo;
import pe.edu.upc.ecohabitproyecto.repositories.IDispositivoRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IDispositivoService;

import java.util.List;

@Service
public class DispositivoServiceImplement implements IDispositivoService {

    @Autowired
    private IDispositivoRepository dR;

    @Override
    public List<Dispositivo> list() {
        return dR.findAll();
    }

    @Override
    public void insert(Dispositivo dispositivo) {
        dR.save(dispositivo);
    }

    @Override
    public Dispositivo listId(int id) {
        return dR.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        dR.deleteById(id);
    }

    @Override
    public void update(Dispositivo dispositivo) { dR.save(dispositivo); }

    @Override
    public List<String[]> getByTipo(String tipo_parametro) {
        return dR.getByTipo(tipo_parametro);
    }
}
