package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Comentario;

import java.util.List;

public interface IComentarioService {
    public List<Comentario> list();

    public void insert(Comentario comentario);
}
