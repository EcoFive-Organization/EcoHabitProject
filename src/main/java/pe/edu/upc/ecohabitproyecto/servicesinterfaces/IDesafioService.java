package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Desafio;

import java.util.List;

public interface IDesafioService {

    List<Desafio> list();

    void insert(Desafio desafio);

    Desafio listId(int id);

    void delete(int id);

    void update(Desafio desafio);

    void unirseADesafio(Integer idUsuario, Integer idDesafio);

}