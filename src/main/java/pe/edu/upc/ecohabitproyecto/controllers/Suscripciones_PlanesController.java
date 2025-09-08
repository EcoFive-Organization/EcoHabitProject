package pe.edu.upc.ecohabitproyecto.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.Suscripciones_PlanesDTO;
import pe.edu.upc.ecohabitproyecto.entities.Suscripciones_Planes;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.ISuscripciones_PlanesService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/suscripciones_planes")
public class Suscripciones_PlanesController {
    @Autowired // Para traer la clase del service
    private ISuscripciones_PlanesService suscripciones_planesService;

    @GetMapping
    public List<Suscripciones_PlanesDTO> listar(){
        return suscripciones_planesService.list().stream().map(x->{
            ModelMapper mapper = new ModelMapper();
            return mapper.map(x, Suscripciones_PlanesDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody Suscripciones_PlanesDTO suscripciones_planesDTO){

        ModelMapper mapper = new ModelMapper();
        Suscripciones_Planes suscripciones_planes = mapper.map(suscripciones_planesDTO, Suscripciones_Planes.class);
        suscripciones_planesService.insert(suscripciones_planes);
    }

}
