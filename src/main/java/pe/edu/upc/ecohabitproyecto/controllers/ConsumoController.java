package pe.edu.upc.ecohabitproyecto.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.CantConsumoDispDTO;
import pe.edu.upc.ecohabitproyecto.dtos.CantidadConsumoDTO;
import pe.edu.upc.ecohabitproyecto.dtos.ConsumoDTO;
import pe.edu.upc.ecohabitproyecto.entities.Consumo;
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

    @GetMapping("/ConsumoTotalPorDispositivo")
    public ResponseEntity<?> getConsumoTotalPorDispositivo() {
        List<CantConsumoDispDTO> listaDTO = new ArrayList<>();
        List<Object[]> fila = cS.getConsumoTotalByDispositivo();

        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró consumo total por dispositivo.");
        }

        for (Object[] columna : fila) {
            CantConsumoDispDTO dto = new CantConsumoDispDTO();

            // Mapeo de las nuevas columnas:
            // Columna 0: ID del Dispositivo (Integer)
            dto.setIdDispositivo(((Number) columna[0]).intValue());
            // Columna 1: ID del Usuario (Integer)
            dto.setIdUsuario(((Number) columna[1]).intValue());
            // Columna 2: Nombre del dispositivo (String)
            dto.setNombreDispositivo((String) columna[2]);
            // Columna 3: Consumo total (BigDecimal)
            dto.setTotalConsumo((BigDecimal) columna[3]);

            listaDTO.add(dto);
        }

        return ResponseEntity.ok(listaDTO);
    }
}
