package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Participacion_Desafio;

import java.util.List;

public interface IParticipacion_DesafioService {

    List<Participacion_Desafio> list();
    void insert(Participacion_Desafio participacionDesafio);
    Participacion_Desafio listId(long id);
    void delete(long id);

    void update(Participacion_Desafio participacionDesafio);
}