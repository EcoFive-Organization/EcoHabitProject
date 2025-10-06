package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.dtos.DesafioAmigoDTO;
import pe.edu.upc.ecohabitproyecto.entities.Desafio;
import pe.edu.upc.ecohabitproyecto.entities.DesafioAmigo;
import pe.edu.upc.ecohabitproyecto.entities.ParticipacionDesafio;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;
import pe.edu.upc.ecohabitproyecto.repositories.IDesafioAmigoRepository;
import pe.edu.upc.ecohabitproyecto.repositories.IDesafioRepository;
import pe.edu.upc.ecohabitproyecto.repositories.IParticipacionDesafioRepository;
import pe.edu.upc.ecohabitproyecto.repositories.IUsuarioRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IDesafioService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DesafioServiceImplement implements IDesafioService {

    @Autowired
    private IDesafioRepository repo;

    @Autowired
    private IParticipacionDesafioRepository partRepo;

    @Autowired
    private IUsuarioRepository usuarioRepo;

    @Autowired
    private IDesafioAmigoRepository desafioAmigoRepo;

    @Override
    public List<Desafio> list() {
        return repo.findAll();
    }

    // 🔹 HU27: Unirse a un desafío comunitario
    @Override
    public void unirseADesafioComunitario(Integer usuarioId, Integer desafioId) {
        Desafio desafio = repo.findById(desafioId)
                .orElseThrow(() -> new RuntimeException("Desafío no encontrado"));

        Usuario usuario = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (partRepo.existsByUsuario_IdUsuarioAndDesafio_IdDesafio(usuarioId, desafioId)) {
            throw new RuntimeException("El usuario ya está inscrito en este desafío");
        }

        ParticipacionDesafio participacion = new ParticipacionDesafio();
        participacion.setUsuario(usuario);
        participacion.setDesafio(desafio);
        participacion.setEstado("INICIADO");
        participacion.setProgreso(BigDecimal.ZERO);
        participacion.setFecha(LocalDateTime.now());

        partRepo.save(participacion);
    }

    // 🔹 HU52: Crear desafío con amigos
    @Override
    public void crearDesafioAmigo(DesafioAmigoDTO dto) {
        Usuario creador = usuarioRepo.findById(dto.getIdCreador())
                .orElseThrow(() -> new RuntimeException("Usuario creador no encontrado"));

        DesafioAmigo desafioAmigo = new DesafioAmigo();
        desafioAmigo.setCreador(creador);
        desafioAmigo.setMeta(dto.getMeta());
        desafioAmigo.setFechaCreacion(LocalDateTime.now());
        desafioAmigo.setEstado("ACTIVO");

        Set<Usuario> amigos = new HashSet<>();
        for (Integer amigoId : dto.getAmigosIds()) {
            Usuario amigo = usuarioRepo.findById(amigoId)
                    .orElseThrow(() -> new RuntimeException("Amigo con ID " + amigoId + " no encontrado"));
            amigos.add(amigo);
        }
        desafioAmigo.setAmigosInvitados(amigos);

        desafioAmigoRepo.save(desafioAmigo);
    }

    @Override
    public void insert(Desafio desafio) {
        repo.save(desafio);
    }

    @Override
    public Desafio listId(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        repo.deleteById(id);
    }

    @Override
    public void update(Desafio desafio) {
        repo.save(desafio);
    }

    // 🔹 HU53: Eliminar desafío con amigos
    @Override
    public void eliminarDesafioAmigo(Integer idDesafioAmigo, Integer idCreador) {
        DesafioAmigo desafioAmigo = desafioAmigoRepo.findById(idDesafioAmigo)
                .orElseThrow(() -> new RuntimeException("Desafío no encontrado"));

        if (!desafioAmigo.getCreador().getIdUsuario().equals(idCreador)) {
            throw new RuntimeException("Solo el creador puede eliminar este desafío");
        }

        if (!"ACTIVO".equalsIgnoreCase(desafioAmigo.getEstado())) {
            throw new RuntimeException("El desafío ya no está activo y no puede eliminarse");
        }

        desafioAmigo.setEstado("CANCELADO");
        desafioAmigoRepo.save(desafioAmigo);
    }

    // 🔹 HU54: Unirse a un desafío con amigos
    @Override
    public void unirseADesafioAmigo(Integer idUsuario, Integer idDesafioAmigo) {
        DesafioAmigo desafioAmigo = desafioAmigoRepo.findById(idDesafioAmigo)
                .orElseThrow(() -> new RuntimeException("Desafío de amigos no encontrado"));

        Usuario usuario = usuarioRepo.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Validar que el usuario esté invitado
        if (!desafioAmigo.getAmigosInvitados().contains(usuario)) {
            throw new RuntimeException("El usuario no fue invitado a este desafío");
        }

        // Validar que no esté ya participando
        if (partRepo.existsByUsuario_IdUsuarioAndDesafioAmigo_IdDesafioAmigo(idUsuario, idDesafioAmigo)) {
            throw new RuntimeException("El usuario ya participa en este desafío");
        }

        ParticipacionDesafio participacion = new ParticipacionDesafio();
        participacion.setUsuario(usuario);
        participacion.setDesafioAmigo(desafioAmigo);
        participacion.setEstado("EN PROGRESO");
        participacion.setProgreso(BigDecimal.ZERO);
        participacion.setFecha(LocalDateTime.now());

        partRepo.save(participacion);
    }
}