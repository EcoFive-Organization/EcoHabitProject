package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.SuscripcionPago;

@Repository
public interface ISuscripcionPagoRepository extends JpaRepository<SuscripcionPago, Integer> {
}
