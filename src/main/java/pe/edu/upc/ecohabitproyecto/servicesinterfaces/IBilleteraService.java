package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.entities.Billetera;

import java.math.BigDecimal;
import java.util.List;

public interface IBilleteraService {
    // Listar todas las billeteras
    List<Billetera> list();

    // Registrar nueva billetera
    void insert(Billetera billetera);

    // Obtener saldo de puntos de un usuario
    BigDecimal getSaldoPuntos(int idUsuario);


    // Acumular puntos en la billetera de un usuario
    void acumularPuntos(Integer idUsuario, Double puntos);

    // Obtener la billetera completa de un usuario
    Billetera obtenerBilleteraPorUsuario(Integer idUsuario);
}