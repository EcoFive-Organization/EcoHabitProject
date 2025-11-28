package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Consumo;
import pe.edu.upc.ecohabitproyecto.entities.Dispositivo;
import pe.edu.upc.ecohabitproyecto.entities.Usuario;
import pe.edu.upc.ecohabitproyecto.repositories.IConsumoRepository;
import pe.edu.upc.ecohabitproyecto.repositories.IUsuarioRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IConsumoService;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public List<Object[]> getConsumoTotalByDispositivo() {
        return cR.getConsumoTotalByDispositivo();
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

}
