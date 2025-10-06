package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Notificacion;

import java.util.List;

@Repository
public interface INotificacionRepository extends JpaRepository<Notificacion, Integer> {

    // 🔹 Obtener todas las notificaciones de un usuario
    List<Notificacion> findByUsuario_IdUsuarioOrderByFechaDesc(Integer idUsuario);
}