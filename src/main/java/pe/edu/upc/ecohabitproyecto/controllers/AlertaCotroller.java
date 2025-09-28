package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.AlertaDTO;
import pe.edu.upc.ecohabitproyecto.dtos.CantidadAlarmasDTO;
import pe.edu.upc.ecohabitproyecto.entities.Alerta;
import pe.edu.upc.ecohabitproyecto.entities.Consumo;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IAlertaService;

import java.sql.Timestamp;
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
    @GetMapping("/CantidadAlerta")
    public ResponseEntity<?> obtenerAlertas() {
        List<CantidadAlarmasDTO> listaDTO = new ArrayList<>();
        List<Object[]> fila = aS.findAllByIdAlerta(); // 👈 tu repositorio

        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron alertas");
        }

        for (Object[] columna : fila) {
            CantidadAlarmasDTO dto = new CantidadAlarmasDTO();

            dto.setIdAlerta(((Number) columna[0]).intValue());
            dto.setFecha(Timestamp.valueOf(columna[1].toString()));
            dto.setMensaje((String) columna[2]);
            dto.setConsumo((Consumo) columna[3]);
            listaDTO.add(dto);
        }

        return ResponseEntity.ok(listaDTO);
    }

}
