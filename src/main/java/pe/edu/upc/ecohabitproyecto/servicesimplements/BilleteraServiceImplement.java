package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Billetera;
import pe.edu.upc.ecohabitproyecto.repositories.IBilleteraRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IBilleteraService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BilleteraServiceImplement implements IBilleteraService {

    @Autowired
    private IBilleteraRepository bR;


    @Override
    public List<Billetera> list() {
        return bR.findAll();
    }

    @Override
    public void insert(Billetera billetera) {
        bR.save(billetera);
    }

    @Override
    public BigDecimal getSaldoPuntos(int idUsuario) {
        return bR.findByUsuarioIdUsuario(idUsuario)
                .map(Billetera::getSaldo)
                .orElse(BigDecimal.ZERO);
    }


    @Override
    public void acumularPuntos(Integer idUsuario, Double puntos) {
        Billetera billetera = bR.findByUsuarioIdUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("Billetera no encontrada"));

        billetera.setSaldo(
                billetera.getSaldo().add(BigDecimal.valueOf(puntos))
        );

        bR.save(billetera);
    }

    @Override
    public Billetera obtenerBilleteraPorUsuario(Integer idUsuario) {
        return bR.findByUsuarioIdUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("Billetera no encontrada"));
    }
}
