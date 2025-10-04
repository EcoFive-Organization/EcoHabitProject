package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Suscripcion;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;

import java.util.Optional;

@Repository
public interface ISuscripcionRepository extends JpaRepository<Suscripcion, Integer> {
    // ✅ METODO SIMPLE: Spring Data JPA genera: SELECT * FROM suscripcion WHERE id_usuario = ?
    Optional<Suscripcion> findByUsuario(Usuario usuario);
}
