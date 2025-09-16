package pe.edu.upc.ecohabitproyecto.servicesinterfaces;


import pe.edu.upc.ecohabitproyecto.entities.Foro;

import java.util.List;

public interface IForoService {

    public List<Foro> list();

    public void insert(Foro foro);

}
