package pe.edu.upc.ecohabitproyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "social_plataformas")
public class Social_Plataformas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plataforma_id")
    private Integer plataforma_id;              // <-- Integer (permite null)

    @Column(name = "plataforma_nom", nullable = false, length = 50)
    private String plataforma_nom;

    public Social_Plataformas() { }

    public Integer getPlataforma_id() { return plataforma_id; }
    public void setPlataforma_id(Integer plataforma_id) { this.plataforma_id = plataforma_id; }

    public String getPlataforma_nom() { return plataforma_nom; }
    public void setPlataforma_nom(String plataforma_nom) { this.plataforma_nom = plataforma_nom; }
}