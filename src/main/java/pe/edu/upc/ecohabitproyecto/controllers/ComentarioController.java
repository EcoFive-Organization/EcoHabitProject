package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.ComentarioDTO;
import pe.edu.upc.ecohabitproyecto.dtos.ReaccionDTO;
import pe.edu.upc.ecohabitproyecto.entities.Comentario;
import pe.edu.upc.ecohabitproyecto.entities.Reaccion;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IComentarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/comentarios")
public class ComentarioController {
    @Autowired
    private IComentarioService comentarioService;

    // Listar comentarios
    @GetMapping
    public List<ComentarioDTO> listar(){
        return comentarioService.list().stream().map(x->{
            ModelMapper mapper = new ModelMapper();
            return mapper.map(x, ComentarioDTO.class);
        }).collect(Collectors.toList());
    }

    // Registrar un comentario
    @PostMapping
    public void insertar(@RequestBody ComentarioDTO comentarioDTO){
        ModelMapper mapper = new ModelMapper();
        Comentario comentario = mapper.map(comentarioDTO, Comentario.class);
        comentarioService.crearComentario(comentario);
    }

    // Listar por id
    @GetMapping("/{id}")
    public ResponseEntity<?> listId(@PathVariable("id") Integer id) {
        Comentario comentario = comentarioService.listId(id);
        if (comentario == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        ComentarioDTO dto = m.map(comentario, ComentarioDTO.class);
        return ResponseEntity.ok(dto);
    }

    // Eliminar fisico
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Comentario comentario = comentarioService.listId(id);
        if (comentario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        comentarioService.eliminarComentario(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    // Modificar un registro
    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody ComentarioDTO dto) {
        ModelMapper m = new ModelMapper();
        // Transforma de dto a software
        Comentario comentario = m.map(dto, Comentario.class);

        // Validación de existencia
        Comentario existente = comentarioService.listId(comentario.getIdComentario());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + comentario.getIdComentario());
        }

        // Actualización si pasa validaciones
        comentarioService.modificarComentario(comentario);
        return ResponseEntity.ok("Registro con ID " + comentario.getIdComentario() + " modificado correctamente.");
    }

}
