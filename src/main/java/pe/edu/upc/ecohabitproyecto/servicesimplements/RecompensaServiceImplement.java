package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Billetera;
import pe.edu.upc.ecohabitproyecto.entities.Recompensa;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;
import pe.edu.upc.ecohabitproyecto.entities.UsuarioRecompensa;
import pe.edu.upc.ecohabitproyecto.repositories.IBilleteraRepository;
import pe.edu.upc.ecohabitproyecto.repositories.IRecompensaRepository;
import pe.edu.upc.ecohabitproyecto.repositories.IUsuarioRecompensaRepository;
import pe.edu.upc.ecohabitproyecto.repositories.IUsuarioRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IRecompensaService;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
public class RecompensaServiceImplement implements IRecompensaService {

    @Autowired
    private IRecompensaRepository recompensaRepo;

    @Autowired
    private IUsuarioRepository usuarioRepo;

    @Autowired
    private IUsuarioRecompensaRepository usuarioRecompensaRepo;

    @Autowired
    private IBilleteraRepository billeteraRepo;

    // ---------------- CRUD ----------------
    @Override
    public List<Recompensa> list() {
        return recompensaRepo.findAll();
    }

    @Override
    public void insert(Recompensa recompensa) {
        recompensaRepo.save(recompensa);
    }

    @Override
    public Recompensa listId(int id) {
        return recompensaRepo.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        recompensaRepo.deleteById(id);
    }

    @Override
    public void update(Recompensa recompensa) {
        recompensaRepo.save(recompensa);
    }

    // ---------------- HU17: Otorgar recompensa ----------------
    @Override
    public void otorgarRecompensa(int idUsuario, int idRecompensa) {
        Usuario usuario = usuarioRepo.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Recompensa recompensa = recompensaRepo.findById(idRecompensa)
                .orElseThrow(() -> new RuntimeException("Recompensa no encontrada"));

        // Guardar en tabla usuario_recompensa
        UsuarioRecompensa ur = new UsuarioRecompensa();
        ur.setUsuario(usuario);
        ur.setRecompensa(recompensa);
        ur.setFecha(new Timestamp(System.currentTimeMillis())); // ✅ ahora correcto
        usuarioRecompensaRepo.save(ur);

        // Actualizar billetera con los puntos de la recompensa
        Billetera billetera = billeteraRepo.findByUsuarioIdUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("Billetera no encontrada"));
        billetera.setSaldo(
                billetera.getSaldo().add(BigDecimal.valueOf(recompensa.getCostoPuntos()))
        );
        billeteraRepo.save(billetera);
    }

    // ---------------- HU17: Listar recompensas de un usuario ----------------
    @Override
    public List<Recompensa> listarRecompensasUsuario(int idUsuario) {
        return usuarioRecompensaRepo.findByUsuario_IdUsuario(idUsuario)
                .stream()
                .map(UsuarioRecompensa::getRecompensa)
                .toList();
    }
}