package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.CantidadReaccionesPublicacionDTO;
import pe.edu.upc.ecohabitproyecto.dtos.PublicacionDTO;
import pe.edu.upc.ecohabitproyecto.entities.Publicacion;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IPublicacionService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
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
        publicacionService.crearPublicacion(publicacion);
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
        publicacionService.eliminarPublicacion(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    // Modificar un registro
    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody PublicacionDTO dto) {
        ModelMapper m = new ModelMapper();
        // Transforma de dto a software
        Publicacion publicacion = m.map(dto, Publicacion.class);

        // Validación de existencia
        Publicacion existente = publicacionService.listId(publicacion.getIdPublicacion());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + publicacion.getIdPublicacion());
        }

        // Actualización si pasa validaciones
        publicacionService.modificarPublicacion(publicacion);
        return ResponseEntity.ok("Registro con ID " + publicacion.getIdPublicacion() + " modificado correctamente.");
    }

    // Cantidad de publicaciones según foro
    @GetMapping("/cantidadReacciones")
    public ResponseEntity<?> getCantidadReacciones() {
        List<CantidadReaccionesPublicacionDTO> listaDTO = new ArrayList<>();
        List<String[]> fila = publicacionService.getCantidadReacciones(); // aqui están las cantidades

        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron registros.");
        }

        for(String[] columna : fila) {
            CantidadReaccionesPublicacionDTO dto = new CantidadReaccionesPublicacionDTO();
            dto.setTitulo(columna[0]); // primera columna "Nombre foro"
            dto.setCantidadReacciones(Integer.parseInt(columna[1])); // segunda columna "Cantidad Publicaciones"
            listaDTO.add(dto);
        }

        return ResponseEntity.ok(listaDTO);

    }


}
