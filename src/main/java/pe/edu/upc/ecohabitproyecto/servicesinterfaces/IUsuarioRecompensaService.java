package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.UsuarioRecompensa;

import java.util.List;

public interface IUsuarioRecompensaService {
    public List<UsuarioRecompensa> list();
    public void insert(UsuarioRecompensa usuario_recompensa);
}
