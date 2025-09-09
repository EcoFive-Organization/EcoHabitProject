package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.Social_PlataformasDTO;
import pe.edu.upc.ecohabitproyecto.entities.Social_Plataformas;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.ISocial_PlataformasService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/social_plataformas")
public class Social_PlataformasController {

    private final ISocial_PlataformasService lS;

    public Social_PlataformasController(ISocial_PlataformasService lS) {
        this.lS = lS;
    }

    @GetMapping
    public List<Social_PlataformasDTO> listar() {
        return lS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, Social_PlataformasDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Social_PlataformasDTO> insertar(@RequestBody Social_PlataformasDTO dto) {
        ModelMapper m = new ModelMapper();

        Social_Plataformas entity = m.map(dto, Social_Plataformas.class);
        entity.setPlataforma_id(null);

        Social_Plataformas saved = lS.insert(entity);

        Social_PlataformasDTO resp = m.map(saved, Social_PlataformasDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }
}