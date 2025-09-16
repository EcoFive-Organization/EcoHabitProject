package pe.edu.upc.ecohabitproyecto.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<Contenido_EducativoDTO> listar() {
        return contenido_educativoService.list().stream().map(x->{
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(x, Contenido_EducativoDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody Contenido_EducativoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Contenido_Educativo contenidoEducativo = modelMapper.map(dto, Contenido_Educativo.class);
        contenido_educativoService.insert(contenidoEducativo);
    }

}
