package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Metodo_Pago;
import pe.edu.upc.ecohabitproyecto.repositories.IMetodo_PagoRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IMetodo_PagoService;

import java.util.List;

@Service
public class Metodo_PagoServiceImplement implements IMetodo_PagoService {
    @Autowired //Notificar que va a usar dos metodos de los muchos que tiene
    private IMetodo_PagoRepository mR;

    @Override
    public List<Metodo_Pago> list() {
        return mR.findAll();
    }

    @Override
    public void insert(Metodo_Pago metodo_Pago) {
        mR.save(metodo_Pago);
    }
}
