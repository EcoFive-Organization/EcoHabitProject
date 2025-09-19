package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Logro;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ILogroRepository extends JpaRepository<Logro, Integer> {

}
