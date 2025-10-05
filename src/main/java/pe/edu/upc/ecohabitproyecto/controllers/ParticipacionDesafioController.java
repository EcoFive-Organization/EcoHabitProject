package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.ParticipacionDesafioDTO;
import pe.edu.upc.ecohabitproyecto.entities.ParticipacionDesafio;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IParticipacionDesafioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/participaciones-desafio")
public class ParticipacionDesafioController {

    @Autowired
    private IParticipacionDesafioService pDS;

    @GetMapping
    public List<ParticipacionDesafioDTO> listar() {
        return pDS.list().stream()
                .map(e -> new ModelMapper().map(e, ParticipacionDesafioDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody ParticipacionDesafioDTO dto) {
        ParticipacionDesafio e = new ModelMapper().map(dto, ParticipacionDesafio.class);
        pDS.insert(e);
    }

    @GetMapping("/{id}")
    public ParticipacionDesafioDTO listarId(@PathVariable Integer id) { // 🔹 corregido a Integer
        ParticipacionDesafio e = pDS.listId(id);
        return new ModelMapper().map(e, ParticipacionDesafioDTO.class);
    }

    @PutMapping
    public void actualizar(@RequestBody ParticipacionDesafioDTO dto) {
        ParticipacionDesafio e = new ModelMapper().map(dto, ParticipacionDesafio.class);
        pDS.update(e);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) { // 🔹 corregido a Integer
        pDS.delete(id);
    }
}