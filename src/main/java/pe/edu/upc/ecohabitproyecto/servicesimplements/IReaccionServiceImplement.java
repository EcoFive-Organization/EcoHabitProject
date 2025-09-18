package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Reaccion;
import pe.edu.upc.ecohabitproyecto.repositories.IReaccionRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IReaccionService;

import java.util.List;

@Service
public class IReaccionServiceImplement implements IReaccionService {
    @Autowired
    private IReaccionRepository reaccionRepository;

    @Override
    public List<Reaccion> list(){
        return reaccionRepository.findAll();
    }

    @Override
    public void insert(Reaccion reaccion) {
        reaccionRepository.save(reaccion);
    }

    @Override
    public Reaccion listId(int id) {
        return reaccionRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        reaccionRepository.deleteById(id);
    }

    @Override
    public void update(Reaccion reaccion) {
        reaccionRepository.save(reaccion);
    }
}
