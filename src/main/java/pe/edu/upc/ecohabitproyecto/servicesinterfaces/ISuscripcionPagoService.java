package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.SuscripcionPago;

import java.util.List;

public interface ISuscripcionPagoService {
    public List<SuscripcionPago> list();
    public void insert(SuscripcionPago suscripcion_pago);
}
