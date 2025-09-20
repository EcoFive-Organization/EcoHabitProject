package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Foro;

import java.util.List;

@Repository
public interface IForoRepository extends JpaRepository<Foro,Integer> {

    // Cantidad de publicaciones según foro
    @Query(value = "select f.titulo as \"Nombre del Foro\", count(p.id_publicacion) as \"Cantidad Publicaciones\" \n" +
            "from foro f left join publicacion p\n" +
            "on f.id_foro = p.id_foro group by f.titulo \n" +
            "order by \"Cantidad Publicaciones\" desc", nativeQuery = true)

    public List<String[]> postQuantityByForum();

}
