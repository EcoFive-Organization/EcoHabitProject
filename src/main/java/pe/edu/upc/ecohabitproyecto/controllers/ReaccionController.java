package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.ReaccionDTO;
import pe.edu.upc.ecohabitproyecto.entities.Reaccion;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IReaccionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reacciones")
public class ReaccionController {
    @Autowired
    private IReaccionService reaccionService;

    @GetMapping
    public List<ReaccionDTO> listar(){
        return reaccionService.list().stream().map(x-> {
            ModelMapper mapper = new ModelMapper();
            return mapper.map(x, ReaccionDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody ReaccionDTO dto){
        ModelMapper mapper = new ModelMapper();
        Reaccion reaccion = mapper.map(dto, Reaccion.class);
        reaccionService.insert(reaccion);
    }

}
