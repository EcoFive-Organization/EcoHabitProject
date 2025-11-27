package pe.edu.upc.ecohabitproyecto.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.ContenidoEducativoDTO;
import pe.edu.upc.ecohabitproyecto.dtos.ListarTipoContenidoDTO;
import pe.edu.upc.ecohabitproyecto.entities.ContenidoEducativo;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IContenidoEducativoService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/educacion")
public class ContenidoEducativoController {
    @Autowired
    private IContenidoEducativoService contenido_educativoService;

    // Listar
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    public List<ContenidoEducativoDTO> listar() {
        return contenido_educativoService.list().stream().map(x->{
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(x, ContenidoEducativoDTO.class);
        }).collect(Collectors.toList());
    }

    // Registrar
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void insertar(@RequestBody ContenidoEducativoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        ContenidoEducativo contenidoEducativo = modelMapper.map(dto, ContenidoEducativo.class);
        contenido_educativoService.registrarContenidoEducativo(contenidoEducativo);
    }

    // Eliminar fisico
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        ContenidoEducativo contenido_educativo = contenido_educativoService.listIdContenidoEducativo(id);
        if (contenido_educativo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        contenido_educativoService.eliminarContenidoEducativo(id);
        return ResponseEntity.ok("Registro eliminado con exito");
    }

    // Modificar
    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> modificar(@RequestBody ContenidoEducativoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        ContenidoEducativo contenidoEducativo = modelMapper.map(dto, ContenidoEducativo.class);

        ContenidoEducativo existe = contenido_educativoService.listIdContenidoEducativo(contenidoEducativo.getIdContenidoEducativo());

        if (existe == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + contenidoEducativo.getIdContenidoEducativo());
        }

        contenido_educativoService.modificarContenidoEducativo(contenidoEducativo);
        return  ResponseEntity.ok("Registro modificado con exito");

    }

    // Listar tipo Lectura
    @GetMapping("/lecturas")
    public ResponseEntity<?> obtenerLecturas() {
        List<ListarTipoContenidoDTO> listaDTO = new ArrayList<>();
        List<String[]> fila = contenido_educativoService.getLecturasEducativas(); // aqui están las cantidades

        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron registros.");
        }

        for(String[] columna : fila) {
            ListarTipoContenidoDTO dto = new ListarTipoContenidoDTO();
            dto.setTitulo(columna[0]);
            dto.setDescripcion(columna[1]);
            dto.setUrl(columna[2]);
            dto.setTipo(columna[3]);
            listaDTO.add(dto);
        }

        return ResponseEntity.ok(listaDTO);

    }

    // Listar tipo Video
    @GetMapping("/videos")
    public ResponseEntity<?> obtenerVideos() {
        List<ListarTipoContenidoDTO> listaDTO = new ArrayList<>();
        List<String[]> fila = contenido_educativoService.getVideosEducativos(); // aqui están los datos

        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron registros.");
        }

        for(String[] columna : fila) {
            ListarTipoContenidoDTO dto = new ListarTipoContenidoDTO();
            dto.setTitulo(columna[0]);
            dto.setDescripcion(columna[1]);
            dto.setUrl(columna[2]);
            dto.setTipo(columna[3]);
            listaDTO.add(dto);
        }

        return ResponseEntity.ok(listaDTO);

    }

    // Listar por id
    @GetMapping("/{id}")
    public ResponseEntity<?> listId(@PathVariable("id") Integer id) {
        ContenidoEducativo contenidoEducativo =  contenido_educativoService.listIdContenidoEducativo(id);
        if (contenidoEducativo == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper modelMapper = new ModelMapper();
        ContenidoEducativoDTO dto = modelMapper.map(contenidoEducativo, ContenidoEducativoDTO.class);
        return ResponseEntity.ok(dto);
    }



}
