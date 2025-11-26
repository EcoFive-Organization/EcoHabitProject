package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
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
import java.time.LocalDateTime; // ✅ Usamos solo LocalDateTime
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
        return tR.findAll();
    }

    @Override
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

        // 1. Validaciones básicas
        Usuario usuario = usuarioRepo.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Billetera billetera = billeteraRepo.findByUsuarioIdUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("Billetera no encontrada"));

        BigDecimal puntosRequeridos = BigDecimal.valueOf(dto.getPuntosACanjear());

        if (billetera.getSaldo().compareTo(puntosRequeridos) < 0) {
            throw new RuntimeException("Saldo insuficiente para canjear");
        }

        // 2. Lógica de Conversión (Ejemplo: 1000 Puntos = 1 USD)
        // Esto evita división por cero
        if (dto.getPuntosACanjear() < 1000) {
            throw new RuntimeException("El monto mínimo de canje es 1000 puntos");
        }
        int dineroReal = dto.getPuntosACanjear() / 1000;

        // 3. Descontar puntos de la billetera
        billetera.setSaldo(billetera.getSaldo().subtract(puntosRequeridos));
        billeteraRepo.save(billetera);

        // 4. Registrar la transacción COMPLETA
        Transaccion transaccion = new Transaccion();
        transaccion.setBilletera(billetera);
        transaccion.setTipo("SALIDA"); // O "CANJE"

        // ✅ Usamos LocalDateTime directo (Más limpio)
        transaccion.setFecha(LocalDateTime.now());

        transaccion.setMontoPuntos(puntosRequeridos);

        // ✅ Campos NUEVOS que faltaban y son obligatorios en BD:
        transaccion.setMontoDineroReal(dineroReal);
        transaccion.setEmailDestino(dto.getEmailPaypal());

        // Aquí deberías poner el ID que te devuelve PayPal.
        // Como aún no conectas la API real en este método, generamos uno falso temporalmente:
        transaccion.setReferenciaPaypal(UUID.randomUUID().toString());

        tR.save(transaccion);
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