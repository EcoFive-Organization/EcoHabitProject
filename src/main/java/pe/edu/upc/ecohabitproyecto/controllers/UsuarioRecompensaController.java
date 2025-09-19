package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.UsuarioRecompensaDTO;
import pe.edu.upc.ecohabitproyecto.entities.UsuarioRecompensa;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IUsuarioRecompensaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios_recompensas")
public class UsuarioRecompensaController {
    @Autowired
    private IUsuarioRecompensaService uS;

    @GetMapping
    public List<UsuarioRecompensaDTO> listar(){
        return uS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, UsuarioRecompensaDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody UsuarioRecompensa u){
        ModelMapper m = new ModelMapper();
        UsuarioRecompensa usuario_recompensa = m.map(u, UsuarioRecompensa.class);
        uS.insert(usuario_recompensa);
    }
}
