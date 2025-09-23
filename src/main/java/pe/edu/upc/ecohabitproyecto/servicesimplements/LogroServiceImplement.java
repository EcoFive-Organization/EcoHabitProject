package pe.edu.upc.ecohabitproyecto.servicesimplements;

import pe.edu.upc.ecohabitproyecto.entities.Logro;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;
import pe.edu.upc.ecohabitproyecto.entities.UsuarioLogro;
import pe.edu.upc.ecohabitproyecto.repositories.ILogroRepository;
import pe.edu.upc.ecohabitproyecto.repositories.IUsuarioLogroRepository;
import pe.edu.upc.ecohabitproyecto.repositories.IUsuarioRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.ILogroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogroServiceImplement implements ILogroService {
    @Autowired
    private ILogroRepository logroRepo;

    @Autowired
    private IUsuarioLogroRepository userLogroRepo;

    @Autowired
    private IUsuarioRepository usuarioRepo;

    @Autowired
    private ILogroRepository repo;

    @Override
    public List<Logro> list() {
        return repo.findAll();
    }

    @Override
    public void desbloquearLogro(Integer idUsuario, String nombreLogro) {
        // 1. Verificar usuario
        Usuario usuario = usuarioRepo.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Buscar logro
        Logro logro = logroRepo.findByNombre(nombreLogro)
                .orElseThrow(() -> new RuntimeException("Logro no existe"));

        // 3. Verificar si ya desbloqueado
        if (userLogroRepo.existsByUsuario_IdUsuarioAndIdLogro(idUsuario, logro.getIdLogro())) {
            throw new RuntimeException("Logro ya desbloqueado");
        }

        // 4. Crear relación UsuarioLogro
        UsuarioLogro ul = new UsuarioLogro();
        ul.setUsuario(usuario);
        ul.setIdLogro(logro.getIdLogro());
        ul.setFechaObtenido(LocalDateTime.now());
        userLogroRepo.save(ul);
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