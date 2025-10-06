package pe.edu.upc.ecohabitproyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.INotificacionService;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired
    private INotificacionService notificacionService;

    @PostMapping("/logro")
    public void notificarLogro(@RequestParam Integer idLogro) {
        notificacionService.notificarLogroAmigo(idLogro);
    }
}