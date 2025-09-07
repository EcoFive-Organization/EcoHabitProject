package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Challenges;

import java.util.List;

public interface IChallengesService {
    // Listar
    public List<Challenges> list();

    // Registrar
    public void insert(Challenges challenges);
}
