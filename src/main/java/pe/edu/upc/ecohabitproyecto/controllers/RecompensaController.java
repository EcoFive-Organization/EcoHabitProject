package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.RecompensaDTO;
import pe.edu.upc.ecohabitproyecto.entities.Recompensa;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IRecompensaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recompensas")
public class RecompensaController {

    @Autowired
    private IRecompensaService rS;

    @GetMapping
    public List<RecompensaDTO> listar() {
        return rS.list().stream()
                .map(e -> new ModelMapper().map(e, RecompensaDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody RecompensaDTO dto) {
        Recompensa e = new ModelMapper().map(dto, Recompensa.class);
        rS.insert(e);
    }

    @GetMapping("/{id}")
    public RecompensaDTO listarId(@PathVariable int id) {
        Recompensa e = rS.listId(id);
        return new ModelMapper().map(e, RecompensaDTO.class);
    }

    @PutMapping
    public void actualizar(@RequestBody RecompensaDTO dto) {
        Recompensa e = new ModelMapper().map(dto, Recompensa.class);
        rS.update(e);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        rS.delete(id);
    }
}