package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Usuario_Logro;

import java.util.List;

public interface IUsuario_LogroService {
    List<Usuario_Logro> list();
    void insert(Usuario_Logro usuarioLogro);
    Usuario_Logro listId(int id);
    void delete(int id);

    void update(Usuario_Logro usuarioLogro);
}
