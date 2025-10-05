package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
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

    // 🔹 HU20: Canjear puntos
    @Override
    public void canjearPuntos(Integer idUsuario, CanjePuntosDTO dto) {
        // 1. Verificar usuario
        Usuario usuario = usuarioRepo.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Obtener billetera
        Billetera billetera = billeteraRepo.findByUsuarioIdUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("Billetera no encontrada"));

        // 3. Validar saldo suficiente
        if (billetera.getSaldo().compareTo(BigDecimal.valueOf(dto.getPuntosACanjear())) < 0) {
            throw new RuntimeException("Saldo insuficiente para canjear");
        }

        // 4. Descontar puntos
        billetera.setSaldo(billetera.getSaldo().subtract(BigDecimal.valueOf(dto.getPuntosACanjear())));
        billeteraRepo.save(billetera);

        // 5. Registrar transacción
        Transaccion transaccion = new Transaccion();
        transaccion.setBilletera(billetera);
        transaccion.setMonto(BigDecimal.valueOf(dto.getPuntosACanjear()));
        transaccion.setTipo("CANJE");
        transaccion.setFecha(Timestamp.valueOf(LocalDateTime.now()));
        tR.save(transaccion);
    }


    @Override
    public List<HistorialTransaccionesDTO> getHistorialTransacciones(Integer idUsuario) {
        // 1. Obtener billetera del usuario
        Billetera billetera = billeteraRepo.findByUsuarioIdUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("Billetera no encontrada"));

        // 2. Consultar transacciones de esa billetera
        List<Transaccion> transacciones = tR.findByBilletera(billetera);

        // 3. Mapear a DTO
        return transacciones.stream().map(tx -> {
            HistorialTransaccionesDTO dto = new HistorialTransaccionesDTO();
            dto.setFecha(tx.getFecha().toLocalDateTime()); // convertir Timestamp → LocalDateTime
            dto.setTipo(tx.getTipo());
            dto.setMonto(tx.getMonto());
            return dto;
        }).collect(Collectors.toList());
    }
}