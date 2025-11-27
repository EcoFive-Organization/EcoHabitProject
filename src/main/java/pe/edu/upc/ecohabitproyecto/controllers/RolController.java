package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.RolDTO;
import pe.edu.upc.ecohabitproyecto.entities.Rol;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IRolService;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RolController {
    @Autowired // Para traer la clase del service
    private IRolService rS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<RolDTO> listar() {
        return rS.list().stream().map(x -> {
            ModelMapper mapper = new ModelMapper();
            return mapper.map(x, RolDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void insertar(@RequestBody RolDTO rolDTO) {

        ModelMapper mapper = new ModelMapper();
        Rol rol = mapper.map(rolDTO, Rol.class);
        rS.insert(rol);
    }
}