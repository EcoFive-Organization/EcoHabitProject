package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.DesafioDTO;
import pe.edu.upc.ecohabitproyecto.dtos.ParticipacionDesafioDTO;
import pe.edu.upc.ecohabitproyecto.dtos.DesafioAmigoDTO;
import pe.edu.upc.ecohabitproyecto.entities.Desafio;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IDesafioService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/desafios")
public class DesafioController {

    @Autowired
    private IDesafioService dS;

    // 🔹 Listar todos los desafíos
    @GetMapping
    public List<DesafioDTO> listar() {
        return dS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, DesafioDTO.class);
        }).collect(Collectors.toList());
    }

    // 🔹 Insertar un desafío normal
    @PostMapping
    public void insertar(@RequestBody DesafioDTO dto) {
        ModelMapper m = new ModelMapper();
        Desafio e = m.map(dto, Desafio.class);
        dS.insert(e);
    }

    // 🔹 Buscar desafío por ID
    @GetMapping("/{id}")
    public DesafioDTO listarId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        return m.map(dS.listId(id), DesafioDTO.class);
    }

    // 🔹 Actualizar desafío
    @PutMapping
    public void actualizar(@RequestBody DesafioDTO dto) {
        ModelMapper m = new ModelMapper();
        Desafio e = m.map(dto, Desafio.class);
        dS.update(e);
    }

    // 🔹 HU27: Unirse a un desafío comunitario
    @PostMapping("/comunitarios/unirse")
    public ResponseEntity<Map<String, Object>> unirseADesafio(@RequestBody ParticipacionDesafioDTO dto) {

        Integer idUsuario = dto.getUsuario().getIdUsuario();
        Integer idDesafio = dto.getDesafio().getIdDesafio();

        dS.unirseADesafioComunitario(idUsuario, idDesafio);

        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Usuario inscrito correctamente en el desafío comunitario");
        response.put("idUsuario", idUsuario);
        response.put("idDesafio", idDesafio);

        return ResponseEntity.ok(response);
    }

    // 🔹 HU52: Crear desafío con amigos
    @PostMapping("/amigos")
    public ResponseEntity<Map<String, Object>> crearDesafioAmigo(@RequestBody DesafioAmigoDTO dto) {
        dS.crearDesafioAmigo(dto);

        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Desafío con amigos creado correctamente");
        response.put("meta", dto.getMeta());
        response.put("idCreador", dto.getIdCreador());
        response.put("amigosInvitados", dto.getAmigosIds());

        return ResponseEntity.ok(response);
    }

    // 🔹 HU53: Eliminar desafío con amigos
    @DeleteMapping("/amigos/{idDesafio}/{idCreador}")
    public ResponseEntity<Map<String, Object>> eliminarDesafioAmigo(
            @PathVariable Integer idDesafio,
            @PathVariable Integer idCreador) {

        dS.eliminarDesafioAmigo(idDesafio, idCreador);

        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Desafío con amigos eliminado correctamente");
        response.put("idDesafio", idDesafio);
        response.put("idCreador", idCreador);

        return ResponseEntity.ok(response);
    }

    // 🔹 HU54: Unirse a un desafío con amigos
    @PostMapping("/amigos/unirse")
    public ResponseEntity<Map<String, Object>> unirseADesafioAmigo(
            @RequestParam Integer idUsuario,
            @RequestParam Integer idDesafioAmigo) {

        dS.unirseADesafioAmigo(idUsuario, idDesafioAmigo);

        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Usuario inscrito correctamente en el desafío con amigos");
        response.put("idUsuario", idUsuario);
        response.put("idDesafioAmigo", idDesafioAmigo);

        return ResponseEntity.ok(response);
    }

    // 🔹 Eliminar desafío normal
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        dS.delete(id);
    }
}