package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Social_Plataformas;
import pe.edu.upc.ecohabitproyecto.repositories.ISocial_PlataformasRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.ISocial_PlataformasService;

import java.util.List;

@Service
public class Social_PlataformasServiceImplement implements ISocial_PlataformasService {

    private final ISocial_PlataformasRepository repo;

    public Social_PlataformasServiceImplement(ISocial_PlataformasRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Social_Plataformas> list() { return repo.findAll(); }

    @Override
    public Social_Plataformas insert(Social_Plataformas e) {
        e.setPlataforma_id(null);                 // asegura INSERT
        return repo.save(e);                      // <-- devuelve guardado
    }
}