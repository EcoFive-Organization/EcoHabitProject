package pe.edu.upc.ecohabitproyecto.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecohabitproyecto.dtos.ChallengesDTO;
import pe.edu.upc.ecohabitproyecto.entities.Challenges;
import pe.edu.upc.ecohabitproyecto.servicesinterfaces.IChallengesService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/challenges")
public class ChallengesController {
    @Autowired
    private IChallengesService  challengesService;

    @GetMapping
    public List<ChallengesDTO> listar(){
        return challengesService.list().stream().map(x->{
            ModelMapper mapper = new ModelMapper();
            return mapper.map(x,ChallengesDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody ChallengesDTO challengesDTO){
        ModelMapper mapper = new ModelMapper();
        Challenges challenges = mapper.map(challengesDTO,Challenges.class);
        challengesService.insert(challenges);
    }




}
