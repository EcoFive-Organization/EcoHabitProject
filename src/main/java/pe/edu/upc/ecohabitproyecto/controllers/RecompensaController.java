package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.RecompensaDTO;
import pe.edu.upc.ecohabitproyecto.entities.Recompensa;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IRecompensaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recompensas")
public class RecompensaController {

    @Autowired
    private IRecompensaService rS;

    // ---------------- CRUD de Recompensas ----------------
    @GetMapping
    public List<RecompensaDTO> listar() {
        return rS.list().stream()
                .map(e -> new ModelMapper().map(e, RecompensaDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody RecompensaDTO dto) {
        Recompensa e = new ModelMapper().map(dto, Recompensa.class);
        rS.insert(e);
    }

    @GetMapping("/{id}")
    public RecompensaDTO listarId(@PathVariable int id) {
        Recompensa e = rS.listId(id);
        return new ModelMapper().map(e, RecompensaDTO.class);
    }

    @PutMapping
    public void actualizar(@RequestBody RecompensaDTO dto) {
        Recompensa e = new ModelMapper().map(dto, Recompensa.class);
        rS.update(e);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        rS.delete(id);
    }

    // ---------------- HU17: Otorgar recompensa ----------------
    @PostMapping("/otorgar")
    public ResponseEntity<String> otorgarRecompensa(
            @RequestParam int idUsuario,
            @RequestParam int idRecompensa) {

        rS.otorgarRecompensa(idUsuario, idRecompensa);
        return ResponseEntity.ok("Recompensa otorgada correctamente");
    }

    // ---------------- HU17: Listar recompensas de un usuario ----------------
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<RecompensaDTO>> listarRecompensasUsuario(@PathVariable int idUsuario) {
        List<Recompensa> recompensas = rS.listarRecompensasUsuario(idUsuario);
        List<RecompensaDTO> response = recompensas.stream()
                .map(r -> new ModelMapper().map(r, RecompensaDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}