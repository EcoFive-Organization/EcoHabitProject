package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Logro;

import java.util.List;

public interface ILogroService {

    List<Logro> list();

    void insert(Logro logro);

    Logro listId(int id);

    void delete(int id);

    void update(Logro logro);

    void desbloquearLogro(Integer idUsuario, String nombreLogro);

}