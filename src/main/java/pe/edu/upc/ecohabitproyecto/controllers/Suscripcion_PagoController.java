package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.Suscripcion_PagoDTO;
import pe.edu.upc.ecohabitproyecto.entities.Suscripcion_Pago;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.ISuscripcion_PagoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/suscripciones_pagos")
public class Suscripcion_PagoController {
    @Autowired
    private ISuscripcion_PagoService spS;

    @GetMapping
    public List<Suscripcion_PagoDTO> listar(){
        return spS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, Suscripcion_PagoDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody Suscripcion_Pago s){
        ModelMapper m = new ModelMapper();
        Suscripcion_Pago suscripcion_pago = m.map(s, Suscripcion_Pago.class);
        spS.insert(suscripcion_pago);
    }
}
