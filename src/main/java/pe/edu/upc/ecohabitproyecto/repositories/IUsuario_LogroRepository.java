package pe.edu.upc.ecohabitproyecto.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.Usuario_Logro;

@Repository
public interface IUsuario_LogroRepository extends JpaRepository<Usuario_Logro,Integer> {
}
