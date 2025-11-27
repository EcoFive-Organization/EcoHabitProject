package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.ecohabitproyecto.dtos.PlanSuscripcionDTO;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IPlanSuscripcionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/plan-suscripcion")
public class PlanSuscripcionController {
    @Autowired
    private IPlanSuscripcionService psS;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    public List<PlanSuscripcionDTO> listar() {
        return psS.list().stream()
                .map(e -> new ModelMapper().map(e, PlanSuscripcionDTO.class))
                .collect(Collectors.toList());
    }
}
