package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import org.springframework.data.repository.query.Param;
import pe.edu.upc.ecohabitproyecto.dtos.ConsumoGraficoDTO;
import pe.edu.upc.ecohabitproyecto.entities.Consumo;
import pe.edu.upc.ecohabitproyecto.entities.Dispositivo;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IConsumoService {
    public List<Consumo> list();
    public void insert(Consumo consumo);
    List<Object[]> findAllByTipoConsumo();
    List<Object[]> getByTotalConsumoTipo(String tipoConsumo);
    List<Object[]> getConsumoByDispositivo();

    List<Object[]> getConsumoTotalByDispositivo(Integer idUsuario);
    List<Object[]> getImpactoEcologicoMensual(LocalDate startDate, LocalDate endDate);
    List<Object[]> calcularMontoAhorrado(String tipoConsumo, LocalDate startDate, LocalDate endDate);

    List<Object[]> getImpactoTotalByTipo();
    List<Object[]> getImpactoTotalByOrigen();
    List<ConsumoGraficoDTO> obtenerConsumoSemanal(Integer idUsuario);

}
