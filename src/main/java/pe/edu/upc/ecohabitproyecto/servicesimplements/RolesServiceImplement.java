package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Roles;
import pe.edu.upc.ecohabitproyecto.repositories.IRolesRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.ILogrosService;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IRolesService;

import java.util.List;
@Service
public class RolesServiceImplement implements IRolesService {
    @Autowired
    private IRolesRepository rR;
    @Override
    public List<Roles> list() {
        return rR.findAll();
    }

    @Override
    public void insert(Roles roles) {
        rR.save(roles);
    }
}
