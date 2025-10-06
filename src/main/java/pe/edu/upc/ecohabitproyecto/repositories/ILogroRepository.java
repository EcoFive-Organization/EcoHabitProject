package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Logro;

import java.util.Optional;

@Repository
public interface ILogroRepository extends JpaRepository<Logro, Integer> {

    // Buscar un logro por su nombre
    Optional<Logro> findByNombre(String nombre);

}