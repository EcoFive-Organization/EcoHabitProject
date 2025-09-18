package pe.edu.upc.ecohabitproyecto.servicesimplements;
import pe.edu.upc.ecohabitproyecto.entities.Participacion_Desafio;
import pe.edu.upc.ecohabitproyecto.repositories.IParticipacion_DesafioRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IParticipacion_DesafioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Participacion_DesafioServiceImplement implements IParticipacion_DesafioService {

    @Autowired
    private IParticipacion_DesafioRepository repo;

    @Override
    public List<Participacion_Desafio> list() {
        return repo.findAll();
    }

    @Override
    public void insert(Participacion_Desafio participacionDesafio) {
        repo.save(participacionDesafio);
    }

    @Override
    public Participacion_Desafio listId(long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void delete(long id) {
        repo.deleteById(id);
    }

    @Override
    public void update(Participacion_Desafio participacionDesafio) {
        repo.save(participacionDesafio);
    }
}
