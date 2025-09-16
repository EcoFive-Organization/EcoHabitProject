package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Transaccion;

import java.util.List;

public interface ITransaccionService {
    public List<Transaccion> list();
    public void insert(Transaccion transaccion);
    public void delete(int id);
    public void update(Transaccion transaccion);
}
