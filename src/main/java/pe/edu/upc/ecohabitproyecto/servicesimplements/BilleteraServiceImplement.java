package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.ecohabitproyecto.entities.Billetera;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;
import pe.edu.upc.ecohabitproyecto.repositories.IBilleteraRepository;
import pe.edu.upc.ecohabitproyecto.repositories.IUsuarioRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IBilleteraService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BilleteraServiceImplement implements IBilleteraService {

    @Autowired
    private IBilleteraRepository bR;

    @Autowired
    private IUsuarioRepository uR;


    @Override
    public List<Billetera> list() {
        // Lógica de visualización según Rol
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"));

        if (isAdmin) {
            return bR.findAll();
        } else {
            Usuario u = uR.findByNombre(username).orElse(null);
            if (u == null) return Collections.emptyList();
            return bR.findByUsuarioIdUsuario(u.getIdUsuario())
                    .map(List::of)
                    .orElse(Collections.emptyList());
        }
    }

    @Override
    @Transactional
    public void insert(Billetera billetera) {
        // 1. Obtener quién hace la petición
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"));

        Usuario usuarioAsignar = null;

        if (isAdmin) {
            // LÓGICA ADMIN: Debe venir el ID del usuario en el objeto
            if (billetera.getUsuario() == null || billetera.getUsuario().getIdUsuario() == 0) {
                throw new RuntimeException("Error: El administrador debe seleccionar un usuario.");
            }
            // Buscamos ese usuario para asegurar que existe
            usuarioAsignar = uR.findById(billetera.getUsuario().getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("El usuario seleccionado no existe."));

        } else {
            // LÓGICA CLIENT: Ignoramos el ID 0 del front y buscamos al dueño del token
            usuarioAsignar = uR.findByNombre(username).orElse(null);

            if (usuarioAsignar == null) {
                throw new RuntimeException("Error de seguridad: Usuario no encontrado.");
            }
        }

        // 2. Validación de Unicidad (1 Usuario = 1 Billetera)
        Optional<Billetera> existente = bR.findByUsuarioIdUsuario(usuarioAsignar.getIdUsuario());
        if (existente.isPresent()) {
            throw new RuntimeException("Este usuario ya tiene una billetera registrada.");
        }

        // 3. Asignación Final y Guardado
        billetera.setUsuario(usuarioAsignar); // 🔒 Aquí corregimos el ID 0

        // Inicializamos saldo si viene nulo
        if (billetera.getSaldo() == null) {
            billetera.setSaldo(BigDecimal.ZERO);
        }

        bR.save(billetera);
    }

    @Override
    public BigDecimal getSaldoPuntos(int idUsuario) {
        return bR.findByUsuarioIdUsuario(idUsuario)
                .map(Billetera::getSaldo)
                .orElse(BigDecimal.ZERO);
    }


    @Override
    public void acumularPuntos(Integer idUsuario, Double puntos) {
        Billetera billetera = bR.findByUsuarioIdUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("Billetera no encontrada"));

        billetera.setSaldo(
                billetera.getSaldo().add(BigDecimal.valueOf(puntos))
        );

        bR.save(billetera);
    }

    @Override
    public Billetera obtenerBilleteraPorUsuario(Integer idUsuario) {
        return bR.findByUsuarioIdUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("Billetera no encontrada"));
    }
}
