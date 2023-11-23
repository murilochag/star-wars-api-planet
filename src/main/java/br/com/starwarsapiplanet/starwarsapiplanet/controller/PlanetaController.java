package br.com.starwarsapiplanet.starwarsapiplanet.controller;

import br.com.starwarsapiplanet.starwarsapiplanet.domain.Planeta;
import br.com.starwarsapiplanet.starwarsapiplanet.domain.PlanetaDTO;
import br.com.starwarsapiplanet.starwarsapiplanet.domain.PlanetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PlanetaController {

    @Autowired
    PlanetaRepository planetaRepository;


    @PostMapping("/planeta")
    public ResponseEntity<Planeta> cadastrar(@RequestBody PlanetaDTO planetaDTO){

        Planeta planeta = planetaDTO.toPlaneta();
        planetaRepository.save(planeta);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/planeta")
    public ResponseEntity<List<PlanetaDTO>> buscartodos(){

        List<Planeta> planetas = planetaRepository.findAll();
        List<PlanetaDTO> planetasDTO = new ArrayList<>();

        for (Planeta planeta: planetas) {
            planetasDTO.add(planeta.toPlanetaDto());
        }

        return ResponseEntity.status(HttpStatus.OK).body(planetasDTO);
    }

}
