package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; // ⬅️ Nuevo import necesario
import org.springframework.security.core.Authentication; // ⬅️ Nuevo import necesario
import org.springframework.security.core.context.SecurityContextHolder; // ⬅️ Nuevo import necesario
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.SuscripcionDTO;
import pe.edu.upc.ecohabitproyecto.dtos.SeleccionarPlanDTO; // ⬅️ Nuevo DTO
import pe.edu.upc.ecohabitproyecto.entities.Suscripcion;
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
    private JwtUserDetailsService jwtUserDetailsService; // ⬅️ Necesario para la seguridad

    @GetMapping
    public List<SuscripcionDTO> listar(){
        return sS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,SuscripcionDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody SuscripcionDTO s){
        ModelMapper m = new ModelMapper();
        Suscripcion suscripcion = m.map(s, Suscripcion.class);
        sS.insert(suscripcion);
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
}
