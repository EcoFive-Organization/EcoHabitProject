package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.Suscripcion_PagoDTO;
import pe.edu.upc.ecohabitproyecto.dtos.Usuario_RecompensaDTO;
import pe.edu.upc.ecohabitproyecto.entities.Suscripcion_Pago;
import pe.edu.upc.ecohabitproyecto.entities.Usuario_Recompensa;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.ISuscripcion_PagoService;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IUsuario_RecompensaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios_recompensas")
public class Usuario_RecompensaController {
    @Autowired
    private IUsuario_RecompensaService uS;

    @GetMapping
    public List<Usuario_RecompensaDTO> listar(){
        return uS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, Usuario_RecompensaDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody Usuario_Recompensa u){
        ModelMapper m = new ModelMapper();
        Usuario_Recompensa usuario_recompensa = m.map(u, Usuario_Recompensa.class);
        uS.insert(usuario_recompensa);
    }
}
