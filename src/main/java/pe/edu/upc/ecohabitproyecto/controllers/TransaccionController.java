package pe.edu.upc.ecohabitproyecto.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.CantidadTransaccionesDTO;
import pe.edu.upc.ecohabitproyecto.dtos.MontoTransaccionesDTO;
import pe.edu.upc.ecohabitproyecto.dtos.SumTipoConsumoDTO;
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
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    public List<TransaccionDTO> listar(){
        return tS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,TransaccionDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
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

    @GetMapping("/DetallesTransaccionesPorTipo")
    public ResponseEntity<?> obtenerDetallesTransaccionesPorTipo() {
        List<CantidadTransaccionesDTO> listaDTO = new ArrayList<>();
        // Llama al nuevo método. Necesitarás implementarlo en tu servicio primero.
        List<Object[]> fila = tS.TransaccionesTotales();

        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron detalles de transacciones por tipo.");
        }

        for (Object[] columna : fila) {
            CantidadTransaccionesDTO dto = new CantidadTransaccionesDTO();
            dto.setTipo((String) columna[0]);
            dto.setMontoTotal((BigDecimal) columna[1]);
            dto.setCantidadTransacciones(((Number) columna[2]).longValue());
            dto.setCantidadBilleterasUnicas(((Number) columna[3]).longValue());
            listaDTO.add(dto);
        }

        return ResponseEntity.ok(listaDTO);
    }
    @GetMapping("/MontoTransaciones")
    public ResponseEntity<?> TransaccionesMonto() {
        List<MontoTransaccionesDTO> listaDTO = new ArrayList<>();
        List<Object[]> fila = tS.TransaccionesMonto();

        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron registros de consumo para contar.");
        }

        for (Object[] columna : fila) {
            MontoTransaccionesDTO dto = new MontoTransaccionesDTO();
            dto.setTipo((String) columna[0]);
            dto.setTotalmonto((BigDecimal) columna[1]);
            listaDTO.add(dto);
        }

        return ResponseEntity.ok(listaDTO);
    }
}
