package pe.edu.upc.ecohabitproyecto.servicesimplements;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.dtos.MetodoPagoDTO;
import pe.edu.upc.ecohabitproyecto.entities.MetodoPago;
import pe.edu.upc.ecohabitproyecto.entities.PasswordResetToken;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;
import pe.edu.upc.ecohabitproyecto.repositories.IMetodoPagoRepository;
import pe.edu.upc.ecohabitproyecto.repositories.IPasswordResetTokenRepository;
import pe.edu.upc.ecohabitproyecto.repositories.IUsuarioRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IUsuarioService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioServiceImplement implements IUsuarioService {

    @Autowired
    private IUsuarioRepository uR;

    @Autowired
    private IPasswordResetTokenRepository tokenRepository;

    @Autowired
    private IMetodoPagoRepository metodoPagoRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Usuario> list() {
        return uR.findAll();
    }

    @Override
    public Usuario listId(int id) {
        return uR.findById(id).orElse(null);
    }

    @Override
    public Usuario insert(Usuario newUsuario) {
        // Validación: verificar si el usuario ya existe por su correo
        Usuario existingUser = uR.findOneByEmail(newUsuario.getEmail());
        if (existingUser != null) {
            throw new IllegalArgumentException("Ya existe un usuario con este correo.");
        }

        // Encriptar la contraseña antes de guardarla
        String encodedPassword = passwordEncoder.encode(newUsuario.getPasswordHash());
        newUsuario.setPasswordHash(encodedPassword);

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
    @Transactional
    public String createPasswordResetToken(String email) {
        Usuario usuario = uR.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("404: Usuario no encontrado con ese email."));

        tokenRepository.deleteByUsuario(usuario);

        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken(token, usuario);
        tokenRepository.save(resetToken);

        return token; // Simulación de envío de email
    }

    @Override
    @Transactional
    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("404: Token inválido o expirado."));

        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            tokenRepository.delete(resetToken);
            throw new RuntimeException("400: El token ha expirado. Por favor, solicite uno nuevo.");
        }

        if (newPassword == null || newPassword.length() < 8) {
            throw new RuntimeException("400: La contraseña debe tener al menos 8 caracteres.");
        }

        Usuario usuario = resetToken.getUsuario();
        usuario.setPasswordHash(passwordEncoder.encode(newPassword));
        uR.save(usuario);

        tokenRepository.delete(resetToken);
    }



}