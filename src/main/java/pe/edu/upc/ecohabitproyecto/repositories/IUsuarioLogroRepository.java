package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.UsuarioLogro;

import java.util.List;

@Repository
public interface IUsuarioLogroRepository extends JpaRepository<UsuarioLogro, Integer> {

    // Verifica si un usuario ya desbloqueó un logro específico
    boolean existsByUsuario_IdUsuarioAndLogro_IdLogro(int idUsuario, int idLogro);

    // Lista todos los logros desbloqueados por un usuario
    List<UsuarioLogro> findByUsuario_IdUsuario(int idUsuario);

    // Lista todos los usuarios que desbloquearon un logro específico
    List<UsuarioLogro> findByLogro_IdLogro(int idLogro);
}