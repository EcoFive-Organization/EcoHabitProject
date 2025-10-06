package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "logro") // ← Asegúrate de que el nombre coincida con la tabla en la BD
public class Logro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_logro") // ← Mapeo explícito para evitar errores de búsqueda
    private Integer idLogro;

    @Column(name = "nombre", length = 150, nullable = false)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "text")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idRecompensa", nullable = false)
    private Recompensa recompensa;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado;

    @Column(name = "puntos", nullable = false)
    private int puntos;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    // Constructores
    public Logro() {
    }

    public Logro(Integer idLogro, String nombre, String descripcion,
                 Recompensa recompensa, String estado, int puntos) {
        this.idLogro = idLogro;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.recompensa = recompensa;
        this.estado = estado;
        this.puntos = puntos;
    }

    // Getters y Setters
    public Integer getIdLogro() {
        return idLogro;
    }

    public void setIdLogro(Integer idLogro) {
        this.idLogro = idLogro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Recompensa getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(Recompensa recompensa) {
        this.recompensa = recompensa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}