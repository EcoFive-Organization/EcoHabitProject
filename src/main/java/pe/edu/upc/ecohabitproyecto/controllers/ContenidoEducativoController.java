package pe.edu.upc.ecohabitproyecto.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.ContenidoEducativoDTO;
import pe.edu.upc.ecohabitproyecto.entities.ContenidoEducativo;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IContenidoEducativoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contenidos_educativos")
public class ContenidoEducativoController {
    @Autowired
    private IContenidoEducativoService contenido_educativoService;

    // Listar
    @GetMapping
    public List<ContenidoEducativoDTO> listar() {
        return contenido_educativoService.list().stream().map(x->{
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(x, ContenidoEducativoDTO.class);
        }).collect(Collectors.toList());
    }

    // Registrar
    @PostMapping
    public void insertar(@RequestBody ContenidoEducativoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        ContenidoEducativo contenidoEducativo = modelMapper.map(dto, ContenidoEducativo.class);
        contenido_educativoService.insert(contenidoEducativo);
    }

    // Eliminar fisico
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        ContenidoEducativo contenido_educativo = contenido_educativoService.listId(id);
        if (contenido_educativo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        contenido_educativoService.delete(id);
        return ResponseEntity.ok("Registro eliminado con exito");
    }

    // Modificar
    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody ContenidoEducativoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        ContenidoEducativo contenidoEducativo = modelMapper.map(dto, ContenidoEducativo.class);

        ContenidoEducativo existe = contenido_educativoService.listId(contenidoEducativo.getIdContenidoEducativo());

        if (existe == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + contenidoEducativo.getIdContenidoEducativo());
        }

        contenido_educativoService.update(contenidoEducativo);
        return  ResponseEntity.ok("Registro modificado con exito");

    }


}
