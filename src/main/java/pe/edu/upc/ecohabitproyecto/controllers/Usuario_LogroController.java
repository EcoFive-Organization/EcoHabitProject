package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.Usuario_LogroDTO;
import pe.edu.upc.ecohabitproyecto.entities.Usuario_Logro;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IUsuario_LogroService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario-logros")
public class Usuario_LogroController {

    @Autowired
    private IUsuario_LogroService uLS;

    @GetMapping
    public List<Usuario_LogroDTO> listar() {
        return uLS.list().stream()
                .map(e -> new ModelMapper().map(e, Usuario_LogroDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody Usuario_LogroDTO dto) {
        Usuario_Logro e = new ModelMapper().map(dto, Usuario_Logro.class);
        uLS.insert(e);
    }

    @GetMapping("/{id}")
    public Usuario_LogroDTO listarId(@PathVariable int id) {
        Usuario_Logro e = uLS.listId(id);
        return new ModelMapper().map(e, Usuario_LogroDTO.class);
    }

    @PutMapping
    public void actualizar(@RequestBody Usuario_LogroDTO dto) {
        Usuario_Logro e = new ModelMapper().map(dto, Usuario_Logro.class);
        uLS.update(e);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        uLS.delete(id);
    }
}