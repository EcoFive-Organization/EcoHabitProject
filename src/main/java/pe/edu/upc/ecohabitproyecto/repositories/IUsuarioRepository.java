package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findOneByEmail(String email);
    Usuario findOneByNombre(String nombre);

    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByNombre(String nombre);

    // 🔹 Contar cuántos usuarios tienen un rol específico
    @Query(value = "SELECT r.nombre_rol, COUNT(u.id_usuario) FROM usuario u INNER JOIN rol r ON u.id_usuario = r.id_usuario GROUP BY r.nombre_rol", nativeQuery = true)
    List<String[]> contarUsuariosRol();

    // 🔹 Contar usuarios por estado y por rol
    @Query(value = "SELECT r.nombre_rol, u.enabled, COUNT(r.id_usuario) AS total_usuarios " +
            "FROM usuario u " +
            "INNER JOIN rol r ON u.id_usuario = r.id_usuario " +
            "GROUP BY r.nombre_rol, u.enabled", nativeQuery = true)
    List<String[]> contarUsuariosEstadoRol();

    // 🔹 Obtener amigos de un usuario (HU55)
    @Query(value = "SELECT u.* FROM usuario u " +
            "INNER JOIN usuario_amigo ua ON u.id_usuario = ua.id_amigo " +
            "WHERE ua.id_usuario = :idUsuario", nativeQuery = true)
    List<Usuario> findAmigosDe(Integer idUsuario);
}
