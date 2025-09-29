package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.BilleteraDTO;
import pe.edu.upc.ecohabitproyecto.entities.Billetera;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IBilleteraService;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/billeteras")
public class BilleteraController {
    @Autowired
    private IBilleteraService bS;

    @GetMapping
    public List<BilleteraDTO>listar(){
        return bS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,BilleteraDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody BilleteraDTO s){
        ModelMapper m = new ModelMapper();
        Billetera bill=m.map(s, Billetera.class);
        bS.insert(bill);
    }
}
