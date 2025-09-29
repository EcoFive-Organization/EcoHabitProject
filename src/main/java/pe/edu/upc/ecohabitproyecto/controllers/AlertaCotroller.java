package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.AlertaDTO;
import pe.edu.upc.ecohabitproyecto.entities.Alerta;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IAlertaService;

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

}
