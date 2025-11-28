package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecohabitproyecto.dtos.HistorialPublicacionDTO;
import pe.edu.upc.ecohabitproyecto.entities.Publicacion;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPublicacionRepository extends JpaRepository<Publicacion,Integer> {
    // Cantidad de Reacciones por Titulo de Publicacion
    // MODIFICADO: Ahora recibe un parámetro :uid y filtra
    @Query(value = "select p.titulo, count(r.id_reaccion) from publicacion p \n" +
            "left join reaccion r on p.id_publicacion = r.id_publicacion " +
            "where p.id_usuario = :uid " + // <--- FILTRO AGREGADO
            "group by p.titulo", nativeQuery = true)
    public List<String[]> getCantidadReacciones(@Param("uid") int uid);

    // Buscar por Solo Amigos
    @Query(value = "select titulo, contenido, privacidad, vistas, compartidos, fecha from publicacion where privacidad = 'Solo Amigos'", nativeQuery = true)

    public List<String[]> soloAmigosPublicacion();

    // Buscar por ID
    @Query(value = "select u.nombre, p.titulo, p.contenido, p.privacidad, p.vistas, p.compartidos, p.fecha from publicacion p inner join usuario u\n" +
            "on p.id_usuario = u.id_usuario where p.id_usuario = :nUsuario", nativeQuery = true)

    public List<String[]> buscarID(@Param("nUsuario") int nUsuario);






}
