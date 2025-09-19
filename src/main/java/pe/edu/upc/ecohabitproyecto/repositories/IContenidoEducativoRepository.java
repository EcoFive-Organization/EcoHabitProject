package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.ContenidoEducativo;

@Repository
public interface IContenidoEducativoRepository extends JpaRepository<ContenidoEducativo, Integer>
{

}
