package pe.edu.upc.ecohabitproyecto.servicesimplements;

import pe.edu.upc.ecohabitproyecto.entities.Usuario_Logro;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IUsuario_LogroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.repositories.IUsuario_LogroRepository;
import java.util.List;

@Service
public class Usuario_LogroServiceImplement implements IUsuario_LogroService {

    @Autowired
    private IUsuario_LogroRepository repo;

    @Override
    public List<Usuario_Logro> list() {
        return repo.findAll();
    }

    @Override
    public void insert(Usuario_Logro usuarioLogro) {
        repo.save(usuarioLogro);
    }

    @Override
    public Usuario_Logro listId(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        repo.deleteById(id);
    }

    @Override
    public void update(Usuario_Logro usuarioLogro) {
        repo.save(usuarioLogro);
    }
}