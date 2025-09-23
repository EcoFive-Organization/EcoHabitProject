package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Usuario;

import java.util.List;

public interface IUsuarioService {
    public List<Usuario> list();

    public Usuario listId(int id);

    public Usuario insert(Usuario newUsuario);

    public void delete(int id);

    // Listar por cantidad de Usuarios según el rol
    public List<String[]> getUsuariosRol();

    // Listar por cantidad de Usuarios, estado y rol
    public List<String[]> getUsuariosEstadoRol();
}
