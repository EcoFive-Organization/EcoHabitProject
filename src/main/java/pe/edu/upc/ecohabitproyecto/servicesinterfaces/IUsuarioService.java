package pe.edu.upc.ecohabitproyecto.servicesinterfaces;

import pe.edu.upc.ecohabitproyecto.dtos.MetodoPagoDTO;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;

import java.util.List;

public interface IUsuarioService {
    List<Usuario> list();

    Usuario listId(int id);

    Usuario insert(Usuario newUsuario);

    void delete(int id);

    // Listar por cantidad de Usuarios según el rol
    List<String[]> getUsuariosRol();

    // Listar por cantidad de Usuarios, estado y rol
    List<String[]> getUsuariosEstadoRol();

    // 1. Fase de solicitud (genera token y simula email)
    String createPasswordResetToken(String email);

    // 2. Fase de restablecimiento (valida token y cambia password)
    void resetPassword(String token, String newPassword);

    //  Configurar o modificar métod de pago para retiros
    void modificarMetodoPago(Integer idUsuario, MetodoPagoDTO dto);
}