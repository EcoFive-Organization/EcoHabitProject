package pe.edu.upc.ecohabitproyecto.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Social_Plataformas;

@Repository
public interface ISocial_PlataformasRepository extends JpaRepository<Social_Plataformas, Integer> {
}
