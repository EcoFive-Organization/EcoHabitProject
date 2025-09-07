package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Recursos;

import java.util.List;

public interface IRecursosService {
    // Para listar los recursos creados
    public List<Recursos> list();

    // Para registrar un recurso
    public void insert(Recursos recursos);
}
