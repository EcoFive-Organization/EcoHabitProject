package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.Participacion_DesafioDTO;
import pe.edu.upc.ecohabitproyecto.entities.Participacion_Desafio;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IParticipacion_DesafioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/participaciones-desafio")
public class Participacion_DesafioController {

    @Autowired
    private IParticipacion_DesafioService pDS;

    @GetMapping
    public List<Participacion_DesafioDTO> listar() {
        return pDS.list().stream()
                .map(e -> new ModelMapper().map(e, Participacion_DesafioDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody Participacion_DesafioDTO dto) {
        Participacion_Desafio e = new ModelMapper().map(dto, Participacion_Desafio.class);
        pDS.insert(e);
    }

    @GetMapping("/{id}")
    public Participacion_DesafioDTO listarId(@PathVariable long id) {
        Participacion_Desafio e = pDS.listId(id);
        return new ModelMapper().map(e, Participacion_DesafioDTO.class);
    }

    @PutMapping
    public void actualizar(@RequestBody Participacion_DesafioDTO dto) {
        Participacion_Desafio e = new ModelMapper().map(dto, Participacion_Desafio.class);
        pDS.update(e);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable long id) {
        pDS.delete(id);
    }
}