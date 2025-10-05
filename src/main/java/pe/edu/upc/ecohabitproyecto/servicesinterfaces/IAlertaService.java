package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import org.springframework.data.repository.query.Param;
import pe.edu.upc.ecohabitproyecto.entities.Alerta;

import java.util.List;

public interface IAlertaService {
    public List<Alerta> list();
    public void insert(Alerta alerta);
    List<Object[]> getByTipoAlerta(@Param("tipoAlerta") String tipoAlerta);
}
