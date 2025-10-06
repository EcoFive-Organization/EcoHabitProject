package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Desafio;
import pe.edu.upc.ecohabitproyecto.dtos.DesafioAmigoDTO;

import java.util.List;

public interface IDesafioService {

    List<Desafio> list();

    void insert(Desafio desafio);

    Desafio listId(int id);

    void delete(int id);

    void update(Desafio desafio);

    // 🔹 HU27: Unirse a un desafío comunitario
    void unirseADesafioComunitario(Integer idUsuario, Integer idDesafio);

    // 🔹 HU52: Crear desafío con amigos
    void crearDesafioAmigo(DesafioAmigoDTO dto);

    void eliminarDesafioAmigo(Integer idDesafioAmigo, Integer idCreador);

    void unirseADesafioAmigo(Integer idUsuario, Integer idDesafioAmigo);


}