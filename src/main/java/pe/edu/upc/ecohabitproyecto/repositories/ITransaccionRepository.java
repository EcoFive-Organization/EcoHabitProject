package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Billetera;
import pe.edu.upc.ecohabitproyecto.entities.Transaccion;

import java.util.List;

@Repository
public interface ITransaccionRepository extends JpaRepository<Transaccion, Integer> {

    // Totales por tipo de transacción
    @Query(value = "SELECT t.tipo AS tipo_transaccion, " +
            "SUM(t.monto_puntos) AS monto_total_transacciones, " +
            "COUNT(t.id_transaccion) AS cantidad_transacciones, " +
            "COUNT(DISTINCT t.id_billetera) AS cantidad_billeteras_unicas " +
            "FROM transaccion t " +
            "GROUP BY t.tipo " +
            "ORDER BY monto_total_transacciones DESC;",
            nativeQuery = true)
    List<Object[]> TransaccionesTotales();

    // Monto total por tipo de transacción
    @Query(value = "SELECT t.tipo AS tipo_transaccion, " +
            "SUM(t.monto_puntos) AS monto_total " +
            "FROM transaccion t " +
            "GROUP BY t.tipo " +
            "ORDER BY monto_total DESC;",
            nativeQuery = true)
    List<Object[]> TransaccionesMonto();

    // Monto total agrupado por mes
    @Query(value = "SELECT EXTRACT(MONTH FROM t.fecha) AS mes, " +
            "SUM(t.monto) AS monto_total " +
            "FROM transaccion t " +
            "GROUP BY mes " +
            "ORDER BY mes;",
            nativeQuery = true)
    List<Object[]> transactionsMonth();

    // Buscar transacciones por billetera
    List<Transaccion> findByBilletera(Billetera billetera);

    //Buscar transacciones por ID de Usuario (A través de la Billetera)
    @Query("SELECT t FROM Transaccion t JOIN t.billetera b WHERE b.usuario.idUsuario = :idUsuario")
    List<Transaccion> findByUsuarioId(@Param("idUsuario") Integer idUsuario);
}