package pe.edu.upc.ecohabitproyecto.securities;

// Importar todas las clases necesarias
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import pe.edu.upc.ecohabitproyecto.securities.TokenBlacklistService;
import pe.edu.upc.ecohabitproyecto.securities.JwtTokenUtil; // Asumiendo el nombre de su clase utilitaria
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Duration;

@Component
public class JwtLogoutHandler implements LogoutHandler {

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil; // **CLASE UTILITARIA QUE DEBE EXISTIR**

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);

            try {
                // 1. Calcular la duración restante del token (TTL)
                Duration remainingTime = jwtTokenUtil.getRemainingExpiration(jwt);
                // Nota: Debe agregar el metodo getRemainingExpiration a su JwtTokenUtil

                // 2. Si el token aún es válido, añadirlo a la lista negra de Redis
                // Nota: isNegative() y isZero() aseguran que solo se añade si hay tiempo restante.
                if (!remainingTime.isNegative() && !remainingTime.isZero()) {
                    tokenBlacklistService.blacklistToken(jwt, remainingTime);
                }

                // Spring Security llama automáticamente al LogoutSuccessHandler después
            } catch (Exception e) {
                // Manejo simple de error en caso de token inválido al hacer logout
                System.err.println("Error al procesar el token en el logout: " + e.getMessage());
            }
        }
    }
}
