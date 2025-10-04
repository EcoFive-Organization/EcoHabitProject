package pe.edu.upc.ecohabitproyecto.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.time.Duration;

@Service
public class TokenBlacklistService {

    @Autowired
    // RedisTemplate maneja la serialización y la comunicación con Redis
    private RedisTemplate<String, Object> redisTemplate;

    private static final String BLACKLIST_PREFIX = "revoked_jwt:";

    // Añade el token a Redis con su tiempo de vida restante (TTL)
    public void blacklistToken(String jwt, Duration expiration) {
        String key = BLACKLIST_PREFIX + jwt;
        // Almacena el token y se asegura de que Redis lo elimine automáticamente cuando expire.
        redisTemplate.opsForValue().set(key, "true", expiration);
        System.out.println("🔒 Token revocado guardado en Redis: " + key + " con TTL: " + expiration.getSeconds() + "s");
    }

    // Verifica si el token existe en la lista negra
    public boolean isTokenBlacklisted(String jwt) {
        String key = BLACKLIST_PREFIX + jwt;
        boolean exists = Boolean.TRUE.equals(redisTemplate.hasKey(key));
        System.out.println("🔍 Verificando token en Redis: " + key + " → " + exists);
        return exists;
    }
}
