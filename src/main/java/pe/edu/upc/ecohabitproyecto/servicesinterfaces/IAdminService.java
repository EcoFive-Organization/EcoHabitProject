package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.dtos.LogroDTO;
import pe.edu.upc.ecohabitproyecto.entities.Logro;

public interface IAdminService {
    Logro registrarLogro(LogroDTO logroDTO);
    Logro modificarLogro(Integer idLogro, LogroDTO logroDTO);
    Logro obtenerLogroPorId(Integer idLogro);
    void eliminarLogro(Integer idLogro);

}