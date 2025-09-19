package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.ReaccionDTO;
import pe.edu.upc.ecohabitproyecto.entities.Reaccion;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IReaccionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reacciones")
public class ReaccionController {
    @Autowired
    private IReaccionService reaccionService;

    @GetMapping
    public List<ReaccionDTO> listar(){
        return reaccionService.list().stream().map(x-> {
            ModelMapper mapper = new ModelMapper();
            return mapper.map(x, ReaccionDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody ReaccionDTO dto){
        ModelMapper mapper = new ModelMapper();
        Reaccion reaccion = mapper.map(dto, Reaccion.class);
        reaccionService.insert(reaccion);
    }

    // Listar por id
    @GetMapping("/{id}")
    public ResponseEntity<?> listId(@PathVariable("id") Integer id) {
        Reaccion reaccion = reaccionService.listId(id);
        if (reaccion == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        ReaccionDTO dto = m.map(reaccion, ReaccionDTO.class);
        return ResponseEntity.ok(dto);
    }

    // Eliminar fisico
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Reaccion reaccion = reaccionService.listId(id);
        if (reaccion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        reaccionService.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    // Modificar un registro
    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody ReaccionDTO dto) {
        ModelMapper m = new ModelMapper();
        // Transforma de dto a software
        Reaccion reaccion = m.map(dto, Reaccion.class);

        // Validación de existencia
        Reaccion existente = reaccionService.listId(reaccion.getIdReaccion());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + reaccion.getIdReaccion());
        }

        // Actualización si pasa validaciones
        reaccionService.update(reaccion);
        return ResponseEntity.ok("Registro con ID " + reaccion.getIdReaccion() + " modificado correctamente.");
    }

}
