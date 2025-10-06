package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.ParticipacionDesafio;

import java.util.List;

public interface IParticipacionDesafioService {
    List<ParticipacionDesafio> list();
    void insert(ParticipacionDesafio participacionDesafio);
    ParticipacionDesafio listId(Integer id);
    void delete(Integer id);
    void update(ParticipacionDesafio participacionDesafio);
}
