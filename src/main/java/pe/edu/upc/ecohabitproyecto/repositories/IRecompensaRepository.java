package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Recompensa;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IRecompensaRepository extends JpaRepository<Recompensa, Integer> { }
