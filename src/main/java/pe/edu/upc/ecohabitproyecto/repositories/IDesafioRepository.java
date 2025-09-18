
package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Desafio;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IDesafioRepository extends JpaRepository<Desafio, Integer> { }
