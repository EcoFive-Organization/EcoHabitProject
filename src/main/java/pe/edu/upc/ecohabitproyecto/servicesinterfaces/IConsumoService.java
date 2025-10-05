package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import org.springframework.data.repository.query.Param;
import pe.edu.upc.ecohabitproyecto.entities.Consumo;
import pe.edu.upc.ecohabitproyecto.entities.Dispositivo;

import java.time.LocalDate;
import java.util.List;

public interface IConsumoService {
    public List<Consumo> list();
    public void insert(Consumo consumo);
    List<Object[]> findAllByTipoConsumo();
    List<Object[]> getByTotalConsumoTipo(String tipoConsumo);
    List<Object[]> getConsumoByDispositivo();

    // 🚀 NUEVO METODO
    List<Object[]> getConsumoTotalByDispositivo();
    List<Object[]> getConsumoTotalByFecha(LocalDate startDate, LocalDate endDate);

}
