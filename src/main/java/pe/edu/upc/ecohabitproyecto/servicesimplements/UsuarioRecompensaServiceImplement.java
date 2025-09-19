package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.UsuarioRecompensa;
import pe.edu.upc.ecohabitproyecto.repositories.IUsuarioRecompensaRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IUsuarioRecompensaService;

import java.util.List;

@Service
public class UsuarioRecompensaServiceImplement implements IUsuarioRecompensaService {
    @Autowired //Notificar que va a usar dos metodos de los muchos que tiene
    private IUsuarioRecompensaRepository uR;

    @Override
    public List<UsuarioRecompensa> list() {
        return uR.findAll();
    }

    @Override
    public void insert(UsuarioRecompensa usuario_recompensa) {
        uR.save(usuario_recompensa);
    }
}
