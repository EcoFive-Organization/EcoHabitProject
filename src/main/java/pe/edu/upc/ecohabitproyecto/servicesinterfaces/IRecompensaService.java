package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Recompensa;

import java.util.List;

public interface IRecompensaService {

    List<Recompensa> list();

    void insert(Recompensa recompensa);

    Recompensa listId(int id);

    void delete(int id);

    void update(Recompensa recompensa);
}