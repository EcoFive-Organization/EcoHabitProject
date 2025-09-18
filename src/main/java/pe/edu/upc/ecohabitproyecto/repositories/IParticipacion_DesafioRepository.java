package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Participacion_Desafio;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IParticipacion_DesafioRepository extends JpaRepository<Participacion_Desafio, Long> { }
