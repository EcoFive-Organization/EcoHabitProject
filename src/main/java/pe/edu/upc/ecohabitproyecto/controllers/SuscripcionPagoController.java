package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.SuscripcionPagoDTO;
import pe.edu.upc.ecohabitproyecto.entities.SuscripcionPago;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.ISuscripcionPagoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/suscripciones_pagos")
public class SuscripcionPagoController {
    @Autowired
    private ISuscripcionPagoService spS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    public List<SuscripcionPagoDTO> listar(){
        return spS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, SuscripcionPagoDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    public void insertar(@RequestBody SuscripcionPago s){
        ModelMapper m = new ModelMapper();
        SuscripcionPago suscripcion_pago = m.map(s, SuscripcionPago.class);
        spS.insert(suscripcion_pago);
    }
}
