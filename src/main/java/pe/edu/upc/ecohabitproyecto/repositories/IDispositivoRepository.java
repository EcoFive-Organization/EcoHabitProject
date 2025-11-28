package pe.edu.upc.ecohabitproyecto.repositories;


import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Dispositivo;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDispositivoRepository extends JpaRepository<Dispositivo, Integer> {
    @Query(value = "SELECT\n" +
            "    nombre\n" +
            "FROM\n" +
            "    Dispositivo\n" +
            "WHERE\n" +
            "    tipo = :tipo_parametro;",nativeQuery = true)
    List<String[]> getByTipo(@Param("tipo_parametro") String tipo_parametro);

    // Listar de ADMIN
    @Query("SELECT d FROM Dispositivo d JOIN FETCH d.usuario")
    List<Dispositivo> findAllWithUsuario();

    // Actualizar
    @Query("SELECT d FROM Dispositivo d JOIN FETCH d.usuario WHERE d.idDispositivo = :id")
    Optional<Dispositivo> findWithUsuarioById(@Param("id") int id);

    //Buscamos por ID
    @Query("SELECT d FROM Dispositivo d JOIN FETCH d.usuario u WHERE u.idUsuario = :idUsuario")
    List<Dispositivo> findByUsuarioId(@Param("idUsuario") int idUsuario);

    //List<Dispositivo> findByUsuarioIdUsuario(Integer idUsuario);

    @Query("SELECT d FROM Dispositivo d JOIN FETCH d.usuario u WHERE u.idUsuario = :idUsuario")
    List<Dispositivo> listByUserId(Integer idUsuario);
}
