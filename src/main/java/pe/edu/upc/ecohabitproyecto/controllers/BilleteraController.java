package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.BilleteraDTO;
import pe.edu.upc.ecohabitproyecto.dtos.CanjePuntosDTO;
import pe.edu.upc.ecohabitproyecto.dtos.HistorialTransaccionesDTO;
import pe.edu.upc.ecohabitproyecto.dtos.MetodoPagoDTO;
import pe.edu.upc.ecohabitproyecto.entities.Billetera;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IBilleteraService;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.ITransaccionService;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IUsuarioService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/billeteras")
public class BilleteraController {

    @Autowired
    private IBilleteraService bS;

    @Autowired
    private ITransaccionService transaccionService;

    @Autowired
    private IUsuarioService usuarioService;

    // Solo ADMIN puede listar todas las billeteras
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<BilleteraDTO> listar() {
        return bS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, BilleteraDTO.class);
        }).collect(Collectors.toList());
    }

    // Solo ADMIN puede insertar billeteras
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public void insertar(@RequestBody BilleteraDTO s) {
        ModelMapper m = new ModelMapper();
        Billetera bill = m.map(s, Billetera.class);
        bS.insert(bill);
    }

    // USER o ADMIN pueden consultar saldo
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/saldo/{idUsuario}")
    public ResponseEntity<Map<String, Object>> getSaldo(@PathVariable int idUsuario) {
        BigDecimal saldo = bS.getSaldoPuntos(idUsuario);

        Map<String, Object> response = new HashMap<>();
        response.put("idUsuario", idUsuario);
        response.put("saldoPuntos", saldo);

        return ResponseEntity.ok(response);
    }

    // USER o ADMIN pueden acumular puntos
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PostMapping("/acumular")
    public ResponseEntity<String> acumularPuntos(
            @RequestParam int idUsuario,
            @RequestParam double puntos) {
        bS.acumularPuntos(idUsuario, puntos);
        return ResponseEntity.ok("Puntos acumulados correctamente");
    }

    // USER o ADMIN pueden obtener su billetera
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/{idUsuario}")
    public ResponseEntity<BilleteraDTO> obtenerBilletera(@PathVariable int idUsuario) {
        Billetera billetera = bS.obtenerBilleteraPorUsuario(idUsuario);
        BilleteraDTO dto = new ModelMapper().map(billetera, BilleteraDTO.class);
        return ResponseEntity.ok(dto);
    }

    // USER o ADMIN pueden canjear puntos
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PostMapping("/canjear")
    public ResponseEntity<Map<String, Object>> canjearPuntos(
            @RequestParam int idUsuario,
            @RequestBody CanjePuntosDTO dto) {

        transaccionService.canjearPuntos(idUsuario, dto);

        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Canje realizado con éxito");
        response.put("idUsuario", idUsuario);
        response.put("puntosCanjeados", dto.getPuntosACanjear());

        return ResponseEntity.ok(response);
    }

    // USER o ADMIN pueden ver historial de transacciones
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/historial/{idUsuario}")
    public ResponseEntity<List<HistorialTransaccionesDTO>> historial(@PathVariable int idUsuario) {
        List<HistorialTransaccionesDTO> historial = transaccionService.getHistorialTransacciones(idUsuario);
        return ResponseEntity.ok(historial);
    }

    // USER o ADMIN pueden configurar/actualizar método de pago
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PutMapping("/retiros")
    public ResponseEntity<Map<String, Object>> configurarMetodoPago(
            @RequestParam int idUsuario,
            @RequestBody MetodoPagoDTO dto) {

        usuarioService.modificarMetodoPago(idUsuario, dto);

        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Método de pago actualizado correctamente");
        response.put("idUsuario", idUsuario);
        response.put("tipo", dto.getTipo());
        response.put("detalles", dto.getDetalles()); // <-- corregido

        return ResponseEntity.ok(response);
    }
}