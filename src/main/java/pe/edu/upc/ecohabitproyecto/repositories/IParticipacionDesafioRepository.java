package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.ParticipacionDesafio;

import java.util.List;
import java.util.Optional;

@Repository
public interface IParticipacionDesafioRepository extends JpaRepository<ParticipacionDesafio, Integer> {

    // Verificar si un usuario ya está inscrito en un desafío
    boolean existsByUsuario_IdUsuarioAndDesafio_IdDesafio(Integer usuarioId, Integer desafioId);
    boolean existsByUsuario_IdUsuarioAndDesafioAmigo_IdDesafioAmigo(Integer idUsuario, Integer idDesafioAmigo);


    // Obtener la participación de un usuario en un desafío (si existe)
    Optional<ParticipacionDesafio> findByUsuario_IdUsuarioAndDesafio_IdDesafio(Integer usuarioId, Integer desafioId);

    // Listar todas las participaciones de un usuario
    List<ParticipacionDesafio> findByUsuario_IdUsuario(Integer usuarioId);

    // Listar todas las participaciones de un desafío
    List<ParticipacionDesafio> findByDesafio_IdDesafio(Integer desafioId);
}