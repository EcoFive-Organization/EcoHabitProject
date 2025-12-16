package pe.edu.upc.ecohabitproyecto.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.ecohabitproyecto.dtos.JwtRequestDTO;
import pe.edu.upc.ecohabitproyecto.dtos.JwtResponseDTO;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;
import pe.edu.upc.ecohabitproyecto.repositories.IUsuarioRepository;
import pe.edu.upc.ecohabitproyecto.securities.JwtTokenUtil;
import pe.edu.upc.ecohabitproyecto.securities.TokenBlacklistService;
import pe.edu.upc.ecohabitproyecto.servicesimplements.JwtUserDetailsService;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IUsuarioService;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    // Dependencias agregadas para la funcionalidad de registro
    @Autowired
    private IUsuarioService uS;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;


    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody JwtRequestDTO req) throws Exception {
        authenticate(req.getUsername(), req.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());

        // 1. PRIMERO: Buscamos al usuario completo en la BD para tener su ID disponible
        Usuario usuario = usuarioRepository.findByNombre(req.getUsername())
                .orElseThrow(() -> new Exception("Usuario no encontrado en base de datos"));

        // 2. SEGUNDO: Generamos el token pasando el userDetails Y el ID del usuario
        // Nota cómo ahora pasamos 'usuario.getIdUsuario()' al metodo
        final String token = jwtTokenUtil.generateToken(userDetails, usuario.getIdUsuario().longValue());

        // 3. Devolvemos el token (que ahora lleva el ID encriptado dentro) y también el ID suelto por si acaso
        return ResponseEntity.ok(new JwtResponseDTO(token, usuario.getIdUsuario()));
    }

    // --- NUEVO: Endpoint para cerrar sesión (Logout) ---
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        // Obtenemos el token del header Authorization
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Quitamos el "Bearer "

            try {
                // Calculamos cuánto tiempo le queda de vida al token
                Date expirationDate = jwtTokenUtil.getExpirationDateFromToken(token);
                long now = System.currentTimeMillis();
                long timeRemaining = expirationDate.getTime() - now;

                if (timeRemaining > 0) {
                    // Si el token aún es válido, lo mandamos a la lista negra en Redis
                    tokenBlacklistService.blacklistToken(token, Duration.ofMillis(timeRemaining));
                    return ResponseEntity.ok("Cierre de sesión exitoso. Token invalidado.");
                } else {
                    return ResponseEntity.ok("El token ya había expirado, cierre de sesión implícito.");
                }
            } catch (Exception e) {
                // Si el token no es válido o hay error en Redis
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al procesar el logout: " + e.getMessage());
            }
        }
        return ResponseEntity.badRequest().body("No se proporcionó un token válido en el header.");
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
    // Nuevo endpoint para el registro de usuarios
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Usuario newUsuario) {
        try {
            Usuario savedUsuario = uS.insert(newUsuario);

            // Devolver un mensaje de éxito o los datos del usuario guardado
            Map<String, String> response = new HashMap<>();
            response.put("message", "Usuario registrado exitosamente");
            response.put("nombre", savedUsuario.getNombre());

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            // Maneja la excepción si el usuario ya existe
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
}
