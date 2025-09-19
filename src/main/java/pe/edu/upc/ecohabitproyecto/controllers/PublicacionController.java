package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.PublicacionDTO;
import pe.edu.upc.ecohabitproyecto.dtos.ReaccionDTO;
import pe.edu.upc.ecohabitproyecto.entities.Publicacion;
import pe.edu.upc.ecohabitproyecto.entities.Reaccion;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IPublicacionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {
    @Autowired
    private IPublicacionService publicacionService;

    // Listar publicaciones
    @GetMapping
    public List<PublicacionDTO> listar(){
        return publicacionService.list().stream().map(x -> {
            ModelMapper mapper = new ModelMapper();
            return mapper.map(x, PublicacionDTO.class);
        }).collect(Collectors.toList());
    }

    // Registrar una publicacion
    @PostMapping
    public void insertar(@RequestBody PublicacionDTO publicacionDTO){
        ModelMapper mapper = new ModelMapper();
        Publicacion publicacion = mapper.map(publicacionDTO, Publicacion.class);
        publicacionService.insert(publicacion);
    }

    // Listar por id
    @GetMapping("/{id}")
    public ResponseEntity<?> listId(@PathVariable("id") Integer id) {
        Publicacion publicacion = publicacionService.listId(id);
        if (publicacion == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        PublicacionDTO dto = m.map(publicacion, PublicacionDTO.class);
        return ResponseEntity.ok(dto);
    }

    // Eliminar fisico
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Publicacion publicacion = publicacionService.listId(id);
        if (publicacion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        publicacionService.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    // Modificar un registro
    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody PublicacionDTO dto) {
        ModelMapper m = new ModelMapper();
        // Transforma de dto a software
        Publicacion publicacion = m.map(dto, Publicacion.class);

        // Validación de existencia
        Publicacion existente = publicacionService.listId(publicacion.getId_publicacion());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + publicacion.getId_publicacion());
        }

        // Actualización si pasa validaciones
        publicacionService.update(publicacion);
        return ResponseEntity.ok("Registro con ID " + publicacion.getId_publicacion() + " modificado correctamente.");
    }

}
