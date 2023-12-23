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

    @Autowired
    PlanetaRepository planetaRepository;

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

    public List<PlanetaDTOResponse> contruirLista(List<Planeta> planetas, List<PlanetaApiDTO> lista){

        List<PlanetaDTOResponse> planetasDtoResponse = new ArrayList<>();

        for (Planeta planeta: planetas) {
            planetasDtoResponse.add(new PlanetaDTOResponse(
                        planeta.getId(),
                        planeta.getNome(),
                        planeta.getClima(),
                        planeta.getTerreno(),
                        buscarAparicoesPorNome(planeta.getNome(), lista)
                    )
            );
        }
        return planetasDtoResponse;

    }

    public List<String> buscarAparicoesPorNome(String nome, List<PlanetaApiDTO> lista){

        for(PlanetaApiDTO planeta: lista){
            if(planeta.getName().equalsIgnoreCase(nome) && planeta.getFilms() != null){
                return planeta.getFilms();
            }
        }
        return null;
    }

    public List<PlanetaApiDTO> listarPlantasApi(){

        Result result = buscarPlanetas();
        List<PlanetaApiDTO> lista = result.results;

        while (result.next != null){
            lista.addAll(result.results);
            result = template.getForEntity(result.next, Result.class).getBody();
        }
        System.out.println("acabou de listar");
        return lista;
    }
}
