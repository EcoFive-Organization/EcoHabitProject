package pe.edu.upc.ecohabitproyecto.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Dispositivo;

@Repository
public interface IConsumoRepository extends JpaRepository<Dispositivo,Integer> {
}
