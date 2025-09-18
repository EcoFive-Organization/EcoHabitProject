package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.SuscripcionDTO;
import pe.edu.upc.ecohabitproyecto.dtos.UsuarioDTOInsert;
import pe.edu.upc.ecohabitproyecto.dtos.UsuarioDTOList;
import pe.edu.upc.ecohabitproyecto.entities.Suscripcion;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.ISuscripcionService;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IUsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/suscripciones")
public class SuscripcionController {
    @Autowired
    private ISuscripcionService sS;

    @GetMapping
    public List<SuscripcionDTO> listar(){
        return sS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,SuscripcionDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody SuscripcionDTO s){
        ModelMapper m = new ModelMapper();
        Suscripcion suscripcion = m.map(s, Suscripcion.class);
        sS.insert(suscripcion);
    }
}
