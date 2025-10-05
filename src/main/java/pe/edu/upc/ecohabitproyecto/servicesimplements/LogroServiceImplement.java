package pe.edu.upc.ecohabitproyecto.servicesimplements;

import pe.edu.upc.ecohabitproyecto.dtos.LogroDesbloqueadoDTO;
import pe.edu.upc.ecohabitproyecto.entities.Billetera;
import pe.edu.upc.ecohabitproyecto.entities.Logro;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;
import pe.edu.upc.ecohabitproyecto.entities.UsuarioLogro;
import pe.edu.upc.ecohabitproyecto.repositories.IBilleteraRepository;
import pe.edu.upc.ecohabitproyecto.repositories.ILogroRepository;
import pe.edu.upc.ecohabitproyecto.repositories.IUsuarioLogroRepository;
import pe.edu.upc.ecohabitproyecto.repositories.IUsuarioRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.ILogroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    private IBilleteraRepository billeteraRepo;

    @Override
    public List<Logro> list() {
        return logroRepo.findAll();
    }

    @Override
    public void insert(Logro logro) {
        logroRepo.save(logro);
    }

    @Override
    public Logro listId(int id) {
        return logroRepo.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        logroRepo.deleteById(id);
    }

    @Override
    public void update(Logro logro) {
        logroRepo.save(logro);
    }


    @Override
    public LogroDesbloqueadoDTO desbloquearLogro(int idUsuario, int idLogro) {
        // 1. Verificar usuario
        Usuario usuario = usuarioRepo.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Buscar logro
        Logro logro = logroRepo.findById(idLogro)
                .orElseThrow(() -> new RuntimeException("Logro no encontrado"));

        // 3. Verificar si ya está desbloqueado
        if (userLogroRepo.existsByUsuario_IdUsuarioAndLogro_IdLogro(idUsuario, idLogro)) {
            return new LogroDesbloqueadoDTO(
                    "El usuario ya tiene este logro desbloqueado",
                    logro.getNombre(),
                    logro.getRecompensa() != null ? logro.getRecompensa().getNombre() : "N/A",
                    billeteraRepo.findByUsuarioIdUsuario(idUsuario)
                            .map(Billetera::getSaldo)
                            .orElse(BigDecimal.ZERO)
            );
        }

        // 4. Crear relación UsuarioLogro
        UsuarioLogro ul = new UsuarioLogro();
        ul.setUsuario(usuario);
        ul.setLogro(logro);
        ul.setFechaDesbloqueo(LocalDateTime.now());
        userLogroRepo.save(ul);

        // 5. Actualizar billetera sumando puntos del logro
        Billetera billetera = billeteraRepo.findByUsuarioIdUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("Billetera no encontrada"));
        billetera.setSaldo(billetera.getSaldo().add(BigDecimal.valueOf(logro.getPuntos())));
        billeteraRepo.save(billetera);

        // 6. Retornar DTO con info completa
        return new LogroDesbloqueadoDTO(
                "¡Logro desbloqueado!",
                logro.getNombre(),
                logro.getRecompensa() != null ? logro.getRecompensa().getNombre() : "N/A",
                billetera.getSaldo()
        );
    }
}