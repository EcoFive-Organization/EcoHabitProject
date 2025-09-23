package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.ParticipacionDesafio;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IParticipacionDesafioRepository extends JpaRepository<ParticipacionDesafio, Long> {
    boolean existsByUsuario_IdUsuarioAndDesafio_IdDesafio(
            Integer usuarioId, Integer desafioId);
}
