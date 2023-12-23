package br.com.starwarsapiplanet.starwarsapiplanet.controller;

import br.com.starwarsapiplanet.starwarsapiplanet.domain.Planeta;
import br.com.starwarsapiplanet.starwarsapiplanet.dto.PlanetaDTO;
import br.com.starwarsapiplanet.starwarsapiplanet.domain.PlanetaRepository;
import br.com.starwarsapiplanet.starwarsapiplanet.dto.PlanetaDTOResponse;
import br.com.starwarsapiplanet.starwarsapiplanet.service.SwapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

        List<PlanetaDTOResponse> listPlanetasDtoResponse = swapiService.contruirLista(planetas, swapiService.listarPlantasApi());

        return ResponseEntity.status(HttpStatus.OK).body(listPlanetasDtoResponse);
    }

    @GetMapping("planeta/{id}")
    public ResponseEntity<PlanetaDTOResponse> buscarPorId(@PathVariable Integer id){

        Planeta planeta = planetaRepository.findById(id).get();
        PlanetaDTOResponse planetaDTOResponse = new PlanetaDTOResponse(planeta.getId(), planeta.getNome(), planeta.getClima(), planeta.getTerreno(), swapiService.buscarAparicoesPorNome(planeta.getNome(), swapiService.listarPlantasApi()));
        return ResponseEntity.status(HttpStatus.OK).body(planetaDTOResponse);
    }

    @GetMapping("planeta/{nomePlaneta}/nome")
    public ResponseEntity<PlanetaDTOResponse> buscarPornome(@PathVariable String nomePlaneta){

        Planeta planeta = planetaRepository.findByNomeIgnoreCase(nomePlaneta).get(0);
        PlanetaDTOResponse planetaDTOResponse = new PlanetaDTOResponse(planeta.getId(), planeta.getNome(), planeta.getClima(), planeta.getTerreno(), swapiService.buscarAparicoesPorNome(planeta.getNome(), swapiService.listarPlantasApi()));
        return ResponseEntity.status(HttpStatus.OK).body(planetaDTOResponse);
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

//    @GetMapping("planeta/swapi/{nome}")
//    public ResponseEntity<PlanetaApiDTO> buscarPlanetaAPI(@PathVariable String nome){
//
//        PlanetaApiDTO planeta = swapiService.buscarPlanetaPorNome(nome);
//
//        return ResponseEntity.status(HttpStatus.OK).body(planeta);
//    }

}
