package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Metodo_Pago;

import java.util.List;

public interface IMetodo_PagoService {
    public List<Metodo_Pago> list();
    public void insert(Metodo_Pago metodo_Pago);
}
