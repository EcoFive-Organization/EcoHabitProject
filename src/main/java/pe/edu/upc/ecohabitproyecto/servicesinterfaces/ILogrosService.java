package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Logros;

import java.util.List;

public interface ILogrosService {
    public List<Logros> list();
    public void insert(Logros logros);
}
