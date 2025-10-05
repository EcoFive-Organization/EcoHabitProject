package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Billetera;

import java.util.Optional;

@Repository
public interface IBilleteraRepository extends JpaRepository<Billetera, Integer> {
    Optional<Billetera> findByUsuarioIdUsuario(int idUsuario);
}
