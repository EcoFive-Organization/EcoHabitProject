package pe.edu.upc.ecohabitproyecto.dtos;

public class UsuarioRolStatusDTO {
    private String nombreRol;
    private Boolean enabled;
    private Long totalUsuarios;

    public UsuarioRolStatusDTO(String nombreRol, Boolean enabled, Long totalUsuarios) {
        this.nombreRol = nombreRol;
        this.enabled = enabled;
        this.totalUsuarios = totalUsuarios;
    }

    public UsuarioRolStatusDTO() {

    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getTotalUsuarios() {
        return totalUsuarios;
    }

    public void setTotalUsuarios(Long totalUsuarios) {
        this.totalUsuarios = totalUsuarios;
    }
}
