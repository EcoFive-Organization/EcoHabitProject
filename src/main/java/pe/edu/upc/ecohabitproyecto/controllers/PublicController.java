package pe.edu.upc.ecohabitproyecto.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class PublicController {

    @GetMapping("/")
    public String home(){
        return "EcoHabit backend activo";
    }

    @GetMapping("/status")
    public Map<String,String> status(){
        return Map.of(
                "estado", "activo",
                "servicio", "EcoHabit",
                "timestamp", LocalDateTime.now().toString()
        );
    }

}
