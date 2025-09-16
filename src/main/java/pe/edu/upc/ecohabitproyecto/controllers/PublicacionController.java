package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.PublicacionDTO;
import pe.edu.upc.ecohabitproyecto.entities.Publicacion;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IPublicacionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {
    @Autowired
    private IPublicacionService publicacionService;

    @GetMapping
    public List<PublicacionDTO> listar(){
        return publicacionService.list().stream().map(x -> {
            ModelMapper mapper = new ModelMapper();
            return mapper.map(x, PublicacionDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody PublicacionDTO publicacionDTO){
        ModelMapper mapper = new ModelMapper();
        Publicacion publicacion = mapper.map(publicacionDTO, Publicacion.class);
        publicacionService.insert(publicacion);
    }
}
