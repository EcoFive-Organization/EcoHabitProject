package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.UsuarioLogro;

import java.util.List;

public interface IUsuarioLogroService {
    List<UsuarioLogro> list();
    void insert(UsuarioLogro usuarioLogro);
    UsuarioLogro listId(int id);
    void delete(int id);

    void update(UsuarioLogro usuarioLogro);
}
