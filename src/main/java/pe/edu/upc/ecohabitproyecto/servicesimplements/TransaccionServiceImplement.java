package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Importante para consistencia
import pe.edu.upc.ecohabitproyecto.dtos.CanjePuntosDTO;
import pe.edu.upc.ecohabitproyecto.dtos.HistorialTransaccionesDTO;
import pe.edu.upc.ecohabitproyecto.entities.Billetera;
import pe.edu.upc.ecohabitproyecto.entities.Transaccion;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;
import pe.edu.upc.ecohabitproyecto.repositories.IBilleteraRepository;
import pe.edu.upc.ecohabitproyecto.repositories.ITransaccionRepository;
import pe.edu.upc.ecohabitproyecto.repositories.IUsuarioRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.ITransaccionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID; // Para simular la referencia de PayPal si no tienes la API real aún
import java.util.stream.Collectors;

@Service
public class TransaccionServiceImplement implements ITransaccionService {

    @Autowired
    private ITransaccionRepository tR;

    @Autowired
    private IBilleteraRepository billeteraRepo;

    @Autowired
    private IUsuarioRepository usuarioRepo;

    @Override
    public List<Transaccion> list() {
        // 1. Obtener autenticación
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"));

        if (isAdmin) {
            // ADMIN: Ve todo el historial del sistema
            return tR.findAll();
        } else {
            // CLIENT: Solo ve SUS movimientos
            Usuario u = usuarioRepo.findByNombre(username).orElse(null);
            if (u == null) return Collections.emptyList();

            return tR.findByUsuarioId(u.getIdUsuario());
        }
    }

    @Override
    @Transactional
    public void insert(Transaccion transaccion) {
        // Asegurar que la fecha se ponga automática si viene nula
        if (transaccion.getFecha() == null) {
            transaccion.setFecha(LocalDateTime.now());
        }
        tR.save(transaccion);
    }

    @Override
    public Transaccion listId(int id) {
        return tR.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        tR.deleteById(id);
    }

    @Override
    public void update(Transaccion transaccion) {
        tR.save(transaccion);
    }

    @Override
    public List<Object[]> TransaccionesTotales() {
        return tR.TransaccionesTotales();
    }

    @Override
    public List<Object[]> TransaccionesMonto() {
        return tR.TransaccionesMonto();
    }

    // 🔹 HU20: Canjear puntos
    @Override
    @Transactional // ✅ Asegura que si falla el guardado, no se descuenten los puntos
    public void canjearPuntos(Integer idUsuario, CanjePuntosDTO dto) {
        // Validación de seguridad: ¿El que llama es el dueño de la cuenta?
        validarAccesoUsuario(idUsuario);

        Usuario usuario = usuarioRepo.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Billetera billetera = billeteraRepo.findByUsuarioIdUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("Billetera no encontrada"));

        BigDecimal puntosRequeridos = BigDecimal.valueOf(dto.getPuntosACanjear());

        if (billetera.getSaldo().compareTo(puntosRequeridos) < 0) {
            throw new RuntimeException("Saldo insuficiente para canjear");
        }

        if (dto.getPuntosACanjear() < 1000) {
            throw new RuntimeException("El monto mínimo de canje es 1000 puntos");
        }
        int dineroReal = dto.getPuntosACanjear() / 1000;

        // Descontar
        billetera.setSaldo(billetera.getSaldo().subtract(puntosRequeridos));
        billeteraRepo.save(billetera);

        // Registrar
        Transaccion transaccion = new Transaccion();
        transaccion.setBilletera(billetera);
        transaccion.setTipo("SALIDA");
        transaccion.setFecha(LocalDateTime.now());
        transaccion.setMontoPuntos(puntosRequeridos);
        transaccion.setMontoDineroReal(dineroReal);
        transaccion.setEmailDestino(dto.getEmailPaypal());
        transaccion.setReferenciaPaypal(UUID.randomUUID().toString()); // Simulación

        tR.save(transaccion);
    }

    // Método auxiliar de seguridad (Reutilizable)
    private void validarAccesoUsuario(Integer idUsuarioObjetivo) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"));

        if (!isAdmin) {
            Usuario u = usuarioRepo.findByNombre(username).orElseThrow();
            if (!u.getIdUsuario().equals(idUsuarioObjetivo)) {
                throw new RuntimeException("Acceso denegado: No puedes operar en la cuenta de otro usuario.");
            }
        }
    }

    @Override
    public List<HistorialTransaccionesDTO> getHistorialTransacciones(Integer idUsuario) {
        Billetera billetera = billeteraRepo.findByUsuarioIdUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("Billetera no encontrada"));

        List<Transaccion> transacciones = tR.findByBilletera(billetera);

        return transacciones.stream().map(tx -> {
            HistorialTransaccionesDTO dto = new HistorialTransaccionesDTO();

            // ✅ CORRECCIÓN: Como en la Entity ya es LocalDateTime, NO necesitas .toLocalDateTime()
            // dto.setFecha(tx.getFecha().toLocalDateTime()); <--- ESTO ERA ANTES
            dto.setFecha(tx.getFecha()); // <--- ESTO ES AHORA (Mucho más simple)

            dto.setTipo(tx.getTipo());
            dto.setMonto(tx.getMontoPuntos());
            return dto;
        }).collect(Collectors.toList());
    }
}