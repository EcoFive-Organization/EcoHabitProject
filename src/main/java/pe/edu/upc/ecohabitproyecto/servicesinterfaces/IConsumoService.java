package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import io.lettuce.core.dynamic.annotation.Param;
import pe.edu.upc.ecohabitproyecto.entities.Consumo;
import pe.edu.upc.ecohabitproyecto.entities.Dispositivo;

import java.util.List;

public interface IConsumoService {
    public List<Consumo> list();
    public void insert(Consumo consumo);
    List<Object[]> findAllByTipoConsumo();
    List<Object[]> getByTotalConsumoTipo(String tipoConsumo);
    List<Object[]> getConsumoByDispositivo();

}
