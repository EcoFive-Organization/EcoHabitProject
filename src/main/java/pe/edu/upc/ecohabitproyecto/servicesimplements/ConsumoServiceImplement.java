package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Consumo;
import pe.edu.upc.ecohabitproyecto.entities.Dispositivo;
import pe.edu.upc.ecohabitproyecto.repositories.IConsumoRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IConsumoService;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsumoServiceImplement implements IConsumoService {

    @Autowired
    private IConsumoRepository cR;

    @Override
    public List<Consumo> list() { return cR.findAll(); }

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
}
