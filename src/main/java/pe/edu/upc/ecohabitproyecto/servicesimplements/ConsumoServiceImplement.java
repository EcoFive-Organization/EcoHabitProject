package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.dtos.ConsumoGraficoDTO;
import pe.edu.upc.ecohabitproyecto.entities.Consumo;
import pe.edu.upc.ecohabitproyecto.entities.Dispositivo;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;
import pe.edu.upc.ecohabitproyecto.repositories.IConsumoRepository;
import pe.edu.upc.ecohabitproyecto.repositories.IUsuarioRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IConsumoService;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ConsumoServiceImplement implements IConsumoService {

    @Autowired
    private IConsumoRepository cR;

    @Autowired
    private IUsuarioRepository uR;

    @Override
    public List<Consumo> list() {
        // 1. Obtener usuario logueado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        // 2. Verificar si es Admin
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"));

        if (isAdmin) {
            // ADMIN: Ve todo
            return cR.findAllWithDispositivoAndUsuario();
        } else {
            // CLIENTE: Buscamos su ID y filtramos
            Usuario u = uR.findByNombre(username).orElse(null);

            if (u == null) return Collections.emptyList();

            // Usamos el nuevo método del repositorio
            return cR.findByUsuarioId(u.getIdUsuario());
        }
    }

    @Override
    public void insert(Consumo consumo) { cR.save(consumo); }

    @Override
    public List<Object[]> findAllByTipoConsumo() {
        return cR.findAllByTipoConsumo();
    }

    @Override
    public List<Object[]> getByTotalConsumoTipo(String tipoConsumo) {
        return cR.getByTotalConsumoTipo(tipoConsumo);
    }

    @Override
    public List<Object[]> getConsumoByDispositivo() {
        return cR.getConsumoByDispositivo();
    }

    @Override
    public List<Object[]> getConsumoTotalByDispositivo(Integer idUsuario) {
        return cR.getConsumoTotalByDispositivo(idUsuario);
    }

    @Override
    public List<Object[]> getImpactoEcologicoMensual(LocalDate startDate, LocalDate endDate) {
        return cR.getImpactoEcologicoMensual(startDate, endDate);
    }

    @Override
    public List<Object[]> calcularMontoAhorrado(String tipoConsumo, LocalDate startDate, LocalDate endDate) {
        return cR.calcularMontoAhorrado(tipoConsumo, startDate, endDate);
    }

    @Override
    public List<Object[]> getImpactoTotalByTipo() {
        return cR.getImpactoTotalByTipo();
    }

    @Override
    public List<Object[]> getImpactoTotalByOrigen() {
        return cR.getImpactoTotalByOrigen();
    }

    @Override
    public List<ConsumoGraficoDTO> obtenerConsumoSemanal(Integer idUsuario, int weeksAgo) {
        // Restamos las semanas solicitadas a la fecha de hoy
        LocalDate hoy = LocalDate.now().minusWeeks(weeksAgo);

        LocalDate lunes = hoy.with(java.time.temporal.TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
        LocalDate domingo = hoy.with(java.time.temporal.TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY));

        List<Object[]> resultados = cR.obtenerConsumoDiarioPorRango(idUsuario, lunes, domingo);

        // Mapeamos a DTO
        List<ConsumoGraficoDTO> dtos = new ArrayList<>();
        for (Object[] fila : resultados) {
            java.sql.Date sqlDate = (java.sql.Date) fila[0];
            LocalDate fechaConvertida = sqlDate.toLocalDate();
            dtos.add(new ConsumoGraficoDTO(fechaConvertida, (String) fila[1], (BigDecimal) fila[2]));
        }
        return dtos;
    }

}
