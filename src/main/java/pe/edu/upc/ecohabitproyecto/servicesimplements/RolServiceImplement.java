package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Rol;
import pe.edu.upc.ecohabitproyecto.repositories.IRolRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IRolService;

import java.util.List;
@Service
public class RolServiceImplement implements IRolService {
    @Autowired
    private IRolRepository rR;
    @Override
    public List<Rol> list() {
        return rR.findAll();
    }

    @Override
    public void insert(Rol rol) {
        rR.save(rol);
    }

    @Override
    public List<String[]> quantityUserByRol() {
        return rR.userQuantityByRol();
    }
}
