package pe.edu.upc.ecohabitproyecto.servicesimplements;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.*;
import pe.edu.upc.ecohabitproyecto.repositories.*;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.ISuscripcionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class SuscripcionServiceImplement implements ISuscripcionService {
    @Autowired //Notificar que va a usar dos metodos de los muchos que tiene
    private ISuscripcionRepository sR;

    @Autowired
    private IPlanSuscripcionRepository psR;

    @Autowired
    private IUsuarioRepository uR;

    @Autowired
    private IMetodoPagoRepository mpR;

    @Autowired
    private ISuscripcionPagoRepository spR;

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

    @Override
    @Transactional
    public SuscripcionPago procesarPago(Integer idUsuario, Integer idMetodoPago) {

        // 1. Obtener Entidades y Validar
        Suscripcion suscripcion = sR
                .findByUsuario(uR.findById(idUsuario)
                        .orElseThrow(() -> new RuntimeException("404: Usuario no encontrado.")))
                .orElseThrow(() -> new RuntimeException("404: Suscripción pendiente de plan no encontrada. Por favor, seleccione un plan primero."));

        MetodoPago metodoPago = mpR.findById(idMetodoPago)
                .orElseThrow(() -> new RuntimeException("404: Método de pago no encontrado."));

        // 2. Determinar Monto (Usando su campo 'costo')
        double montoPlan = suscripcion.getPlanSuscripcion().getCosto();

        // 3. ⚠️ SIMULACIÓN DE GATEWAY DE PAGO (Lógica Corregida) ⚠️
        String estadoTransaccionDB = "FALLIDO";

        // ✅ Uso seguro de Boolean.TRUE.equals() para manejar valores nulos/false
        if (Boolean.TRUE.equals(metodoPago.getActivo())) {
            estadoTransaccionDB = "EXITOSO";
        } else {
            throw new RuntimeException("El método de pago es inactivo o inválido para procesar la transacción.");
        }

        // 4. Registrar la Transacción (SuscripcionPago)
        SuscripcionPago pago = new SuscripcionPago();
        pago.setSuscripcion(suscripcion);
        pago.setMonto(BigDecimal.valueOf(montoPlan));
        pago.setFecha(LocalDateTime.now());
        pago.setEstado(estadoTransaccionDB);
        pago.setMetodoPago(metodoPago); // ⬅️ Usando el setter correcto

        SuscripcionPago pagoGuardado = spR.save(pago);

        // 5. Actualizar el estado de la Suscripción (Solo si fue exitoso)
        if (estadoTransaccionDB.equals("EXITOSO")) {
            suscripcion.setEstado("ACTIVA");
            sR.save(suscripcion);
        }

        return pagoGuardado;
    }
}

