package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Publicacion;

import java.util.List;

@Repository
public interface IPublicacionRepository extends JpaRepository<Publicacion,Integer> {
    // Cantidad de Reacciones por Titulo de Publicacion
    @Query(value = "select p.titulo, count(r.id_reaccion) from publicacion p \n" +
            "left join reaccion r on p.id_publicacion = r.id_publicacion group by p.titulo", nativeQuery = true)

    public List<String[]> getCantidadReacciones();


}
