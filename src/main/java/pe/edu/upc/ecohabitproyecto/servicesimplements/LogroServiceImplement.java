package pe.edu.upc.ecohabitproyecto.servicesimplements;

import pe.edu.upc.ecohabitproyecto.entities.Logro;
import pe.edu.upc.ecohabitproyecto.repositories.ILogroRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.ILogroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogroServiceImplement implements ILogroService {

    @Autowired
    private ILogroRepository repo;

    @Override
    public List<Logro> list() {
        return repo.findAll();
    }

    @Override
    public void insert(Logro logro) {
        repo.save(logro);
    }

    @Override
    public Logro listId(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        repo.deleteById(id);
    }

    @Override
    public void update(Logro logro) {
        repo.save(logro);
    }
}