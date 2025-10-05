package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.ParticipacionDesafio;
import pe.edu.upc.ecohabitproyecto.repositories.IParticipacionDesafioRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IParticipacionDesafioService;

import java.util.List;

@Service
public class ParticipacionDesafioServiceImplement implements IParticipacionDesafioService {

    @Autowired
    private IParticipacionDesafioRepository repo;

    @Override
    public List<ParticipacionDesafio> list() {
        return repo.findAll();
    }

    @Override
    public void insert(ParticipacionDesafio participacionDesafio) {
        repo.save(participacionDesafio);
    }

    @Override
    public ParticipacionDesafio listId(Integer id) { // 🔹 corregido
        return repo.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) { // 🔹 corregido
        repo.deleteById(id);
    }

    @Override
    public void update(ParticipacionDesafio participacionDesafio) {
        repo.save(participacionDesafio);
    }
}
