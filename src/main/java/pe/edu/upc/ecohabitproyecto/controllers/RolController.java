package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.QuantityPostForumDTO;
import pe.edu.upc.ecohabitproyecto.dtos.QuantityUserByRolDTO;
import pe.edu.upc.ecohabitproyecto.dtos.RolDTO;
import pe.edu.upc.ecohabitproyecto.entities.Rol;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IRolService;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RolController {
    @Autowired // Para traer la clase del service
    private IRolService rS;

    @GetMapping
    public List<RolDTO> listar() {
        return rS.list().stream().map(x -> {
            ModelMapper mapper = new ModelMapper();
            return mapper.map(x, RolDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody RolDTO rolDTO) {

        ModelMapper mapper = new ModelMapper();
        Rol rol = mapper.map(rolDTO, Rol.class);
        rS.insert(rol);
    }

    // Cantidad de usuarios según rol
    @GetMapping("/cantidades")
    public ResponseEntity<?> obtenerCantidadUsuarios() {
        List<QuantityUserByRolDTO> listaDTO = new ArrayList<>();
        List<String[]> fila = rS.quantityUserByRol(); // aqui están las cantidades

        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron registros.");
        }

        for(String[] columna : fila) {
            QuantityUserByRolDTO dto = new QuantityUserByRolDTO();
            dto.setNameRol(columna[0]); // primera columna "Nombre foro"
            dto.setQuantityUsers(Integer.parseInt(columna[1])); // segunda columna "Cantidad Publicaciones"
            listaDTO.add(dto);
        }

        return ResponseEntity.ok(listaDTO);

    }

}