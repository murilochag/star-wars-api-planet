package br.com.starwarsapiplanet.starwarsapiplanet.controller;

import br.com.starwarsapiplanet.starwarsapiplanet.domain.Planeta;
import br.com.starwarsapiplanet.starwarsapiplanet.domain.PlanetaDTO;
import br.com.starwarsapiplanet.starwarsapiplanet.domain.PlanetaRepository;
import br.com.starwarsapiplanet.starwarsapiplanet.dto.PlanetaApiDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    @GetMapping("planeta/swapi/{planetaId}")
    public ResponseEntity<PlanetaApiDTO> buscarPlanetaAPI(@PathVariable String planetaId){

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://swapi.dev/api/planets/" + planetaId;

        try {
            ResponseEntity<PlanetaApiDTO> response = restTemplate
                    .getForEntity(url, PlanetaApiDTO.class);

            return ResponseEntity.status(HttpStatus.OK).body(response.getBody());

        } catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

//        System.out.println("resposta: " + response);
//        System.out.println("corpo da resposta: " + response.getBody());

//        if(){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }


    }

}
