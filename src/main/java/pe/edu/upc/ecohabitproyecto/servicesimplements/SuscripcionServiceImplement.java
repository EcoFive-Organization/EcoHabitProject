package pe.edu.upc.ecohabitproyecto.servicesimplements;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.PlanSuscripcion;
import pe.edu.upc.ecohabitproyecto.entities.Suscripcion;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;
import pe.edu.upc.ecohabitproyecto.repositories.IPlanSuscripcionRepository;
import pe.edu.upc.ecohabitproyecto.repositories.ISuscripcionRepository;
import pe.edu.upc.ecohabitproyecto.repositories.IUsuarioRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.ISuscripcionService;

import java.util.List;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class SuscripcionServiceImplement implements ISuscripcionService {
    @Autowired //Notificar que va a usar dos metodos de los muchos que tiene
    private ISuscripcionRepository sR;

    @Autowired
    private IPlanSuscripcionRepository psR;

    @Autowired
    private IUsuarioRepository uR;

    @Override
    public List<Suscripcion> list() {
        return sR.findAll();
    }

    @Override
    public void insert(Suscripcion suscripcion) {
        sR.save(suscripcion);
    }

    @Override
    @Transactional
    public Suscripcion seleccionarPlan(Integer idUsuario, Integer idPlan) {

        // 1. Obtener entidades (Validación 404)
        Usuario usuario = uR.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("404: Usuario no encontrado."));
        PlanSuscripcion plan = psR.findById(idPlan)
                .orElseThrow(() -> new RuntimeException("404: Plan de suscripción no encontrado."));

        // 2. BUSCAR la suscripción existente por la entidad Usuario
        Optional<Suscripcion> suscripcionExistente = sR.findByUsuario(usuario);

        Suscripcion suscripcion;

        if (suscripcionExistente.isPresent()) {
            // Caso 1: ACTUALIZACIÓN
            suscripcion = suscripcionExistente.get();
        } else {
            // Caso 2: INSERCIÓN
            suscripcion = new Suscripcion();
            suscripcion.setUsuario(usuario); // Asignación de FK
            suscripcion.setFechaInicio(LocalDate.now());
        }

        // 3. Asignar/Actualizar campos comunes
        suscripcion.setPlanSuscripcion(plan);
        suscripcion.setEstado("ACTIVO");
        suscripcion.setFechaFin(LocalDate.now().plusMonths(1));

        // 4. GUARDAR: Spring Data JPA hace el INSERT o UPDATE automáticamente
        return sR.save(suscripcion);
    }
}

