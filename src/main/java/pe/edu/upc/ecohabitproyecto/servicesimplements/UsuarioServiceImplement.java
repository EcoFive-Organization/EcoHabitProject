package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;
import pe.edu.upc.ecohabitproyecto.repositories.IUsuarioRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IUsuarioService;

import java.util.List;

@Service
public class UsuarioServiceImplement implements IUsuarioService {
    @Autowired //Notificar que va a usar dos metodos de los muchos que tiene
    private IUsuarioRepository uR;

    @Override
    public List<Usuario> list() {
        return uR.findAll();
    }

    @Override
    public Usuario listId(int id) {
        return uR.findById(id).orElse(null);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario insert(Usuario newUsuario) {
        // Validación: verificar si el usuario ya existe por su correo
        Usuario existingUser = uR.findOneByEmail(newUsuario.getEmail());
        if (existingUser != null) {
            // Lanza una excepción si el usuario ya existe
            throw new IllegalArgumentException("Ya existe un usuario con este correo.");
        }

        // Encriptar la contraseña antes de guardarla en la base de datos
        String encodedPassword = passwordEncoder.encode(newUsuario.getPasswordHash());
        newUsuario.setPasswordHash(encodedPassword);

        // Guardar el nuevo usuario en el repositorio
        return uR.save(newUsuario);
    }

    @Override
    public void delete(int id) {
        uR.deleteById(id);
    }

}
