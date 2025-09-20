package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Foro;
import pe.edu.upc.ecohabitproyecto.repositories.IForoRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IForoService;

import java.util.List;

@Service
public class ForoServiceImplement implements IForoService {
    @Autowired
    private IForoRepository iForoRepository;

    @Override
    public List<Foro> list(){
        return iForoRepository.findAll();
    }

    @Override
    public void insert(Foro foro) {
        iForoRepository.save(foro);
    }

    @Override
    public Foro listId(Integer id) {
        return iForoRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        iForoRepository.deleteById(id);
    }

    @Override
    public void update(Foro foro) {
        iForoRepository.save(foro);
    }

    @Override
    public List<String[]> quantityPostByForum() {
        return iForoRepository.postQuantityByForum();
    }
}
