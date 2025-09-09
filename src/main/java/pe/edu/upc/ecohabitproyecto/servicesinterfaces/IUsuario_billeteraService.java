package pe.edu.upc.ecohabitproyecto.servicesinterfaces;
import pe.edu.upc.ecohabitproyecto.entities.Suscripciones_Planes;
import pe.edu.upc.ecohabitproyecto.entities.Usuario_billetera;
import java.util.List;

public interface IUsuario_billeteraService {
    // Listar
    public List<Usuario_billetera> list();

    // Registrar
    public void insert(Usuario_billetera usuario_billetera);
}
