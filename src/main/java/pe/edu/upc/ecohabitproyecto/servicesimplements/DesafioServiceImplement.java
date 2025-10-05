package pe.edu.upc.ecohabitproyecto.servicesimplements;

import pe.edu.upc.ecohabitproyecto.entities.Desafio;
import pe.edu.upc.ecohabitproyecto.entities.ParticipacionDesafio;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;
import pe.edu.upc.ecohabitproyecto.repositories.IParticipacionDesafioRepository;
import pe.edu.upc.ecohabitproyecto.repositories.IUsuarioRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IDesafioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.repositories.IDesafioRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DesafioServiceImplement implements IDesafioService {

    @Autowired private IDesafioRepository repo;
    @Autowired private IParticipacionDesafioRepository partRepo;
    @Autowired private IUsuarioRepository usuarioRepo;


    @Override
    public List<Desafio> list() {
        return repo.findAll();
    }

    @Override
    public void unirseADesafio(Integer usuarioId, Integer desafioId) {
        Desafio d = repo.findById(desafioId)
                .orElseThrow(() -> new RuntimeException("Desafío no encontrado"));

        // 1. Validación de Inscripción
        if (partRepo.existsByUsuario_IdUsuarioAndDesafio_IdDesafio(usuarioId, desafioId)) {
            throw new RuntimeException("Ya inscrito al desafío");
        }

        Usuario u = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Creación e Inicialización de la Participación
        ParticipacionDesafio p = new ParticipacionDesafio();

        // Asignación de Foráneas
        p.setUsuario(u);
        p.setDesafio(d);

        // 3. 🚀 INICIALIZACIÓN DE CAMPOS (CORRECCIÓN)
        p.setEstado("INICIADO"); // Estado inicial
        p.setProgreso(BigDecimal.ZERO); // Progreso inicial a 0
        p.setFecha(LocalDateTime.now()); // Fecha actual de inscripción

        // 4. Guardar la Participación
        partRepo.save(p);
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