package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.MetodoPagoDTO;
import pe.edu.upc.ecohabitproyecto.entities.MetodoPago;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IMetodoPagoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/metodos_pagos")
public class MetodoPagoController {
    @Autowired
    private IMetodoPagoService mS;

    @GetMapping
    public List<MetodoPagoDTO> listar(){
        return mS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, MetodoPagoDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody MetodoPagoDTO l){
        ModelMapper m = new ModelMapper();
        MetodoPago metodo = m.map(l, MetodoPago.class);
        mS.insert(metodo);
    }
}
