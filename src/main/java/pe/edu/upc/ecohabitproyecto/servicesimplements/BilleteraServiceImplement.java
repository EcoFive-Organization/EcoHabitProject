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
    public List<Billetera> list(){
        // Listar
        return bR.findAll();
    }

    @Override
    public void insert(Billetera billetera) {
        // Registrar
        bR.save(billetera);
    }

    @Override
    public BigDecimal getSaldoPuntos(int idUsuario) {
        // Buscar la billetera del usuario y devolver el saldo
        return bR.findByUsuarioIdUsuario(idUsuario)
                .map(Billetera::getSaldo)
                .orElse(BigDecimal.ZERO);
    }
}
