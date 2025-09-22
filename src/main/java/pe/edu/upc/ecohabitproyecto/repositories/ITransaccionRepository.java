package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Transaccion;

import java.util.List;

@Repository
public interface ITransaccionRepository extends JpaRepository<Transaccion,Integer> {
    @Query(value = "SELECT t.id_transaccion, t.id_billetera,t.monto,t.fecha\n" +
            "FROM transaccion t\n" +
            "JOIN billetera b ON t.id_billetera = b.id_billetera;", nativeQuery = true)
    public List<Transaccion> findAllByTransaccion();
}
