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
    // 1. Buscar Lectura (JPQL)
    // Agregamos 'c.idContenidoEducativo' al principio
    @Query(value = "select c.idContenidoEducativo, c.titulo, c.descripcion, c.url, c.tipo from ContenidoEducativo c where c.tipo = 'Lectura'")
    public List<String[]> buscarLectura();
    // Nota: Cambié String[] a Object[] porque el ID es un número, es más seguro.

    // 2. Buscar Video (Native Query)
    // Agregamos 'c.id_contenido_educativo' al principio (nombre de columna en DB)
    @Query(value = "select c.id_contenido_educativo, c.titulo, c.descripcion, c.url, c.tipo from contenido_educativo c where c.tipo = 'Video'", nativeQuery = true)
    public List<String[]> buscarVideo();

}
