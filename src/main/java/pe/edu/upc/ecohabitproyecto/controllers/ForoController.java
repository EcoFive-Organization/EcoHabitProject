package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.ForoDTO;
import pe.edu.upc.ecohabitproyecto.entities.Foro;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IForoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/foros")
public class ForoController {
    @Autowired
    private IForoService iForoService;

    @GetMapping
    public List<ForoDTO> listar() {
        return iForoService.list().stream().map(x->{
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(x,ForoDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody ForoDTO foroDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Foro foro = modelMapper.map(foroDTO,Foro.class);
        iForoService.insert(foro);
    }

}
