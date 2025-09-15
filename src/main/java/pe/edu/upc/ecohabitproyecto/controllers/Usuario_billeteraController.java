package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.Suscripciones_PlanesDTO;
import pe.edu.upc.ecohabitproyecto.dtos.Usuario_billeteraDTO;
import pe.edu.upc.ecohabitproyecto.entities.Suscripciones_Planes;
import pe.edu.upc.ecohabitproyecto.entities.Usuario_billetera;
import pe.edu.upc.ecohabitproyecto.repositories.IUsuario_billeteraRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.ISuscripciones_PlanesService;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IUsuario_billeteraService;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/usuario_billetera")
public class Usuario_billeteraController {
    @Autowired // Para traer la clase del service
    private IUsuario_billeteraService usuarioBilleteraService;

    @GetMapping
    public List<Usuario_billeteraDTO> listar(){
        return usuarioBilleteraService.list().stream().map(x->{
            ModelMapper mapper = new ModelMapper();
            return mapper.map(x, Usuario_billeteraDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody Usuario_billeteraDTO usuarioBilleteraDTO){

        ModelMapper mapper = new ModelMapper();
        Usuario_billetera usuarioBilletera = mapper.map(usuarioBilleteraDTO, Usuario_billetera.class);
        usuarioBilleteraService.insert(usuarioBilletera);
    }
}
