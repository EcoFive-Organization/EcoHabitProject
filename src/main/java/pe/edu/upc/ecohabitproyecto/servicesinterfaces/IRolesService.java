package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Roles;

import java.util.List;

public interface IRolesService {
    public List<Roles> list();
    public void insert(Roles roles);
}
