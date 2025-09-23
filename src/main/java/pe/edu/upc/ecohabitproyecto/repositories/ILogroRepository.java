package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Logro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@Repository
public interface ILogroRepository extends JpaRepository<Logro, Integer> {
    Optional<Logro> findByNombre(String nombre);

}
