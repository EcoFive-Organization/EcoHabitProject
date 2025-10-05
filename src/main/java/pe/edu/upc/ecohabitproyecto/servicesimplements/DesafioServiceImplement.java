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
        // 1. Validar que el desafío exista
        Desafio desafio = repo.findById(desafioId)
                .orElseThrow(() -> new RuntimeException("Desafío no encontrado"));

        // 2. Validar que el usuario exista
        Usuario usuario = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 3. Validar que no esté ya inscrito
        if (partRepo.existsByUsuario_IdUsuarioAndDesafio_IdDesafio(usuarioId, desafioId)) {
            throw new RuntimeException("El usuario ya está inscrito en este desafío");
        }

        // 4. Crear la participación
        ParticipacionDesafio participacion = new ParticipacionDesafio();
        participacion.setUsuario(usuario);
        participacion.setDesafio(desafio);
        participacion.setEstado("INICIADO");
        participacion.setProgreso(BigDecimal.ZERO);
        participacion.setFecha(LocalDateTime.now());

        // 5. Guardar la participación
        partRepo.save(participacion);
    }

    // 🔹 HU52: Crear desafío con amigos
    @Override
    public void crearDesafioAmigo(DesafioAmigoDTO dto) {
        // 1. Validar que el creador exista
        Usuario creador = usuarioRepo.findById(dto.getIdCreador())
                .orElseThrow(() -> new RuntimeException("Usuario creador no encontrado"));

        // 2. Crear el desafío con amigos
        DesafioAmigo desafioAmigo = new DesafioAmigo();
        desafioAmigo.setCreador(creador);
        desafioAmigo.setMeta(dto.getMeta());
        desafioAmigo.setFechaCreacion(LocalDateTime.now());
        desafioAmigo.setEstado("ACTIVO");

        // 3. Asociar amigos invitados
        Set<Usuario> amigos = new HashSet<>();
        for (Integer amigoId : dto.getAmigosIds()) {
            Usuario amigo = usuarioRepo.findById(amigoId)
                    .orElseThrow(() -> new RuntimeException("Amigo con ID " + amigoId + " no encontrado"));
            amigos.add(amigo);
        }
        desafioAmigo.setAmigosInvitados(amigos);

        // 4. Guardar el desafío
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
}