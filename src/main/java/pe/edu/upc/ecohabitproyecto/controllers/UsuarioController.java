package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.UsuarioRolCountDTO;
import pe.edu.upc.ecohabitproyecto.dtos.UsuarioDTOList;
import pe.edu.upc.ecohabitproyecto.dtos.UsuarioRolStatusDTO;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IUsuarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private IUsuarioService uS;

    @GetMapping
    public List<UsuarioDTOList> listar(){
        return uS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,UsuarioDTOList.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Usuario u = uS.listId(id);
        if (u == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id + " para eliminar.");
        }
        uS.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    // Listar por cantidad de Usuarios según el rol
    @GetMapping("/roles")
    public ResponseEntity<?> obtenerUsuariosRoles() {
        List<UsuarioRolCountDTO> listaDTO = new ArrayList<>();
        List<String[]> fila = uS.getUsuariosRol(); // aqui están las cantidades

        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron registros.");
        }

        for(String[] columna : fila) {
            UsuarioRolCountDTO dto = new UsuarioRolCountDTO();
            dto.setNombreRol(columna[0]);
            dto.setTotalUsuarios(Long.valueOf(columna[1]));
            listaDTO.add(dto);
        }

        return ResponseEntity.ok(listaDTO);

    }

    // Listar por cantidad de Usuarios, estado y rol
    @GetMapping("/estados_roles")
    public ResponseEntity<?> obtenerVideos() {
        List<UsuarioRolStatusDTO> listaDTO = new ArrayList<>();
        List<String[]> fila = uS.getUsuariosEstadoRol(); // aqui están los datos

        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron registros.");
        }

        for(String[] columna : fila) {
            UsuarioRolStatusDTO dto = new UsuarioRolStatusDTO();
            dto.setNombreRol(columna[0]);
            dto.setEnabled(Boolean.valueOf(columna[1]));
            dto.setTotalUsuarios(Long.valueOf(columna[2]));
            listaDTO.add(dto);
        }

        return ResponseEntity.ok(listaDTO);

    }
}
