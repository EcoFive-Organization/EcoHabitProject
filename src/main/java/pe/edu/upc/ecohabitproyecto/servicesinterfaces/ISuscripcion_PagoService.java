package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Suscripcion_Pago;

import java.util.List;

public interface ISuscripcion_PagoService {
    public List<Suscripcion_Pago> list();
    public void insert(Suscripcion_Pago suscripcion_pago);
}
