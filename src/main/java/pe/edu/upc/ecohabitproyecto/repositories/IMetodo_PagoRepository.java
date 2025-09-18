package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Metodo_Pago;

@Repository
public interface IMetodo_PagoRepository extends JpaRepository<Metodo_Pago, Integer> {
}
