package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.AlertaDTO;
import pe.edu.upc.ecohabitproyecto.dtos.AlertaIrregularDTO;
import pe.edu.upc.ecohabitproyecto.dtos.SumTipoConsumoDTO;
import pe.edu.upc.ecohabitproyecto.dtos.TipoAlertaDTO;
import pe.edu.upc.ecohabitproyecto.entities.Alerta;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IAlertaService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//Entrar a Swagger: http://localhost:8080/swagger-ui/index.html#/

@RestController
@RequestMapping("/alertas")
public class AlertaCotroller {

    @Autowired
    private IAlertaService aS;

    @GetMapping
    public List<AlertaDTO> listar(){
        return aS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, AlertaDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody AlertaDTO s){
        ModelMapper m = new ModelMapper();
        Alerta aler=m.map(s, Alerta.class);
        aS.insert(aler);
    }
    @GetMapping("/TipoAlerta")
    public ResponseEntity<?> getByTipoAlerta(@RequestParam String tipoAlerta) {
        List<TipoAlertaDTO> listaDTO = new ArrayList<>();
        List<Object[]> fila = aS.getByTipoAlerta(tipoAlerta);

        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron registros de consumo para contar.");
        }

        for (Object[] columna : fila) {
            TipoAlertaDTO dto = new TipoAlertaDTO();
            dto.setIdAlerta((Integer) columna[0]);
            dto.setTipoAlerta((String) columna[1]);
            listaDTO.add(dto);
        }

        return ResponseEntity.ok(listaDTO);
    }
    @GetMapping("/AlertaIrregular")
    public ResponseEntity<?> getByTipoIrregular(@RequestParam String tipoAlerta) {
        List<AlertaIrregularDTO> listaDTO = new ArrayList<>();
        List<Object[]> fila = aS.getByTipoIrregular(tipoAlerta);

        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron registros de consumo para contar.");
        }

        for (Object[] columna : fila) {
            AlertaIrregularDTO dto = new AlertaIrregularDTO();
            dto.setIdAlerta((Integer) columna[0]);
            dto.setTipoAlerta((String) columna[1]);
            dto.setMensaje((String) columna[2]);
            listaDTO.add(dto);
        }
        return ResponseEntity.ok(listaDTO);
    }
}
