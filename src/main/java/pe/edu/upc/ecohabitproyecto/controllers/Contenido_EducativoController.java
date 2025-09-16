package pe.edu.upc.ecohabitproyecto.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.Contenido_EducativoDTO;
import pe.edu.upc.ecohabitproyecto.entities.Contenido_Educativo;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IContenido_EducativoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contenido_educativos")
public class Contenido_EducativoController {
    @Autowired
    private IContenido_EducativoService contenido_educativoService;

    // Listar
    @GetMapping
    public List<Contenido_EducativoDTO> listar() {
        return contenido_educativoService.list().stream().map(x->{
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(x, Contenido_EducativoDTO.class);
        }).collect(Collectors.toList());
    }

    // Registrar
    @PostMapping
    public void insertar(@RequestBody Contenido_EducativoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Contenido_Educativo contenidoEducativo = modelMapper.map(dto, Contenido_Educativo.class);
        contenido_educativoService.insert(contenidoEducativo);
    }

    // Eliminar fisico
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Contenido_Educativo contenido_educativo = contenido_educativoService.listId(id);
        if (contenido_educativo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        contenido_educativoService.delete(id);
        return ResponseEntity.ok("Registro eliminado con exito");
    }

    // Modificar
    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody Contenido_EducativoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Contenido_Educativo contenidoEducativo = modelMapper.map(dto, Contenido_Educativo.class);

        Contenido_Educativo existe = contenido_educativoService.listId(contenidoEducativo.getId_contenidoEducativo());

        if (existe == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + contenidoEducativo.getId_contenidoEducativo());
        }

        contenido_educativoService.update(contenidoEducativo);
        return  ResponseEntity.ok("Registro modificado con exito");

    }


}
