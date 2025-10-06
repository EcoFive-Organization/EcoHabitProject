package pe.edu.upc.ecohabitproyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.LogroDTO;
import pe.edu.upc.ecohabitproyecto.entities.Logro;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IAdminService;

@RestController
@RequestMapping("/api/admin/logros")
@PreAuthorize("hasAuthority('ADMIN')") // Protege todos los métodos del controlador
public class LogroAdminController {

    @Autowired
    private IAdminService adminService;

    @PostMapping
    public ResponseEntity<Logro> registrarLogro(@RequestBody LogroDTO dto) {
        Logro nuevoLogro = adminService.registrarLogro(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLogro);
    }

    @PutMapping("/{idLogro}")
    public ResponseEntity<Logro> modificarLogro(@PathVariable("idLogro") Integer idLogro,
                                                @RequestBody LogroDTO dto) {
        System.out.println("ID recibido en controlador (PUT): " + idLogro);
        Logro actualizado = adminService.modificarLogro(idLogro, dto);
        return ResponseEntity.ok(actualizado);
    }

    @GetMapping("/{idLogro}")
    public ResponseEntity<Logro> obtenerLogroPorId(@PathVariable("idLogro") Integer idLogro) {
        Logro logro = adminService.obtenerLogroPorId(idLogro);
        return ResponseEntity.ok(logro);
    }

    @DeleteMapping("/{idLogro}")
    public ResponseEntity<Void> eliminarLogro(@PathVariable("idLogro") Integer idLogro) {
        System.out.println("ID recibido en controlador (DELETE): " + idLogro);
        adminService.eliminarLogro(idLogro);
        return ResponseEntity.noContent().build();
    }
}