package pe.edu.upc.ecohabitproyecto.dtos;

public class UsuarioRolCountDTO {
    private String nombreRol;
    private Long totalUsuarios;

    public UsuarioRolCountDTO(String nombreRol, Long totalUsuarios) {
        this.nombreRol = nombreRol;
        this.totalUsuarios = totalUsuarios;
    }

    public UsuarioRolCountDTO() {

    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public Long getTotalUsuarios() {
        return totalUsuarios;
    }

    public void setTotalUsuarios(Long totalUsuarios) {
        this.totalUsuarios = totalUsuarios;
    }
}
