package pe.edu.upc.ecohabitproyecto.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.*;
import pe.edu.upc.ecohabitproyecto.entities.Consumo;
import pe.edu.upc.ecohabitproyecto.entities.Dispositivo;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IConsumoService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/consumos")
public class ConsumoController {

    @Autowired
    private IConsumoService cS;

    @GetMapping
    public List<ConsumoDTO> listar(){
        return cS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
                return m.map(x,ConsumoDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody ConsumoDTO s){
        ModelMapper m = new ModelMapper();
        Consumo cons=m.map(s, Consumo.class);
        cS.insert(cons);
    }
    @GetMapping("/CantidadPorTipoConsumo")
    public ResponseEntity<?> obtenerCantidadPorTipoConsumo() {
        List<CantidadConsumoDTO> listaDTO = new ArrayList<>();
        List<Object[]> fila = cS.findAllByTipoConsumo(); // Llama al nuevo método del servicio

        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron registros de consumo para contar.");
        }

        for (Object[] columna : fila) {
            CantidadConsumoDTO dto = new CantidadConsumoDTO();
            dto.setTipo((String) columna[0]);
            dto.setCantidad(((Number) columna[1]).longValue());
            listaDTO.add(dto);
        }

        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/TipoConsumo")
    public ResponseEntity<?> getByTotalConsumoTipo(@RequestParam String tipoConsumo) {
        List<SumTipoConsumoDTO> listaDTO = new ArrayList<>();
        List<Object[]> fila = cS.getByTotalConsumoTipo(tipoConsumo);

        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron registros de consumo para contar.");
        }

        for (Object[] columna : fila) {
            SumTipoConsumoDTO dto = new SumTipoConsumoDTO();
            dto.setTipo((String) columna[0]);
            dto.setTotalConsumo((BigDecimal) columna[1]);
            listaDTO.add(dto);
        }

        return ResponseEntity.ok(listaDTO);
    }
    @GetMapping("/CantidadConsumoDisp")
    public ResponseEntity<?> getConsumoByDispositivo() {
        List<CantConsumoDispDTO> listaDTO = new ArrayList<>();
        List<Object[]> fila = cS.getConsumoByDispositivo(); // Llama al nuevo método del servicio

        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron registros de consumo para contar.");
        }

        for (Object[] columna : fila) {
            CantConsumoDispDTO dto = new CantConsumoDispDTO();
            dto.setId_consumo(((Number) columna[0]).intValue());
            int idDispositivo = ((Number) columna[1]).intValue();
            Dispositivo tempDisp = new Dispositivo();
            tempDisp.setIdDispositivo(idDispositivo);
            dto.setDispositivo(tempDisp);
            dto.setValor((BigDecimal) columna[2]);
            listaDTO.add(dto);
        }

        return ResponseEntity.ok(listaDTO);
    }

}
