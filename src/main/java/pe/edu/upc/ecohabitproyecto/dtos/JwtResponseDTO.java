package pe.edu.upc.ecohabitproyecto.dtos;

import java.io.Serializable;

public class JwtResponseDTO implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    // 🔴 CAMBIO 1: Agregamos el campo ID
    private final Integer idUsuario;

    // 🔴 CAMBIO 2: Actualizamos el constructor para pedir el ID
    public JwtResponseDTO(String jwttoken, Integer idUsuario) {
        super();
        this.jwttoken = jwttoken;
        this.idUsuario = idUsuario;
    }

    public String getJwttoken() {
        return jwttoken;
    }

    // 🔴 CAMBIO 3: Agregamos el getter
    public Integer getIdUsuario() {
        return idUsuario;
    }

}
