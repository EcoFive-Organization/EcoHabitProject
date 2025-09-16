package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.ComentarioDTO;
import pe.edu.upc.ecohabitproyecto.entities.Comentario;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IComentarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {
    @Autowired
    private IComentarioService comentarioService;

    // Listar
    @GetMapping
    public List<ComentarioDTO> listar(){
        return comentarioService.list().stream().map(x->{
            ModelMapper mapper = new ModelMapper();
            return mapper.map(x, ComentarioDTO.class);
        }).collect(Collectors.toList());
    }

    // Registrar
    @PostMapping
    public void insertar(@RequestBody ComentarioDTO comentarioDTO){
        ModelMapper mapper = new ModelMapper();
        Comentario comentario = mapper.map(comentarioDTO, Comentario.class);
        comentarioService.insert(comentario);
    }

}
