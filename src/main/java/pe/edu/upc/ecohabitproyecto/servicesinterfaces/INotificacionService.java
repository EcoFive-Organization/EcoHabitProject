package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

public interface INotificacionService {

    /**
     * Envía notificaciones a los amigos del usuario que desbloqueó un logro.
     * @param idLogro ID del logro desbloqueado
     */
    void notificarLogroAmigo(Integer idLogro);
}