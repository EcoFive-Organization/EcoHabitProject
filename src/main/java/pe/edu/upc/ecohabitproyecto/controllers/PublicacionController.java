package pe.edu.upc.ecohabitproyecto.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.CantidadReaccionesPublicacionDTO;
import pe.edu.upc.ecohabitproyecto.dtos.HistorialPublicacionDTO;
import pe.edu.upc.ecohabitproyecto.dtos.PublicacionDTO;
import pe.edu.upc.ecohabitproyecto.dtos.PublicacionSoloAmigosDTO;
import pe.edu.upc.ecohabitproyecto.entities.Publicacion;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IPublicacionService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
@RequestMapping("/publicaciones")
public class PublicacionController {
    @Autowired
    private IPublicacionService publicacionService;

    // Listar publicaciones
    @GetMapping
    public List<PublicacionDTO> listar() {
        // 1. Obtener quién está logueado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // 2. Verificar si tiene rol ADMIN
        boolean isAdmin = false;
        for (GrantedAuthority authority : auth.getAuthorities()) {
            if (authority.getAuthority().equals("ADMIN")) { // Asegúrate que el rol en BD sea "ADMIN" (o "ROLE_ADMIN")
                isAdmin = true;
                break;
            }
        }

        List<Publicacion> lista;

        // 3. Decidir qué lista traer
        if (isAdmin) {
            // El Admin ve TODO
            lista = publicacionService.list();
        } else {
            // El Cliente ve solo lo SUYO (auth.getName() devuelve el 'nombre' del usuario)
            lista = publicacionService.listByNombreUsuario(auth.getName());
        }

        // 4. Convertir a DTO
        return lista.stream().map(x -> {
            ModelMapper mapper = new ModelMapper();
            return mapper.map(x, PublicacionDTO.class);
        }).collect(Collectors.toList());
    }

    // Registrar una publicacion
    @PostMapping
    public void insertar(@RequestBody PublicacionDTO publicacionDTO){
        ModelMapper mapper = new ModelMapper();
        Publicacion publicacion = mapper.map(publicacionDTO, Publicacion.class);
        publicacionService.crearPublicacion(publicacion);
    }

    // Listar por id
    @GetMapping("/{id}")
    public ResponseEntity<?> listId(@PathVariable("id") Integer id) {
        Publicacion publicacion = publicacionService.listId(id);
        if (publicacion == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        PublicacionDTO dto = m.map(publicacion, PublicacionDTO.class);
        return ResponseEntity.ok(dto);
    }

    // Eliminar fisico
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Publicacion publicacion = publicacionService.listId(id);
        if (publicacion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        publicacionService.eliminarPublicacion(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    // Modificar un registro
    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody PublicacionDTO dto) {
        ModelMapper m = new ModelMapper();
        // Transforma de dto a software
        Publicacion publicacion = m.map(dto, Publicacion.class);

        // Validación de existencia
        Publicacion existente = publicacionService.listId(publicacion.getIdPublicacion());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + publicacion.getIdPublicacion());
        }

        // Actualización si pasa validaciones
        publicacionService.modificarPublicacion(publicacion);
        return ResponseEntity.ok("Registro con ID " + publicacion.getIdPublicacion() + " modificado correctamente.");
    }

    // Cantidad de reacciones segun publicacion
    // MODIFICADO: Ahora recibe el ID en la ruta
    @GetMapping("/cantidadReacciones/{idUsuario}")
    public ResponseEntity<?> getCantidadReacciones(@PathVariable("idUsuario") int idUsuario) {

        List<CantidadReaccionesPublicacionDTO> listaDTO = new ArrayList<>();

        // Pasamos el ID al servicio
        List<String[]> fila = publicacionService.getCantidadReacciones(idUsuario);

        if (fila.isEmpty()) {
            // Opcional: Si quieres que el gráfico salga vacío en vez de error 404,
            // puedes devolver OK con lista vacía. Pero mantendré tu lógica:
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron registros para este usuario.");
        }

        for(String[] columna : fila) {
            CantidadReaccionesPublicacionDTO dto = new CantidadReaccionesPublicacionDTO();
            dto.setTitulo(columna[0]);
            dto.setCantidadReacciones(Integer.parseInt(columna[1]));
            listaDTO.add(dto);
        }

        return ResponseEntity.ok(listaDTO);
    }

    // Visualizar Solo Amigos
    @GetMapping("/solo-amigos")
    public ResponseEntity<?> getSoloAmigos() {
        List<PublicacionSoloAmigosDTO> listaDTO = new ArrayList<>();
        List<String[]> fila = publicacionService.soloAmigosPublicacion();

        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron registros.");
        }

        for(String[] columna : fila) {
            PublicacionSoloAmigosDTO dto = new PublicacionSoloAmigosDTO();
            dto.setTitulo(columna[0]);
            dto.setContenido(columna[1]);
            dto.setPrivacidad(columna[2]);
            dto.setVistas(Integer.parseInt(columna[3]));
            dto.setCompartidos(Integer.parseInt(columna[4]));
            dto.setFecha(LocalDate.parse(columna[5]));
            listaDTO.add(dto);
        }

        return ResponseEntity.ok(listaDTO);

    }

    // Buscar Historial
    @GetMapping("/historial")
    public ResponseEntity<?> getHistorial(@RequestParam int nUsuario) {
        List<String[]> fila = publicacionService.buscarID(nUsuario);
        List<HistorialPublicacionDTO> listaDTO = new ArrayList<>();

        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron registros.");
        }

        for (String[] columna : fila) {
            HistorialPublicacionDTO dto = new HistorialPublicacionDTO();
            dto.setNombre(columna[0]);
            dto.setTitulo(columna[1]);
            dto.setContenido(columna[2]);
            dto.setPrivacidad(columna[3]);
            dto.setVistas(Integer.parseInt(columna[4]));
            dto.setCompartidos(Integer.parseInt(columna[5]));
            dto.setFecha(LocalDate.parse(columna[6]));
            listaDTO.add(dto);
        }

        return ResponseEntity.ok(listaDTO);
    }



}
