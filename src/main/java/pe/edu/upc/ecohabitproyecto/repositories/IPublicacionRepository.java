package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Publicacion;

@Repository
public interface IPublicacionRepository extends JpaRepository<Publicacion,Integer> {
}
