package pe.edu.upc.ecohabitproyecto.servicesimplements;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.PasswordResetToken;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;
import pe.edu.upc.ecohabitproyecto.repositories.IPasswordResetTokenRepository;
import pe.edu.upc.ecohabitproyecto.repositories.IUsuarioRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IUsuarioService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioServiceImplement implements IUsuarioService {
    @Autowired //Notificar que va a usar dos metodos de los muchos que tiene
    private IUsuarioRepository uR;
    @Autowired private IPasswordResetTokenRepository tokenRepository;

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

    @Override
    public List<String[]> getUsuariosRol() {
        return uR.contarUsuariosRol();
    }

    @Override
    public List<String[]> getUsuariosEstadoRol() {
        return uR.contarUsuariosEstadoRol();
    }

    @Override
    @Transactional // Necesario para ejecutar deleteByUsuario en el repositorio
    public String createPasswordResetToken(String email) {

        // Asumimos que IUsuarioRepository tiene un metodo findByEmail(String email)
        Usuario usuario = uR.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("404: Usuario no encontrado con ese email."));

        // 1. Eliminar tokens viejos asociados a este usuario
        tokenRepository.deleteByUsuario(usuario);

        // 2. Crear y guardar el nuevo token
        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken(token, usuario);
        tokenRepository.save(resetToken);

        // 3. SIMULACIÓN DE ENVÍO DE EMAIL
        // Aquí iría el código real para enviar el email con el enlace.

        return token; // Retornamos el token para fines de prueba en Postman
    }

    @Override
    @Transactional
    public void resetPassword(String token, String newPassword) {

        // 1. Buscar y validar la existencia del token
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("404: Token inválido o expirado."));

        // 2. Validar expiración
        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            tokenRepository.delete(resetToken);
            throw new RuntimeException("400: El token ha expirado. Por favor, solicite uno nuevo.");
        }

        // 3. Validar longitud/seguridad de newPassword aquí si es necesario
        if (newPassword == null || newPassword.length() < 5) {
            throw new RuntimeException("400: La contraseña debe tener al menos 8 caracteres.");
        }

        // 4. Actualizar y encriptar la contraseña del usuario
        Usuario usuario = resetToken.getUsuario();
        usuario.setPasswordHash(passwordEncoder.encode(newPassword));
        uR.save(usuario);

        // 5. Invalidar el token (ya no se puede usar)
        tokenRepository.delete(resetToken);
    }

}
