package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.ForoDTO;
import pe.edu.upc.ecohabitproyecto.dtos.QuantityPostForumDTO;
import pe.edu.upc.ecohabitproyecto.dtos.ReaccionDTO;
import pe.edu.upc.ecohabitproyecto.entities.Foro;
import pe.edu.upc.ecohabitproyecto.entities.Reaccion;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IForoService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/foros")
public class ForoController {
    @Autowired
    private IForoService iForoService;

    // Listar los foros registrados
    @GetMapping
    public List<ForoDTO> listar() {
        return iForoService.list().stream().map(x->{
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(x,ForoDTO.class);
        }).collect(Collectors.toList());
    }

    // Registrar un foro
    @PostMapping
    public void insertar(@RequestBody ForoDTO foroDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Foro foro = modelMapper.map(foroDTO,Foro.class);
        iForoService.insert(foro);
    }

    // Listar por id
    @GetMapping("/{id}")
    public ResponseEntity<?> listId(@PathVariable("id") Integer id) {
        Foro foro = iForoService.listId(id);
        if (foro == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        ForoDTO dto = m.map(foro, ForoDTO.class);
        return ResponseEntity.ok(dto);
    }

    // Eliminar fisico
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Foro foro = iForoService.listId(id);
        if (foro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        iForoService.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    // Modificar un registro
    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody ForoDTO dto) {
        ModelMapper m = new ModelMapper();
        // Transforma de dto a software
        Foro foro = m.map(dto, Foro.class);

        // Validación de existencia
        Foro existente = iForoService.listId(foro.getIdForo());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + foro.getIdForo());
        }

        // Actualización si pasa validaciones
        iForoService.update(foro);
        return ResponseEntity.ok("Registro con ID " + foro.getIdForo() + " modificado correctamente.");
    }

    // Cantidad de publicaciones según foro
    @GetMapping("/cantidades")
    public ResponseEntity<?> obtenerCantidad() {
        List<QuantityPostForumDTO> listaDTO = new ArrayList<>();
        List<String[]> fila = iForoService.quantityPostByForum(); // aqui están las cantidades

        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron registros.");
        }

        for(String[] columna : fila) {
            QuantityPostForumDTO dto = new QuantityPostForumDTO();
            dto.setNameForum(columna[0]); // primera columna "Nombre foro"
            dto.setQuantityPost(Integer.parseInt(columna[1])); // segunda columna "Cantidad Publicaciones"
            listaDTO.add(dto);
        }

        return ResponseEntity.ok(listaDTO);

    }


}
