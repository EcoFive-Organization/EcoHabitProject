package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Transaccion;

@Repository
public interface ITransaccionRepository extends JpaRepository<Transaccion,Integer> {
}
