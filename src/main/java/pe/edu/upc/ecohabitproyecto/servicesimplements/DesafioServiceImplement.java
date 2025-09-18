package pe.edu.upc.ecohabitproyecto.servicesimplements;

import pe.edu.upc.ecohabitproyecto.entities.Desafio;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IDesafioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.repositories.IDesafioRepository;
import java.util.List;

@Service
public class DesafioServiceImplement implements IDesafioService {

    @Autowired
    private IDesafioRepository repo;

    @Override
    public List<Desafio> list() {
        return repo.findAll();
    }

    @Override
    public void insert(Desafio desafio) {
        repo.save(desafio);
    }

    @Override
    public Desafio listId(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        repo.deleteById(id);
    }

    @Override
    public void update(Desafio desafio) {
        repo.save(desafio);
    }
}