package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.dtos.LogroDTO;
import pe.edu.upc.ecohabitproyecto.entities.Logro;
import pe.edu.upc.ecohabitproyecto.entities.Recompensa;
import pe.edu.upc.ecohabitproyecto.repositories.ILogroRepository;
import pe.edu.upc.ecohabitproyecto.repositories.IRecompensaRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IAdminService;

@Service
public class AdminServiceImplement implements IAdminService {

    @Autowired
    private ILogroRepository logroRepo;

    @Autowired
    private IRecompensaRepository recompensaRepo;

    @Override
    public Logro obtenerLogroPorId(Integer idLogro) {
        System.out.println("ID recibido en servicio (GET): " + idLogro);
        return logroRepo.findById(idLogro)
                .orElseThrow(() -> new RuntimeException("Logro no encontrado"));
    }

    @Override
    public Logro registrarLogro(LogroDTO dto) {
        Logro logro = new Logro();

        logro.setNombre(dto.getNombre());
        logro.setDescripcion(dto.getDescripcion());
        logro.setPuntos(dto.getPuntos());
        logro.setEstado(dto.getEstado() != null ? dto.getEstado() : "ACTIVO");

        if (dto.getIdRecompensa() != null) {
            Recompensa recompensa = recompensaRepo.findById(dto.getIdRecompensa())
                    .orElseThrow(() -> new RuntimeException("Recompensa no encontrada"));
            logro.setRecompensa(recompensa);
        }

        return logroRepo.save(logro);
    }

    @Override
    public Logro modificarLogro(Integer idLogro, LogroDTO dto) {
        System.out.println("ID recibido en servicio (PUT): " + idLogro);

        Logro logroExistente = logroRepo.findById(idLogro)
                .orElseThrow(() -> new RuntimeException("Logro no encontrado"));

        logroExistente.setNombre(dto.getNombre());
        logroExistente.setDescripcion(dto.getDescripcion());
        logroExistente.setPuntos(dto.getPuntos());
        logroExistente.setEstado(dto.getEstado());

        if (dto.getIdRecompensa() != null) {
            Recompensa recompensa = recompensaRepo.findById(dto.getIdRecompensa())
                    .orElseThrow(() -> new RuntimeException("Recompensa no encontrada"));
            logroExistente.setRecompensa(recompensa);
        }

        return logroRepo.save(logroExistente);
    }

    @Override
    public void eliminarLogro(Integer idLogro) {
        System.out.println("ID recibido en servicio (DELETE): " + idLogro);

        Logro logro = logroRepo.findById(idLogro)
                .orElseThrow(() -> new RuntimeException("Logro no encontrado"));

        logroRepo.delete(logro);
    }
}