package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Usuario_billetera;

@Repository
public interface IUsuario_billeteraRepository extends JpaRepository<Usuario_billetera,Integer>{
}
