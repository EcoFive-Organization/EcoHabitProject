package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.RecursosDTO;
import pe.edu.upc.ecohabitproyecto.entities.Recursos;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IRecursosService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recursos")
public class RecursosController {
    @Autowired
    private IRecursosService recursosService;

    // @GetMapping, verbo GET en HTTP
    @GetMapping
    public List<RecursosDTO> listar() {
        return recursosService.list().stream().map(x->{
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(x, RecursosDTO.class);
        }).collect(Collectors.toList());
    }

    // PostMapping para insertar datos
    @PostMapping
    public void insertar(@RequestBody RecursosDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Recursos recursos = modelMapper.map(dto, Recursos.class);
        recursosService.insert(recursos);
    }



}
