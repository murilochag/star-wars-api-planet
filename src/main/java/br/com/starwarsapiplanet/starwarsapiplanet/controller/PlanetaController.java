package br.com.starwarsapiplanet.starwarsapiplanet.controller;

import br.com.starwarsapiplanet.starwarsapiplanet.domain.Planeta;
import br.com.starwarsapiplanet.starwarsapiplanet.dto.PlanetaDTO;
import br.com.starwarsapiplanet.starwarsapiplanet.domain.PlanetaRepository;
import br.com.starwarsapiplanet.starwarsapiplanet.dto.PlanetaDTOResponse;
import br.com.starwarsapiplanet.starwarsapiplanet.service.PlanetaSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlanetaController {

    @Autowired
    PlanetaSevice planetaSevice;


    @PostMapping("/planeta")
    public ResponseEntity<PlanetaDTOResponse> cadastrar(@RequestBody PlanetaDTO planetaDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(planetaSevice.salvar(planetaDTO));
    }

    @GetMapping("/planeta")
    public ResponseEntity<List<PlanetaDTOResponse>> buscartodos(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(planetaSevice.buscarTodos(pageable));
    }

    @GetMapping("planeta/{planetaID}")
    public ResponseEntity<PlanetaDTOResponse> buscarPorId(@PathVariable Integer planetaID){
        if(planetaSevice.buscarPorId(planetaID) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(planetaSevice.buscarPorId(planetaID));
    }

    @GetMapping("planeta/{nomePlaneta}/nome")
    public ResponseEntity<PlanetaDTOResponse> buscarPornome(@PathVariable String nomePlaneta){
        if(planetaSevice.buscarPorNome(nomePlaneta) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(planetaSevice.buscarPorNome(nomePlaneta));
    }

    @PutMapping("planeta")
    public ResponseEntity<PlanetaDTOResponse> alterar(@RequestBody PlanetaDTO planetaDTO){
        return ResponseEntity.status(HttpStatus.OK).body(planetaSevice.atualizar(planetaDTO));
    }

    @DeleteMapping("planeta/{planetaID}")
    public ResponseEntity<PlanetaDTO> excluir(@PathVariable Integer planetaID){

        if(planetaSevice.deletar(planetaID)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
