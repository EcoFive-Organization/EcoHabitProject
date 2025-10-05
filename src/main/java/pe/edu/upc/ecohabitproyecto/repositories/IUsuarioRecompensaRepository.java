package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.UsuarioRecompensa;

import java.util.List;

@Repository
public interface IUsuarioRecompensaRepository extends JpaRepository<UsuarioRecompensa, Integer> {
    List<UsuarioRecompensa> findByUsuario_IdUsuario(int idUsuario);
}
