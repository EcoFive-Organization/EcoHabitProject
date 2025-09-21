package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.entities.ContenidoEducativo;

import java.util.List;

@Repository
public interface IContenidoEducativoRepository extends JpaRepository<ContenidoEducativo, Integer>
{
    // Listar por tipo Lectura
    @Query(value = "select c.titulo, c.descripcion, c.url, c.tipo from ContenidoEducativo c where c.tipo = 'Lectura'")
    public List<String[]> buscarLectura();

    // Listar por tipo Video
    @Query(value = "select c.titulo, c.descripcion, c.url, c.tipo from contenido_educativo c where c.tipo = 'Video'", nativeQuery = true)
    public List<String[]> buscarVideo();

}
