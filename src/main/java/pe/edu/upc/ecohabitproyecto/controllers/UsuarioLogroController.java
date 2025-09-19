package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.UsuarioLogroDTO;
import pe.edu.upc.ecohabitproyecto.entities.UsuarioLogro;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IUsuarioLogroService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario-logros")
public class UsuarioLogroController {

    @Autowired
    private IUsuarioLogroService uLS;

    @GetMapping
    public List<UsuarioLogroDTO> listar() {
        return uLS.list().stream()
                .map(e -> new ModelMapper().map(e, UsuarioLogroDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody UsuarioLogroDTO dto) {
        UsuarioLogro e = new ModelMapper().map(dto, UsuarioLogro.class);
        uLS.insert(e);
    }

    @GetMapping("/{id}")
    public UsuarioLogroDTO listarId(@PathVariable int id) {
        UsuarioLogro e = uLS.listId(id);
        return new ModelMapper().map(e, UsuarioLogroDTO.class);
    }

    @PutMapping
    public void actualizar(@RequestBody UsuarioLogroDTO dto) {
        UsuarioLogro e = new ModelMapper().map(dto, UsuarioLogro.class);
        uLS.update(e);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        uLS.delete(id);
    }
}