package br.com.starwarsapiplanet.starwarsapiplanet.service;

import br.com.starwarsapiplanet.starwarsapiplanet.domain.Planeta;
import br.com.starwarsapiplanet.starwarsapiplanet.domain.PlanetaRepository;
import br.com.starwarsapiplanet.starwarsapiplanet.dto.PlanetaApiDTO;
import br.com.starwarsapiplanet.starwarsapiplanet.dto.PlanetaDTOResponse;
import br.com.starwarsapiplanet.starwarsapiplanet.swapi.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class SwapiService {

    RestTemplate template = new RestTemplate();


    public PlanetaApiDTO buscarPlanetaPorId(String planetaId){
        String url = "https://swapi.dev/api/planets/" + planetaId;
        ResponseEntity<PlanetaApiDTO> response = template
                .getForEntity(url, PlanetaApiDTO.class);
        return response.getBody();
    }

    public Result buscarPlanetas(){
        String url = "https://swapi.dev/api/planets/";
        ResponseEntity<Result> response = template
                .getForEntity(url, Result.class);
        return response.getBody();
    }


    public List<String> buscarAparicaoPorNome(String nome){

        String url = "https://swapi.dev/api/planets/?search=" + nome.toLowerCase();

        List<PlanetaApiDTO> response = template.getForEntity(url, Result.class).getBody().getResults();

        if(response.isEmpty()){
            return null;
        }
        if(response.get(0).getName().equalsIgnoreCase(nome)){
            return response.get(0).getFilms();
        }
        return null;
    }

}
