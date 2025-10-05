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
    @Query(value = "SELECT c.id_consumo, d.id_dispositivo, c.valor\n" +
            "FROM consumo c JOIN Dispositivo d ON c.id_dispositivo = d.id_dispositivo\n" +
            "ORDER BY c.id_consumo ASC;",nativeQuery = true)
    List<Object[]> getConsumoByDispositivo();
    @Query(value = "SELECT tipo, SUM(valor) AS total_consumo FROM consumo WHERE tipo = :tipoConsumo\n" +
            "GROUP BY tipo;", nativeQuery = true)
    List<Object[]> getByTotalConsumoTipo(
            @Param("tipoConsumo") String tipoConsumo);
}
