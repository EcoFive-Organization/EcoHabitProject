package pe.edu.upc.ecohabitproyecto.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecohabitproyecto.entities.Challenges;
import pe.edu.upc.ecohabitproyecto.repositories.IChallengesRepository;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IChallengesService;

import java.util.List;

@Service
public class ChallengesServiceImplement implements IChallengesService {

    @Autowired
    private IChallengesRepository iChallengesRepository;
    @Override
    public List<Challenges> list(){
        return iChallengesRepository.findAll();
    }

    @Override
    public void insert(Challenges challenges) {
        iChallengesRepository.save(challenges);
    }

}
