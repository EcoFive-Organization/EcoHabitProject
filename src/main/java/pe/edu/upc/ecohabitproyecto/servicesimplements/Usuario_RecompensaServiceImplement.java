package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Usuario_Recompensa;
import pe.edu.upc.ecohabitproyecto.repositories.IUsuario_RecompensaRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IUsuario_RecompensaService;

import java.util.List;

@Service
public class Usuario_RecompensaServiceImplement implements IUsuario_RecompensaService {
    @Autowired //Notificar que va a usar dos metodos de los muchos que tiene
    private IUsuario_RecompensaRepository uR;

    @Override
    public List<Usuario_Recompensa> list() {
        return uR.findAll();
    }

    @Override
    public void insert(Usuario_Recompensa usuario_recompensa) {
        uR.save(usuario_recompensa);
    }
}
