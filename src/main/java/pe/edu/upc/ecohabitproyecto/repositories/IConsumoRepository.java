package pe.edu.upc.ecohabitproyecto.repositories;


import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Consumo;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    // 🚀 CORRECCIÓN CLAVE: Agrupación por Mes/Año e Impacto (suma del valor)
    // Usamos TO_CHAR para obtener el año y el mes como una cadena.
    @Query(value = "SELECT TO_CHAR(fecha, 'YYYY-MM') AS mes_anio, SUM(valor) AS total_impacto " +
            "FROM consumo " +
            "WHERE fecha BETWEEN :startDate AND :endDate " +
            "GROUP BY mes_anio " +
            "ORDER BY mes_anio ASC;", nativeQuery = true)
    List<Object[]> getImpactoEcologicoMensual(@Param("startDate") LocalDate startDate,
                                              @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT c.tipo, SUM(c.valor) AS consumo_real, SUM(c.umbral) AS consumo_referencia " +
            "FROM consumo c " +
            "WHERE c.tipo = :tipoConsumo AND c.fecha BETWEEN :startDate AND :endDate " +
            "GROUP BY c.tipo", nativeQuery = true)
    List<Object[]> calcularMontoAhorrado(
            @Param("tipoConsumo") String tipoConsumo,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    //Estadística 1: Impacto agrupado por TIPO (Ej: Agua, Electricidad)
    @Query(value = "SELECT tipo, SUM(valor) AS impacto_total " +
            "FROM consumo " +
            "GROUP BY tipo " +
            "ORDER BY impacto_total DESC", nativeQuery = true)
    List<Object[]> getImpactoTotalByTipo();

    //Estadística 2: Impacto agrupado por ORIGEN (Ej: Lavadora, Grifo)
    @Query(value = "SELECT origen_consumo, SUM(valor) AS impacto_total " +
            "FROM consumo " +
            "GROUP BY origen_consumo " +
            "ORDER BY impacto_total DESC", nativeQuery = true)
    List<Object[]> getImpactoTotalByOrigen();
}
