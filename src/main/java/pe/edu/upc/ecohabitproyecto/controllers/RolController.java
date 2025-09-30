package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.QuantityPostForumDTO;
import pe.edu.upc.ecohabitproyecto.dtos.RolDTO;
import pe.edu.upc.ecohabitproyecto.dtos.RolDTOList;
import pe.edu.upc.ecohabitproyecto.entities.Rol;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IRolService;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RolController {
    @Autowired // Para traer la clase del service
    private IRolService rS;

    @GetMapping
    public List<RolDTOList> listar() {
        return rS.list().stream().map(x -> {
            ModelMapper mapper = new ModelMapper();
            return mapper.map(x, RolDTOList.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody RolDTO rolDTO) {

        ModelMapper mapper = new ModelMapper();
        Rol rol = mapper.map(rolDTO, Rol.class);
        rS.insert(rol);
    }
}