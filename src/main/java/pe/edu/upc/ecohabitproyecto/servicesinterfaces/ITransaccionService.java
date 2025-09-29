package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Transaccion;

import java.util.List;

public interface ITransaccionService {
    public List<Transaccion> list();
    public void insert(Transaccion transaccion);
    public Transaccion listId(int id);
    public void delete(int id);
    public void update(Transaccion transaccion);
    List<Object[]> TransaccionesTotales();
}
