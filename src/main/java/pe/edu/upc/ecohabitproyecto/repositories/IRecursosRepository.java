package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Recursos;

// Se hace herencia a JPARepository
// Se incluye @Repository para manejar la data
@Repository
public interface IRecursosRepository extends JpaRepository<Recursos, Integer> {
}
