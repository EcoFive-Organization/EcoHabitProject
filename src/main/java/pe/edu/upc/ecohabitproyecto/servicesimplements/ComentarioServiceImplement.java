package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Comentario;
import pe.edu.upc.ecohabitproyecto.repositories.IComentarioRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IComentarioService;

import java.util.List;

@Service
public class ComentarioServiceImplement implements IComentarioService {
    @Autowired
    private IComentarioRepository comentarioRepository;

    @Override
    public List<Comentario> list(){
        return comentarioRepository.findAll();
    }

    @Override
    public void insert(Comentario comentario){
        comentarioRepository.save(comentario);
    }

    @Override
    public Comentario listId(int id) {
        return comentarioRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        comentarioRepository.deleteById(id);
    }

    @Override
    public void update(Comentario comentario) {
        comentarioRepository.save(comentario);
    }
}
