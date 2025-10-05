package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.LogroDTO;
import pe.edu.upc.ecohabitproyecto.dtos.LogroDesbloqueadoDTO;
import pe.edu.upc.ecohabitproyecto.entities.Logro;
import pe.edu.upc.ecohabitproyecto.entities.UsuarioLogro;
import pe.edu.upc.ecohabitproyecto.repositories.IUsuarioLogroRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.ILogroService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/logros")
public class LogroController {

    @Autowired
    private ILogroService lS;

    @Autowired
    private IUsuarioLogroRepository userLogroRepo;


    @GetMapping
    public List<LogroDTO> listar() {
        return lS.list().stream()
                .map(e -> new ModelMapper().map(e, LogroDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody LogroDTO dto) {
        Logro e = new ModelMapper().map(dto, Logro.class);
        lS.insert(e);
    }

    @GetMapping("/{id}")
    public LogroDTO listarId(@PathVariable int id) {
        Logro e = lS.listId(id);
        return new ModelMapper().map(e, LogroDTO.class);
    }

    @PutMapping
    public void actualizar(@RequestBody LogroDTO dto) {
        Logro e = new ModelMapper().map(dto, Logro.class);
        lS.update(e);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        lS.delete(id);
    }


    @PostMapping("/desbloquear")
    public ResponseEntity<LogroDesbloqueadoDTO> desbloquearLogro(
            @RequestParam int idUsuario,
            @RequestParam int idLogro) {

        LogroDesbloqueadoDTO dto = lS.desbloquearLogro(idUsuario, idLogro);
        return ResponseEntity.ok(dto);
    }


    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<LogroDesbloqueadoDTO>> listarLogrosUsuario(@PathVariable int idUsuario) {
        List<UsuarioLogro> usuarioLogros = userLogroRepo.findByUsuario_IdUsuario(idUsuario);

        List<LogroDesbloqueadoDTO> response = usuarioLogros.stream().map(ul ->
                new LogroDesbloqueadoDTO(
                        "Logro desbloqueado previamente",
                        ul.getLogro().getNombre(),
                        ul.getLogro().getRecompensa() != null ? ul.getLogro().getRecompensa().getNombre() : "N/A",
                        null // no devolvemos saldo en historial
                )
        ).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}