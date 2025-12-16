package pe.edu.upc.ecohabitproyecto.dtos;

public class UsuarioRolCountDTO {
    private Long totalUsuarios;

    public UsuarioRolCountDTO(String nombreRol, Long totalUsuarios) {
        this.totalUsuarios = totalUsuarios;
    }

    public UsuarioRolCountDTO() {

    }

    public Long getTotalUsuarios() {
        return totalUsuarios;
    }

    public void setTotalUsuarios(Long totalUsuarios) {
        this.totalUsuarios = totalUsuarios;
    }
}
