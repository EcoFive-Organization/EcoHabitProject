package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Suscripciones_Planes;
import pe.edu.upc.ecohabitproyecto.entities.Usuario_billetera;
import pe.edu.upc.ecohabitproyecto.repositories.ISuscripciones_PlanesRepository;
import pe.edu.upc.ecohabitproyecto.repositories.IUsuario_billeteraRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.ISuscripciones_PlanesService;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IUsuario_billeteraService;

import java.util.List;

@Service
public class Usuario_billeteraImplement implements IUsuario_billeteraService {
    @Autowired
    private IUsuario_billeteraRepository user_billeteraRepository;

    @Override
    public List<Usuario_billetera> list(){
        // Listar
        return user_billeteraRepository.findAll();
    }

    @Override
    public void insert(Usuario_billetera  user_billetera) {
        // Registrar
        user_billeteraRepository.save(user_billetera);
    }
}
