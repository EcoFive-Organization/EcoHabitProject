package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Alerta;

import java.util.List;

@Repository
public interface IAlertaRepository extends JpaRepository<Alerta,Integer> {
    @Query(value = "SELECT id_alerta, tipo_alerta AS tipo FROM alerta WHERE tipo_alerta = :tipoAlerta\n" +
            "GROUP BY id_alerta;", nativeQuery = true)
    List<Object[]> getByTipoAlerta(@Param("tipoAlerta") String tipoAlerta);
}
