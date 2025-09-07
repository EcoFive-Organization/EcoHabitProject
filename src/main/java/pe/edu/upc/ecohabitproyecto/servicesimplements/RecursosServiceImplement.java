package pe.edu.upc.ecohabitproyecto.servicesimplements;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Recursos;
import pe.edu.upc.ecohabitproyecto.repositories.IRecursosRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IRecursosService;

import java.util.List;

@Service
public class RecursosServiceImplement implements IRecursosService {

    @Autowired
    private IRecursosRepository iRecursosRepository;
    @Override
    public List<Recursos> list() {
        //findAll para listar todos los registros
        return iRecursosRepository.findAll();
    }

    @Override
    public void insert(Recursos recursos) {
        // save para registrar
        iRecursosRepository.save(recursos);
    }

}
