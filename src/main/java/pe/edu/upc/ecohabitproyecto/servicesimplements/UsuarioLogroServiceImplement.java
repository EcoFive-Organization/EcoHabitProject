package pe.edu.upc.ecohabitproyecto.servicesimplements;

import pe.edu.upc.ecohabitproyecto.entities.UsuarioLogro;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IUsuarioLogroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.repositories.IUsuarioLogroRepository;
import java.util.List;

@Service
public class UsuarioLogroServiceImplement implements IUsuarioLogroService {

    @Autowired
    private IUsuarioLogroRepository repo;

    @Override
    public List<UsuarioLogro> list() {
        return repo.findAll();
    }

    @Override
    public void insert(UsuarioLogro usuarioLogro) {
        repo.save(usuarioLogro);
    }

    @Override
    public UsuarioLogro listId(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        repo.deleteById(id);
    }

    @Override
    public void update(UsuarioLogro usuarioLogro) {
        repo.save(usuarioLogro);
    }
}