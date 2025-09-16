package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Billetera;
import pe.edu.upc.ecohabitproyecto.repositories.IBilleteraRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IBilleteraService;

import java.util.List;

@Service
public class BilleteraImplement implements IBilleteraService {
    @Autowired
    private IBilleteraRepository billeteraRepository;

    @Override
    public List<Billetera> list(){
        // Listar
        return billeteraRepository.findAll();
    }

    @Override
    public void insert(Billetera billetera) {
        // Registrar
        billeteraRepository.save(billetera);
    }
}
