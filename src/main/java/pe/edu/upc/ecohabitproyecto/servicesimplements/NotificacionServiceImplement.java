package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Logro;
import pe.edu.upc.ecohabitproyecto.entities.Notificacion;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;
import pe.edu.upc.ecohabitproyecto.repositories.ILogroRepository;
import pe.edu.upc.ecohabitproyecto.repositories.INotificacionRepository;
import pe.edu.upc.ecohabitproyecto.repositories.IUsuarioRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.INotificacionService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacionServiceImplement implements INotificacionService {

    @Autowired
    private ILogroRepository logroRepo;

    @Autowired
    private IUsuarioRepository usuarioRepo;

    @Autowired
    private INotificacionRepository notificacionRepo;

    @Override
    public void notificarLogroAmigo(Integer idLogro) {
        Logro logro = logroRepo.findById(idLogro)
                .orElseThrow(() -> new RuntimeException("Logro no encontrado"));

        Usuario autorDelLogro = logro.getUsuario();
        if (autorDelLogro == null) {
            throw new RuntimeException("El logro no tiene usuario asociado");
        }

        List<Usuario> amigos = usuarioRepo.findAmigosDe(autorDelLogro.getIdUsuario());
        if (amigos == null || amigos.isEmpty()) {
            return; // No hay amigos, no se envía nada
        }

        String nombreLogro = logro.getNombre() != null ? logro.getNombre() : "un logro";
        String nombreAutor = autorDelLogro.getNombre() != null ? autorDelLogro.getNombre() : "Tu amigo";

        for (Usuario amigo : amigos) {
            String mensaje = String.format("¡Felicidades! %s ha desbloqueado el logro '%s'",
                    nombreAutor, nombreLogro);

            Notificacion notificacion = new Notificacion();
            notificacion.setUsuario(amigo);
            notificacion.setMensaje(mensaje);
            notificacion.setTipo("LOGRO_AMIGO");
            notificacion.setFecha(LocalDateTime.now());

            notificacionRepo.save(notificacion);


        }
    }
}