package pe.edu.upc.ecohabitproyecto.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.CantidadConsumoDTO;
import pe.edu.upc.ecohabitproyecto.dtos.DispositivoDTOInsert;
import pe.edu.upc.ecohabitproyecto.dtos.DispositivoDTOList;
import pe.edu.upc.ecohabitproyecto.dtos.TipoDispositivoListDTO;
import pe.edu.upc.ecohabitproyecto.entities.Dispositivo;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IDispositivoService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
//@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/dispositivos")
public class DispositivoController {
    @Autowired
    private IDispositivoService dS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    public List<DispositivoDTOList> listar(){
        return dS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,DispositivoDTOList.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    public void insertar(@RequestBody DispositivoDTOInsert s){
        ModelMapper m = new ModelMapper();
        Dispositivo disp=m.map(s, Dispositivo.class);
        dS.insert(disp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Dispositivo disp = dS.listId(id);
        if (disp == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        DispositivoDTOInsert dto = m.map(disp, DispositivoDTOInsert.class);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Dispositivo d = dS.listId(id);
        if (d == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        dS.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody DispositivoDTOInsert dto) {
        ModelMapper m = new ModelMapper();
        Dispositivo d = m.map(dto, Dispositivo.class);
        Dispositivo existente = dS.listId(d.getIdDispositivo());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + d.getIdDispositivo());
        }
        dS.update(d);
        return ResponseEntity.ok("Registro con ID " + d.getIdDispositivo() + " modificado correctamente.");
    }
    @GetMapping("/TipoDispositivoLista")
    public ResponseEntity<?> getTipoDispositivoLista(@RequestParam String tipo_dispositivo) {
        List<TipoDispositivoListDTO> listaDTO = new ArrayList<>();
        List<String[]> fila = dS.getByTipo(tipo_dispositivo); // Llama al nuevo método del servicio

        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron registros de consumo para contar.");
        }

        for (Object[] columna : fila) {
            TipoDispositivoListDTO dto = new TipoDispositivoListDTO();
            dto.setTipo((String) columna[0]);
            dto.setNombre((String) columna[1]);
            listaDTO.add(dto);
        }

        return ResponseEntity.ok(listaDTO);
    }

}
