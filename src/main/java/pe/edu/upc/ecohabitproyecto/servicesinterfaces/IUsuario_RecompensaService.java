package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Usuario_Recompensa;

import java.util.List;

public interface IUsuario_RecompensaService {
    public List<Usuario_Recompensa> list();
    public void insert(Usuario_Recompensa usuario_recompensa);
}
