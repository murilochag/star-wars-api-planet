package br.com.starwarsapiplanet.starwarsapiplanet.service;

import br.com.starwarsapiplanet.starwarsapiplanet.dto.PlanetaApiDTO;
import br.com.starwarsapiplanet.starwarsapiplanet.swapi.Result;
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

    public PlanetaApiDTO buscarPlanetaPorNome(String nome){

        Result listaPlanetas = buscarPlanetas();

        while (listaPlanetas.next != null){
            List<PlanetaApiDTO> lista = listaPlanetas.results;
            for (PlanetaApiDTO p: lista) {
                if (p.getName().equalsIgnoreCase(nome)){
                    return p;
                }
            }
            listaPlanetas = template.getForEntity(listaPlanetas.next.toString(), Result.class).getBody();
        }
        return null;
    }

    public List<String> buscarAparicoesPorNome(String nome){

        PlanetaApiDTO planeta = buscarPlanetaPorNome(nome);

        if(planeta == null){
            return new ArrayList<>();
        }
        return planeta.getFilms();
    }
}
