package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.Metodo_PagoDTO;
import pe.edu.upc.ecohabitproyecto.dtos.UsuarioDTOInsert;
import pe.edu.upc.ecohabitproyecto.dtos.UsuarioDTOList;
import pe.edu.upc.ecohabitproyecto.entities.Metodo_Pago;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IMetodo_PagoService;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IUsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/metodos_pagos")
public class Metodo_PagoController {
    @Autowired
    private IMetodo_PagoService mS;

    @GetMapping
    public List<Metodo_PagoDTO> listar(){
        return mS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,Metodo_PagoDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody Metodo_PagoDTO l){
        ModelMapper m = new ModelMapper();
        Metodo_Pago metodo = m.map(l, Metodo_Pago.class);
        mS.insert(metodo);
    }
}
