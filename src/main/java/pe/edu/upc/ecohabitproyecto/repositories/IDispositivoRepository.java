package pe.edu.upc.ecohabitproyecto.repositories;


import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Dispositivo;

import java.util.List;

@Repository
public interface IDispositivoRepository extends JpaRepository<Dispositivo, Integer> {
    @Query(value = "SELECT\n" +
            "    nombre\n" +
            "FROM\n" +
            "    Dispositivo\n" +
            "WHERE\n" +
            "    tipo = :tipo_parametro;",nativeQuery = true)
    List<String[]> getByTipo(@Param("tipo_parametro") String tipo_parametro);
}
