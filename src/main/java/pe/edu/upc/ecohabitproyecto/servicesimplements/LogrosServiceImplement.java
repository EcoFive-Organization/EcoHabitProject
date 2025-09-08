package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Logros;
import pe.edu.upc.ecohabitproyecto.repositories.ILogrosRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.ILogrosService;

import java.util.List;
@Service
public class LogrosServiceImplement implements ILogrosService {
    @Autowired
    private ILogrosRepository lR;
    @Override
    public List<Logros> list() {
        return lR.findAll();
    }

    @Override
    public void insert(Logros software) {
        lR.save(software);
    }
}
