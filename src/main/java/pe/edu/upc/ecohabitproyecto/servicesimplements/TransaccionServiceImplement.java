package pe.edu.upc.ecohabitproyecto.servicesimplements;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Transaccion;
import pe.edu.upc.ecohabitproyecto.repositories.ITransaccionRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.ITransaccionService;

import java.util.List;

@Service
public class TransaccionServiceImplement implements ITransaccionService {

    @Autowired
    private ITransaccionRepository tR;

    @Override
    public List<Transaccion> list() {
        return tR.findAll();
    }

    @Override
    public void insert(Transaccion transaccion) {
        tR.save(transaccion);
    }

    @Override
    public void delete(int id) {
        tR.deleteById(id);
    }

    @Override
    public void update(Transaccion transaccion) {
        tR.save(transaccion);
    }
}
