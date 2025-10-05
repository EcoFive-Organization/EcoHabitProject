package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Suscripcion;
import pe.edu.upc.ecohabitproyecto.entities.SuscripcionPago;

import java.util.List;

public interface ISuscripcionService {
    public List<Suscripcion> list();
    public void insert(Suscripcion suscripcion);

    public Suscripcion seleccionarPlan(Integer idUsuario, Integer idPlan);

    public SuscripcionPago procesarPago(Integer idUsuario, Integer idMetodoPago);

    public Suscripcion cancelarSuscripcion(Integer idUsuario);

}
