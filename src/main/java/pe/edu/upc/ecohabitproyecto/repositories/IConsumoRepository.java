package pe.edu.upc.ecohabitproyecto.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Consumo;

import java.util.List;

@Repository
public interface IConsumoRepository extends JpaRepository<Consumo,Integer> {
    @Query(value = "SELECT tipo, COUNT(id_consumo) AS cantidad_consumos FROM consumo\n" +
            "GROUP BY tipo ORDER BY cantidad_consumos DESC;", nativeQuery = true)
    List<Object[]> findAllByTipoConsumo();
    @Query(value = "SELECT SUM(valor) AS total_consumo\n" +
            "FROM consumo\n" +
            "WHERE tipo = :tipoConsumo;", nativeQuery = true)
    List<String[]> getByTotalConsumoTipo(
            @Param("tipoConsumo") String tipoConsumo);
}
