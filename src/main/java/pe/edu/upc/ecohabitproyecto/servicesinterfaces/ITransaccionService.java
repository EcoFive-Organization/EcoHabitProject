package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.dtos.CanjePuntosDTO;
import pe.edu.upc.ecohabitproyecto.entities.Transaccion;

import java.util.List;

public interface ITransaccionService {
    List<Transaccion> list();
    void insert(Transaccion transaccion);
    Transaccion listId(int id);
    void delete(int id);
    void update(Transaccion transaccion);
    List<Object[]> TransaccionesTotales();

    void canjearPuntos(Integer idUsuario, CanjePuntosDTO dto);
}