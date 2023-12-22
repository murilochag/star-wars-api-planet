package br.com.starwarsapiplanet.starwarsapiplanet.controller;

import br.com.starwarsapiplanet.starwarsapiplanet.domain.Planeta;
import br.com.starwarsapiplanet.starwarsapiplanet.dto.PlanetaDTO;
import br.com.starwarsapiplanet.starwarsapiplanet.domain.PlanetaRepository;
import br.com.starwarsapiplanet.starwarsapiplanet.dto.PlanetaApiDTO;
import br.com.starwarsapiplanet.starwarsapiplanet.dto.PlanetaDTOResponse;
import br.com.starwarsapiplanet.starwarsapiplanet.service.SwapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PlanetaController {

    @Autowired
    PlanetaRepository planetaRepository;

    @Autowired
    SwapiService swapiService;


    @PostMapping("/planeta")
    public ResponseEntity<Planeta> cadastrar(@RequestBody PlanetaDTO planetaDTO){

        Planeta planeta = planetaDTO.toPlaneta();
        planetaRepository.save(planeta);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/planeta")
    public ResponseEntity<List<PlanetaDTOResponse>> buscartodos(){

        List<Planeta> planetas = planetaRepository.findAll();
        List<PlanetaDTOResponse> planetasDtoResponse = new ArrayList<>();

        planetas.forEach(planeta ->
                planetasDtoResponse.add(new PlanetaDTOResponse(
                        planeta.getId(),
                        planeta.getNome(),
                        planeta.getClima(),
                        planeta.getTerreno(),
                        swapiService.buscarAparicoesPorNome(planeta.getNome()))));

        return ResponseEntity.status(HttpStatus.OK).body(planetasDtoResponse);
    }

    @GetMapping("planeta/{id}")
    public ResponseEntity<PlanetaDTO> buscarPorId(@PathVariable Integer id){

        PlanetaDTO planetaDTO = planetaRepository.findById(id).get().toPlanetaDto();

        return ResponseEntity.status(HttpStatus.OK).body(planetaDTO);
    }

    @GetMapping("planeta/{nomePlaneta}/nome")
    public ResponseEntity<PlanetaDTO> buscarPornome(@PathVariable String nomePlaneta){

        PlanetaDTO planetaDTO = planetaRepository.findByNomeIgnoreCase(nomePlaneta).get(0).toPlanetaDto();

        return ResponseEntity.status(HttpStatus.OK).body(planetaDTO);
    }

    @PutMapping("planeta")
    @Transactional
    public ResponseEntity<PlanetaDTO> alterar(@RequestBody PlanetaDTO planetaDTO){

        Planeta planeta = planetaRepository.getReferenceById(planetaDTO.id());
        Planeta planetaAtualizado = planeta.atualizar(planetaDTO);

        PlanetaDTO planetaDTOAtualizado = planetaAtualizado.toPlanetaDto();
        
        return ResponseEntity.status(HttpStatus.OK).body(planetaDTOAtualizado);
    }

    @DeleteMapping("planeta/{planetaID}")
    public ResponseEntity<PlanetaDTO> excluir(@PathVariable Integer planetaID){

        if(!planetaRepository.existsById(planetaID)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        planetaRepository.deleteById(planetaID);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("planeta/swapi/{nome}")
    public ResponseEntity<PlanetaApiDTO> buscarPlanetaAPI(@PathVariable String nome){

        PlanetaApiDTO planeta = swapiService.buscarPlanetaPorNome(nome);

        return ResponseEntity.status(HttpStatus.OK).body(planeta);
    }

}
