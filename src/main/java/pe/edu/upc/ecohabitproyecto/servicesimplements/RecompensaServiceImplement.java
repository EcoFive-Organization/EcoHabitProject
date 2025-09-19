package pe.edu.upc.ecohabitproyecto.servicesimplements;

import pe.edu.upc.ecohabitproyecto.entities.Recompensa;
import pe.edu.upc.ecohabitproyecto.repositories.IRecompensaRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IRecompensaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecompensaServiceImplement implements IRecompensaService {

    @Autowired
    private IRecompensaRepository repo;

    @Override
    public List<Recompensa> list() {
        return repo.findAll();
    }

    @Override
    public void insert(Recompensa recompensa) {
        repo.save(recompensa);
    }

    @Override
    public Recompensa listId(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        repo.deleteById(id);
    }

    @Override
    public void update(Recompensa recompensa) {
        repo.save(recompensa);
    }
}