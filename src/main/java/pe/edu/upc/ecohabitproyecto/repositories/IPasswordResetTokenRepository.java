package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.PasswordResetToken;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;

import java.util.Optional;

@Repository
public interface IPasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    // 1. Encuentra el token de recuperación por su valor (se usa para validar el token que envía el usuario)
    Optional<PasswordResetToken> findByToken(String token);

    // 2. Elimina todos los tokens asociados a un usuario (se usa para limpiar tokens viejos al solicitar uno nuevo)
    // Se requiere @Modifying y @Transactional en el servicio para ejecutar DELETE en JpaRepository
    @Modifying
    @Query("delete from PasswordResetToken t where t.usuario = :usuario")
    void deleteByUsuario(@Param("usuario") Usuario usuario);
}
