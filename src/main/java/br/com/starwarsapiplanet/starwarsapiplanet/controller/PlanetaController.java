package br.com.starwarsapiplanet.starwarsapiplanet.controller;

import br.com.starwarsapiplanet.starwarsapiplanet.domain.Planeta;
import br.com.starwarsapiplanet.starwarsapiplanet.domain.PlanetaDTO;
import br.com.starwarsapiplanet.starwarsapiplanet.domain.PlanetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/planeta")
public class PlanetaController {

    @Autowired
    PlanetaRepository planetaRepository;


    @PostMapping()
    public void cadastrar(@RequestBody PlanetaDTO planetaDTO){
        Planeta planeta = planetaDTO.toPlaneta();
        planetaRepository.save(planeta);
    }

}
