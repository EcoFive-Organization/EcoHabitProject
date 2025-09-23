package pe.edu.upc.ecohabitproyecto.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.CantidadTransaccionesDTO;
import pe.edu.upc.ecohabitproyecto.dtos.TransaccionDTO;
import pe.edu.upc.ecohabitproyecto.entities.Billetera;
import pe.edu.upc.ecohabitproyecto.entities.Transaccion;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.ITransaccionService;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {
    @Autowired
    private ITransaccionService tS;

    @GetMapping
    public List<TransaccionDTO> listar(){
        return tS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,TransaccionDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody TransaccionDTO s){
        ModelMapper m = new ModelMapper();
        Transaccion tran=m.map(s, Transaccion.class);
        tS.insert(tran);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Transaccion tran = tS.listId(id);
        if (tran == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        TransaccionDTO dto = m.map(tran, TransaccionDTO.class);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Transaccion t = tS.listId(id);
        if (t == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        tS.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody TransaccionDTO dto) {
        ModelMapper m = new ModelMapper();
        Transaccion t = m.map(dto, Transaccion.class);
        Transaccion existente = tS.listId(t.getIdTransaccion());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + t.getIdTransaccion());
        }
        tS.update(t);
        return ResponseEntity.ok("Registro con ID " + t.getIdTransaccion() + " modificado correctamente.");
    }
    @GetMapping("/cantidades")
    public ResponseEntity<?> obtenerCantidadTransacciones() {

        List<CantidadTransaccionesDTO> listaDTO = new ArrayList<>();
        List<Object[]> fila = (List<Object[]>) tS.findAllByTransaccion();

        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron registros ");
        }

        for (Object[] columna : fila) {
            CantidadTransaccionesDTO dto = new CantidadTransaccionesDTO();

            dto.setIdTransaccion(((Number) columna[0]).intValue());
            dto.setBilletera((Billetera) columna[1]);
            dto.setMonto(BigDecimal.valueOf(((Number) columna[2]).floatValue()));
            dto.setFecha(Timestamp.valueOf(columna[3].toString()));

            listaDTO.add(dto);
        }

        return ResponseEntity.ok(listaDTO);
    }
}
