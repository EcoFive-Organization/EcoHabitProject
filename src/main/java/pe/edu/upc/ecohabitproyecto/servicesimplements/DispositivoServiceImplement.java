package pe.edu.upc.ecohabitproyecto.servicesimplements;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Dispositivo;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;
import pe.edu.upc.ecohabitproyecto.repositories.IDispositivoRepository;
import pe.edu.upc.ecohabitproyecto.repositories.IUsuarioRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IDispositivoService;

import java.time.LocalDate;
import java.util.List;

@Service
public class DispositivoServiceImplement implements IDispositivoService {

    @Autowired
    private IDispositivoRepository dR;

    @Autowired
    private IUsuarioRepository uR;

    @Override
    public List<Dispositivo> list() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"));

        if (isAdmin) {
            return dR.findAllWithUsuario();
        } else {
            // 1. Buscamos el usuario logueado por su username único (el del login)
            // IMPORTANTE: Tu columna de login (nombre o email) DEBE ser única en BD.
            Usuario u = uR.findByNombre(username).orElse(null);

            if (u == null) {
                return List.of(); // O lanzar excepción
            }

            // 2. Usamos su ID para buscar los dispositivos de forma segura
            return dR.findByUsuarioId(u.getIdUsuario());
        }
    }

    @Override
    @Transactional
    public void insert(Dispositivo dispositivo) {
        // 1. Obtener quién está haciendo la petición (del Token)
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        // 2. Verificar Rol
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"));

        if (isAdmin) {
            // LÓGICA ADMIN:
            // El admin sí manda el ID del usuario desde el front (del select).
            // Validamos que no venga vacío.
            if (dispositivo.getUsuario() == null || dispositivo.getUsuario().getIdUsuario() == 0) {
                throw new RuntimeException("Error: El administrador debe seleccionar un usuario.");
            }
            // Opcional: Podrías buscar el usuario en BD para asegurar que existe,
            // pero si confías en el ID del front, Hibernate lo intentará vincular.

        } else {
            // LÓGICA CLIENT (AQUÍ ESTABA EL ERROR):
            // El cliente manda idUsuario=0 porque el campo está oculto.
            // NOSOTROS debemos buscar su usuario real en la BD y asignarlo.

            Usuario usuarioReal = uR.findByNombre(username).orElse(null); // Buscamos por el nombre del token

            if (usuarioReal == null) {
                throw new RuntimeException("Error de seguridad: No se encontró el usuario logueado.");
            }

            // Asignamos el usuario real al dispositivo
            dispositivo.setUsuario(usuarioReal);
        }

        // Guardar
        dR.save(dispositivo);
    }

    // Metodo actualizado
    @Override
    public Dispositivo listId(int id) {
        return dR.findWithUsuarioById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        dR.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Dispositivo dispositivo) {
        // 1. Recuperamos el registro original de la BD
        Dispositivo dispositivoOriginal = dR.findById(dispositivo.getIdDispositivo())
                .orElseThrow(() -> new RuntimeException("Dispositivo no encontrado para actualizar"));

        // 2. Actualizamos los campos editables
        dispositivoOriginal.setNombre(dispositivo.getNombre());
        dispositivoOriginal.setTipo(dispositivo.getTipo());
        dispositivoOriginal.setUbicacion(dispositivo.getUbicacion());
        // La fecha de registro NO se debería cambiar en una edición, se mantiene la original

        // 3. Verificación de Seguridad (Opcional pero recomendada)
        // Si es CLIENT, verificamos que no esté intentando editar un dispositivo de otro
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"));

        if (!isAdmin) {
            // Si es cliente, verificamos que el dispositivo le pertenezca
            if (!dispositivoOriginal.getUsuario().getNombre().equals(username)) { // O comparar IDs
                throw new RuntimeException("No tienes permiso para editar este dispositivo");
            }
        } else {
            // Si es ADMIN y quiere cambiar el dueño (reasignar), aquí sí lo permitimos
            // Pero si el objeto viene con usuario null/0, mantenemos el original
            if (dispositivo.getUsuario() != null && dispositivo.getUsuario().getIdUsuario() != 0) {
                // Buscar usuario nuevo si se quiere cambiar
                Usuario nuevoDuenio = uR.findById(dispositivo.getUsuario().getIdUsuario()).orElse(dispositivoOriginal.getUsuario());
                dispositivoOriginal.setUsuario(nuevoDuenio);
            }
        }

        // 4. Guardamos el objeto ORIGINAL modificado (que ya tiene el ID de usuario correcto)
        dR.save(dispositivoOriginal);
    }

    @Override
    public List<String[]> getByTipo(String tipo_parametro) {
        return dR.getByTipo(tipo_parametro);
    }


}
