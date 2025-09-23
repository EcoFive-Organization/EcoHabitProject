package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;

import java.util.List;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
    public Usuario findOneByEmail(String email);
    public Usuario findOneByNombre(String nombre);

    // Contar cuántos usuarios tienen un rol específico
    @Query(value = " SELECT r.nombre_rol, COUNT(u.id_usuario) FROM usuario u INNER JOIN rol r ON u.id_usuario = r.id_usuario GROUP BY r.nombre_rol", nativeQuery = true)
    public List<String[]> contarUsuariosRol();

    // Contar usuarios por estado y por rol
    @Query(value = " SELECT r.nombre_rol, u.enabled, COUNT(r.id_usuario) AS total_usuarios\n" +
            " FROM usuario u\n" +
            " INNER JOIN rol r ON u.id_usuario = r.id_usuario\n" +
            " GROUP BY r.nombre_rol, u.enabled;", nativeQuery = true)
    public List<String[]> contarUsuariosEstadoRol();
}
