package pe.edu.upc.ecohabitproyecto.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.*;
import pe.edu.upc.ecohabitproyecto.entities.Consumo;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;
import pe.edu.upc.ecohabitproyecto.repositories.IUsuarioRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IConsumoService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/consumos")
public class ConsumoController {

    @Autowired
    private IConsumoService cS;
    @Autowired
    private IUsuarioRepository uR;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    public List<ConsumoDTO> listar(){
        return cS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
                return m.map(x,ConsumoDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    public void insertar(@RequestBody ConsumoDTO s){
        ModelMapper m = new ModelMapper();
        Consumo cons=m.map(s, Consumo.class);
        cS.insert(cons);
    }


    @GetMapping("/CantidadPorTipoConsumo")
    public ResponseEntity<?> obtenerCantidadPorTipoConsumo() {
        List<CantidadConsumoDTO> listaDTO = new ArrayList<>();
        List<Object[]> fila = cS.findAllByTipoConsumo(); // Llama al nuevo metodo del servicio

        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron registros de consumo para contar.");
        }

        for (Object[] columna : fila) {
            CantidadConsumoDTO dto = new CantidadConsumoDTO();
            dto.setTipo((String) columna[0]);
            dto.setCantidad(((Number) columna[1]).longValue());
            listaDTO.add(dto);
        }

        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/TipoConsumo")
    public ResponseEntity<?> getByTotalConsumoTipo(@RequestParam String tipoConsumo) {
        List<SumTipoConsumoDTO> listaDTO = new ArrayList<>();
        List<Object[]> fila = cS.getByTotalConsumoTipo(tipoConsumo);

        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron registros de consumo para contar.");
        }

        for (Object[] columna : fila) {
            SumTipoConsumoDTO dto = new SumTipoConsumoDTO();
            dto.setTipo((String) columna[0]);
            dto.setTotalConsumo((BigDecimal) columna[1]);
            listaDTO.add(dto);
        }

        return ResponseEntity.ok(listaDTO);
    }


    @GetMapping("/ConsumoTotalPorDispositivo")
    public ResponseEntity<?> getConsumoTotalPorDispositivo() {

        // 1. OBTENER EL USUARIO LOGUEADO DESDE EL TOKEN
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Usuario u = uR.findByNombre(username).orElse(null);

        if (u == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado.");
        }

        // 2. LLAMAR AL SERVICIO PASANDO EL ID DEL USUARIO
        List<CantidadTransaccionesDTO> listaDTO = new ArrayList<>();
        List<Object[]> fila = cS.getConsumoTotalByDispositivo(u.getIdUsuario()); // <--- Pasamos el ID aquí

        if (fila.isEmpty()) {
            // Devolvemos lista vacía en lugar de 404 para que el gráfico simplemente se muestre vacío y no dé error en consola
            return ResponseEntity.ok(new ArrayList<>());
        }

        List<CantConsumoDispDTO> listaResultados = new ArrayList<>();

        for (Object[] columna : fila) {
            CantConsumoDispDTO dto = new CantConsumoDispDTO();
            dto.setIdDispositivo(((Number) columna[0]).intValue());
            dto.setIdUsuario(((Number) columna[1]).intValue());
            dto.setNombreDispositivo((String) columna[2]);
            dto.setTotalConsumo((BigDecimal) columna[3]);

            listaResultados.add(dto);
        }

        return ResponseEntity.ok(listaResultados);
    }


    @GetMapping("/FechaConsumo")
    public ResponseEntity<?> getImpactoEcologicoMensual(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate) {

        List<ImpactoEcologicoMensualDTO> listaDTO = new ArrayList<>();

        // 1. Llama al servicio que agrupa por mes/año y suma el impacto
        List<Object[]> resultados = cS.getImpactoEcologicoMensual(startDate, endDate);

        if (resultados.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron registros de impacto en el rango de fechas especificado.");
        }

        // 2. Mapeo seguro de Object[] a DTO
        for (Object[] columna : resultados) {
            ImpactoEcologicoMensualDTO dto = new ImpactoEcologicoMensualDTO();

            // Columna 0: Mes/Año como String (Ej: "2025-09")
            dto.setMesAnio((String) columna[0]);

            // Columna 1: Impacto total (SUM(valor) como BigDecimal)
            dto.setImpactoTotal((BigDecimal) columna[1]);

            listaDTO.add(dto);
        }

        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/montoAhorrado")
    public ResponseEntity<?> calcularMontoAhorrado(
            @RequestParam("tipo") String tipoConsumo,
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate) {

        List<Object[]> resultado = cS.calcularMontoAhorrado(tipoConsumo, startDate, endDate);

        if (resultado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron registros de consumo para el tipo y periodo especificados.");
        }

        Object[] columna = resultado.get(0);

        String tipo = (String) columna[0];

        BigDecimal consumoReal = new BigDecimal(columna[1].toString());

        BigDecimal consumoReferencia = new BigDecimal(columna[2].toString());

        BigDecimal montoAhorrado = consumoReferencia.subtract(consumoReal);

        MontoAhorradoDTO dto = new MontoAhorradoDTO();
        dto.setTipoConsumo(tipo);
        dto.setMontoAhorrado(montoAhorrado);

        return ResponseEntity.ok(dto);
    }

    // Endpoint 1: Impacto por Tipo de Consumo (Agua, Energía, etc.)
    @GetMapping("/ImpactoPorTipo")
    public ResponseEntity<?> getImpactoTotalByTipo() {
        List<ImpactoPorCategoriaDTO> listaDTO = new ArrayList<>();
        List<Object[]> resultados = cS.getImpactoTotalByTipo();

        if (resultados.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron registros de impacto para este reporte.");
        }

        for (Object[] columna : resultados) {
            ImpactoPorCategoriaDTO dto = new ImpactoPorCategoriaDTO();
            // Columna 0: Tipo (String)
            //dto.setCategoria((String) columna[0]);
            // Columna 1: Impacto total (SUM(valor) como BigDecimal)
            dto.setImpactoTotal(new BigDecimal(columna[0].toString()));

            listaDTO.add(dto);
        }

        return ResponseEntity.ok(listaDTO);
    }

    // Endpoint 2: Impacto por Origen de Consumo (Dispositivo, Grifo, etc.)
    @GetMapping("/ImpactoPorOrigen")
    public ResponseEntity<?> getImpactoTotalByOrigen() {
        List<ImpactoPorCategoriaDTO> listaDTO = new ArrayList<>();
        List<Object[]> resultados = cS.getImpactoTotalByOrigen();

        if (resultados.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron registros de impacto para este reporte.");
        }

        for (Object[] columna : resultados) {
            ImpactoPorCategoriaDTO dto = new ImpactoPorCategoriaDTO();
            // Columna 0: OrigenConsumo (String)
            dto.setCategoria((String) columna[0]);
            // Columna 1: Impacto total (SUM(valor) como BigDecimal)
            dto.setImpactoTotal(new BigDecimal(columna[1].toString()));

            listaDTO.add(dto);
        }
        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/grafico-semanal")
    public ResponseEntity<List<ConsumoGraficoDTO>> getGraficoSemanal() {
        // Obtenemos el usuario autenticado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Usuario u = uR.findByNombre(username).orElse(null);

        if (u == null) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(cS.obtenerConsumoSemanal(u.getIdUsuario()));
    }

}
