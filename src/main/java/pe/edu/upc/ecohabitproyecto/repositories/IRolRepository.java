package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Rol;

import java.util.List;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer >{

    @Query(value = "select r.nombre_rol as \"Nombre Rol\", count(u.id_usuario) as \"Cantidad de usuarios\"\n" +
            "from rol r left join usuario u on r.id_rol = u.id_rol \n" +
            "group by r.nombre_rol order by \"Cantidad de usuarios\" desc", nativeQuery = true)

    public List<String[]> userQuantityByRol();
}
