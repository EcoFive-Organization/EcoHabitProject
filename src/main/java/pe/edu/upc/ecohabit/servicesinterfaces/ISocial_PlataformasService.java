package pe.edu.upc.ecohabit.servicesinterfaces;

import pe.edu.upc.ecohabit.entities.Social_Plataformas;
import java.util.List;

public interface ISocial_PlataformasService {
    List<Social_Plataformas> list();
    Social_Plataformas insert(Social_Plataformas e);   // <-- retorna entidad
}