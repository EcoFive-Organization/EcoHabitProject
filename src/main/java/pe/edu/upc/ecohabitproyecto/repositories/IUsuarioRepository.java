package pe.edu.upc.ecohabitproyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
    public Usuario findOneByEmail(String email);
    public Usuario findOneByNombre(String nombre);

    //BUSCAR POR NOMBRE
    @Query("select count(u.nombre) from Usuario u where u.nombre =:nombre")
    public int buscarNombre(@Param("nombre") String nombre);


    //INSERTAR ROLES
    @Transactional
    @Modifying
    @Query(value = "insert into rol (nombre_rol, id_usuario) VALUES (:nombre_rol, :id_user)", nativeQuery = true)
    public void insRol(@Param("rol") String authority, @Param("id_user") Long id_user);
}
