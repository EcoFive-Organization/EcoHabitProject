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

    @Query(value = "SELECT id_consumo, id_dispositivo, valor FROM consumo ORDER BY id_consumo ASC;",nativeQuery = true)
    List<Object[]> getConsumoByDispositivo();

    @Query(value = "SELECT tipo, SUM(valor) AS total_consumo FROM consumo WHERE tipo = :tipoConsumo\n" +
            "GROUP BY tipo;", nativeQuery = true)
    List<Object[]> getByTotalConsumoTipo(
            @Param("tipoConsumo") String tipoConsumo);

    // 🚀 MODIFICACIÓN CLAVE: Agregando id_dispositivo y d.id_usuario
    @Query(value = "SELECT d.id_dispositivo, u.id_usuario, d.nombre, SUM(c.valor) AS total_consumo " +
            "FROM consumo c " +
            "JOIN dispositivo d ON c.id_dispositivo = d.id_dispositivo " +
            "JOIN usuario u ON d.id_usuario = u.id_usuario " +
            "GROUP BY d.id_dispositivo, u.id_usuario, d.nombre " +
            "ORDER BY total_consumo DESC", nativeQuery = true)
    List<Object[]> getConsumoTotalByDispositivo();
}
