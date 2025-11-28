package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; // ⬅️ Nuevo import necesario
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication; // ⬅️ Nuevo import necesario
import org.springframework.security.core.context.SecurityContextHolder; // ⬅️ Nuevo import necesario
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.ProcesarPagoDTO;
import pe.edu.upc.ecohabitproyecto.dtos.SuscripcionDTO;
import pe.edu.upc.ecohabitproyecto.dtos.SeleccionarPlanDTO; // ⬅️ Nuevo DTO
import pe.edu.upc.ecohabitproyecto.entities.Suscripcion;
import pe.edu.upc.ecohabitproyecto.entities.SuscripcionPago;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.ISuscripcionService; // Su interfaz
import pe.edu.upc.ecohabitproyecto.servicesimplements.JwtUserDetailsService; // ⬅️ Nuevo servicio

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/suscripciones")
public class SuscripcionController {
    @Autowired
    private ISuscripcionService sS;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService; // Necesario para la seguridad

    @GetMapping("/validar/{idUsuario}")
    public boolean validarSuscripcion(@PathVariable("idUsuario") Integer idUsuario) {
        return sS.verificarSuscripcionActiva(idUsuario);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<SuscripcionDTO> listar(){
        return sS.list().stream().map(x -> {
            SuscripcionDTO dto = new SuscripcionDTO();
            dto.setIdSuscripcion(x.getIdSuscripcion());
            dto.setFechaInicio(x.getFechaInicio());
            dto.setFechaFin(x.getFechaFin());
            dto.setEstado(x.getEstado());
            dto.setPaypalSuscripcionId(x.getPaypalSuscripcionId());

            // IDs
            dto.setIdUsuario(x.getUsuario().getIdUsuario());
            dto.setIdPlanSuscripcion(x.getPlanSuscripcion().getIdPlanSuscripcion());

            // LLENAR LOS NOMBRES PARA LA VISTA
            dto.setNombreUsuario(x.getUsuario().getNombre());
            dto.setNombrePlan(x.getPlanSuscripcion().getNombre());

            return dto;
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    public void insertar(@RequestBody SuscripcionDTO s){
        sS.insert(s);
    }

    // ✅ NUEVO ENDPOINT PARA SELECCIONAR PLAN
    @PostMapping("/seleccionar-plan")
    public ResponseEntity<Suscripcion> seleccionarPlan(@RequestBody SeleccionarPlanDTO planDTO) {

        // 1. Obtener el nombre de usuario del token JWT
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // 2. Resolver el nombre de usuario al ID de la base de datos
        Integer idUsuario = jwtUserDetailsService.getIdUsuarioByUsername(username);

        if (idUsuario == null) {
            // Si no se encuentra el usuario (lo cual es raro si el token es válido)
            return ResponseEntity.status(401).build(); // 401 No autorizado
        }

        try {
            // 3. Llamar al servicio para realizar el INSERT/UPDATE
            Suscripcion nuevaSuscripcion = sS.seleccionarPlan(idUsuario, planDTO.getIdPlan());

            // 4. Devolver 200 OK con el objeto suscripción actualizado/creado
            return ResponseEntity.ok(nuevaSuscripcion);
        } catch (RuntimeException e) {
            // Manejar errores de Plan no encontrado (404/400)
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/procesar-pago")
    public ResponseEntity<?> procesarPago(@RequestBody ProcesarPagoDTO pagoDTO) {

        // 1. Obtener ID de Usuario desde el Token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Integer idUsuario = jwtUserDetailsService.getIdUsuarioByUsername(username);

        if (idUsuario == null) {
            return ResponseEntity.status(401).build();
        }

        try {
            // 2. Procesar el Pago
            SuscripcionPago pagoRealizado = sS.procesarPago(idUsuario, pagoDTO.getIdMetodoPago());

            // 3. Devolver Respuesta
            if (pagoRealizado.getEstado().equals("EXITOSO")) {
                return ResponseEntity.ok(pagoRealizado);
            } else {
                // 402 Payment Required: Pago registrado como fallido
                return ResponseEntity.status(402).body(pagoRealizado);
            }
        } catch (RuntimeException e) {
            // 400 Bad Request: Errores de negocio (e.g., "Metodo de pago inactivo")
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/cancelar")
    public ResponseEntity<?> cancelarSuscripcion() {

        // 1. Obtener ID de Usuario desde el Token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Integer idUsuario = jwtUserDetailsService.getIdUsuarioByUsername(username);

        if (idUsuario == null) {
            return ResponseEntity.status(401).build(); // No autorizado
        }

        try {
            // 2. Cancelar la suscripción
            Suscripcion suscripcionCancelada = sS.cancelarSuscripcion(idUsuario);

            // 3. Devolver el objeto actualizado
            return ResponseEntity.ok(suscripcionCancelada);

        } catch (RuntimeException e) {
            // 400 Bad Request: Errores de negocio (suscripción no activa, no encontrada)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
