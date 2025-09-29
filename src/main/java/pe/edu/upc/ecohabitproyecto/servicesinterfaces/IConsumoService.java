package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Consumo;

import java.util.List;

public interface IConsumoService {
    public List<Consumo> list();
    public void insert(Consumo consumo);
    List<String[]> findAllByTipoConsumo();

}
