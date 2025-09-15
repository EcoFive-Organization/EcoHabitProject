package pe.edu.upc.ecohabitproyecto.servicesinterfaces;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.ecohabitproyecto.entities.Billetera;
import pe.edu.upc.ecohabitproyecto.repositories.IBilleteraRepository;

import java.util.List;

public interface IBilleteraService {
    // Listar
    public List<Billetera> list();

    // Registrar
    public void insert(Billetera billetera);
}
