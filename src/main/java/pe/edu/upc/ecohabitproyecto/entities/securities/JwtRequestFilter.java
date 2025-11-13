package pe.edu.upc.ecohabitproyecto.entities.securities;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier; // ⬅️ NUEVA IMPORTACIÓN
import org.springframework.security.authentication.BadCredentialsException; // ⬅️ NUEVA IMPORTACIÓN
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver; // ⬅️ NUEVA IMPORTACIÓN
import pe.edu.upc.ecohabitproyecto.servicesimplements.JwtUserDetailsService;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    // 1. Inyección de la Blacklist (HU39)
    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    // 2. 🔑 Inyección del Resolver de Excepciones (CORRECCIÓN CRÍTICA)
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver exceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);

            // 🛡️ VERIFICACIÓN DE LA LISTA NEGRA DE REDIS (HU39)
            if (tokenBlacklistService.isTokenBlacklisted(jwtToken)) {

                logger.warn("Petición rechazada: Token JWT revocado (en lista negra de Redis).");

                // 🔑 NUEVO CÓDIGO: ESCRIBIR LA RESPUESTA DE ERROR 401 MANUALMENTE
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
                response.setContentType("application/json");

                // Enviamos el mensaje que Spring Security intentaba enviar
                response.getWriter().write("{\"error\": \"Unauthorized\", \"message\": \"Token Blacklisted.\"}");
                response.getWriter().flush();

                return; // ⬅️ CLAVE: Terminar el procesamiento del filtro.
            }

            // Lógica de validación de JWT existente
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("No se puede encontrar el token JWT");
            } catch (ExpiredJwtException e) {
                System.out.println("Token JWT ha expirado");
            }
        } else {
            logger.warn("JWT Token no inicia con la palabra Bearer");
            System.out.println(requestTokenHeader);
        }


        // Once we get the token validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

            // if token is valid configure Spring Security to manually set
            // authentication
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}


