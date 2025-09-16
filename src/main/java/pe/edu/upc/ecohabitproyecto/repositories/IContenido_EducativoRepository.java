package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Contenido_Educativo;

@Repository
public interface IContenido_EducativoRepository extends JpaRepository<Contenido_Educativo, Integer>
{

}
