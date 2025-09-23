package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Alerta;

import java.util.List;

@Repository
public interface IAlertaRepository extends JpaRepository<Alerta,Integer> {
    @Query(value = "SELECT a.id_alerta,a.fecha,a.mensaje,a.id_consumo\n" +
            "FROM alerta a\n" +
            "JOIN consumo c ON a.id_consumo = c.id_consumo\n" +
            "JOIN dispositivo d ON c.id_dispositivo = d.id_dispositivo;", nativeQuery = true)
    List<Object[]> findAllByIdAlerta();
}
