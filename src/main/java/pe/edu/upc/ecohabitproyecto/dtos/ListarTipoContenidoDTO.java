package pe.edu.upc.ecohabitproyecto.dtos;

public class ListarTipoContenidoDTO {
    private int idContenidoEducativo;
    private String titulo;
    private String descripcion;
    private String url;
    private String tipo;

    public int getIdContenidoEducativo() {
        return idContenidoEducativo;
    }

    public void setIdContenidoEducativo(int idContenidoEducativo) {
        this.idContenidoEducativo = idContenidoEducativo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
