package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.DesafioDTO;
import pe.edu.upc.ecohabitproyecto.dtos.ParticipacionDesafioDTO;
import pe.edu.upc.ecohabitproyecto.entities.Desafio;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IDesafioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/desafios")
public class DesafioController {

    @Autowired
    private IDesafioService dS;

    @GetMapping
    public List<DesafioDTO> listar() {
        return dS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, DesafioDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody DesafioDTO dto) {
        ModelMapper m = new ModelMapper();
        Desafio e = m.map(dto, Desafio.class);
        dS.insert(e);
    }

    @GetMapping("/{id}")
    public DesafioDTO listarId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        return m.map(dS.listId(id), DesafioDTO.class);
    }

    @PutMapping
    public void actualizar(@RequestBody DesafioDTO dto) {
        ModelMapper m = new ModelMapper();
        Desafio e = m.map(dto, Desafio.class);
        dS.update(e);
    }

    //@PostMapping("/unirse-desafios")
    //public ResponseEntity<Void> unirseAlDesafio(
    //        @RequestParam Integer idUsuario,
    //        @RequestParam Integer idDesafio) {
    //    dS.unirseADesafio(idUsuario, idDesafio);
    //    return ResponseEntity.ok().build();
    //}

    @PostMapping("/unirse-desafios")
    public ResponseEntity<Void> unirseDesafios(@RequestBody ParticipacionDesafioDTO dto) {

        // 1. EXTRAER los IDs de los objetos anidados (Usuario y Desafio)
        // Asumimos que los IDs vienen cargados dentro del DTO
        Integer idUsuario = dto.getUsuario().getIdUsuario();
        Integer idDesafio = dto.getDesafio().getIdDesafio();

        // 2. LLAMAR al metodo del servicio que maneja la lógica de negocio (tu metodo 'unirseADesafio')
        dS.unirseADesafio(idUsuario, idDesafio);

        // 3. Devolver la respuesta de éxito
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        dS.delete(id);
    }
}
