package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Dispositivo;

import java.util.List;

public interface IDispositivoService {
    public List<Dispositivo> list();
    public void insert(Dispositivo dispositivo);
    public Dispositivo listId(int id);
    public void delete(int id);
    public void update(Dispositivo dispositivo);
    List<String[]> getByTipo(String tipo_parametro);

}
