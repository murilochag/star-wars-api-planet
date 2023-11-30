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
import java.util.Optional;

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

        planetas.forEach(planeta -> planetasDTO.add(planeta.toPlanetaDto()));

        return ResponseEntity.status(HttpStatus.OK).body(planetasDTO);
    }

    @GetMapping("planeta/{id}")
    public ResponseEntity<PlanetaDTO> buscarPorId(@PathVariable Integer id){

        PlanetaDTO planetaDTO = planetaRepository.findById(id).get().toPlanetaDto();

        return ResponseEntity.status(HttpStatus.OK).body(planetaDTO);
    }

    @GetMapping("planeta/{nomePlaneta}/nome")
    public ResponseEntity<PlanetaDTO> buscarPornome(@PathVariable String nomePlaneta){

        PlanetaDTO planetaDTO = planetaRepository.buscarPlanetaPorNome(nomePlaneta).get(0).toPlanetaDto();

        return ResponseEntity.status(HttpStatus.OK).body(planetaDTO);
    }

}
