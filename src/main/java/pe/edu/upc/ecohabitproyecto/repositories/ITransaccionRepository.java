package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Transaccion;

import java.util.List;

@Repository
public interface ITransaccionRepository extends JpaRepository<Transaccion,Integer> {
    @Query(value = "SELECT t.tipo AS tipo_transaccion, SUM(t.monto) AS monto_total_transacciones,\n" +
            "COUNT(t.id_transaccio) AS cantidad_transacciones, COUNT(DISTINCT t.id_billetera) AS cantidad_billeteras_unicas\n" +
            "FROM transaccion t GROUP BY t.tipo ORDER BY monto_total_transacciones DESC;", nativeQuery = true)
    List<Object[]> TransaccionesTotales();
    @Query(value = "SELECT tipo, SUM(monto) AS monto_total\n" +
            "FROM Transaccion\n" +
            "GROUP BY tipo\n" +
            "ORDER BY monto_total DESC;",nativeQuery = true)
    List<Object[]> TransaccionesMonto();
}
