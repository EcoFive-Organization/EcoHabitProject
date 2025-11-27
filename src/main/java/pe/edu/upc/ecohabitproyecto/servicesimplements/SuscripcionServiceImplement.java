package pe.edu.upc.ecohabitproyecto.servicesimplements;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.dtos.SuscripcionDTO;
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
    @Transactional
    public void insert(SuscripcionDTO dto) {
        Suscripcion suscripcion = new Suscripcion();

        // Validación ID PayPal
        if (dto.getPaypalSuscripcionId() == null || dto.getPaypalSuscripcionId().isEmpty()) {
            throw new RuntimeException("El ID de suscripción de PayPal es nulo o vacío");
        }
        suscripcion.setPaypalSuscripcionId(dto.getPaypalSuscripcionId());

        // Fechas
        suscripcion.setFechaInicio(dto.getFechaInicio());
        suscripcion.setFechaFin(dto.getFechaFin());
        suscripcion.setEstado("ACTIVA");

        // 🔴 CORRECCIÓN: Usamos dto.getIdUsuario() directamente
        Usuario usuario = uR.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getIdUsuario()));
        suscripcion.setUsuario(usuario);

        // 🔴 CORRECCIÓN: Usamos dto.getIdPlanSuscripcion() directamente
        PlanSuscripcion plan = psR.findById(dto.getIdPlanSuscripcion())
                .orElseThrow(() -> new RuntimeException("Plan no encontrado con ID: " + dto.getIdPlanSuscripcion()));
        suscripcion.setPlanSuscripcion(plan);

        // Guardar
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
        pago.setFecha(LocalDate.now());
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

    @Override
    @Transactional
    public Suscripcion cancelarSuscripcion(Integer idUsuario) {
        // 1. Buscar el usuario y su suscripción
        Suscripcion suscripcion = sR
                .findByUsuario(uR.findById(idUsuario)
                        .orElseThrow(() -> new RuntimeException("404: Usuario no encontrado.")))
                .orElseThrow(() -> new RuntimeException("404: Suscripción no encontrada para el usuario."));

        // 2. Validar el estado actual
        if (!"ACTIVA".equals(suscripcion.getEstado())) {
            // Se usa equals y no == para comparar Strings
            throw new RuntimeException("400: La suscripción no está activa y no puede ser cancelada.");
        }

        // 3. Aplicar la lógica de cancelación
        suscripcion.setEstado("CANCELADA");
        // Nota: Solo actualizamos la fecha de fin a hoy. La fecha original de expiración es irrelevante al cancelar.
        suscripcion.setFechaFin(LocalDate.now());

        // 4. Guardar los cambios
        return sR.save(suscripcion);
    }

    @Override
    public boolean verificarSuscripcionActiva(Integer idUsuario) {
        Usuario u = new Usuario();
        u.setIdUsuario(idUsuario); // Solo necesitamos el ID para la consulta
        return sR.existsByUsuarioAndEstado(u, "ACTIVA");
    }
}

