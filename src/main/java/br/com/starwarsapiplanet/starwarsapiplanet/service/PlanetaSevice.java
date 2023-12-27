package br.com.starwarsapiplanet.starwarsapiplanet.service;

import br.com.starwarsapiplanet.starwarsapiplanet.domain.Planeta;
import br.com.starwarsapiplanet.starwarsapiplanet.domain.PlanetaRepository;
import br.com.starwarsapiplanet.starwarsapiplanet.dto.PlanetaDTO;
import br.com.starwarsapiplanet.starwarsapiplanet.dto.PlanetaDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanetaSevice {

    @Autowired
    private PlanetaRepository planetaRepository;

    @Autowired
    private SwapiService swapiService;

    public PlanetaDTOResponse salvar(PlanetaDTO planetaDTO){
        Planeta planeta = planetaDTO.toPlaneta();
        planetaRepository.save(planeta);
        return new PlanetaDTOResponse(planeta);
    }

    public List<PlanetaDTOResponse> buscarTodos(Pageable pageable){
        List<Planeta> planetas = planetaRepository.findAll(pageable).stream().toList();
        List<PlanetaDTOResponse> listPlanetasDtoResponse = new ArrayList<>();
        planetas.forEach(p -> listPlanetasDtoResponse.add(new PlanetaDTOResponse(p)));
        return listPlanetasDtoResponse;
    }

    public PlanetaDTOResponse buscarPorId(Integer id){
        if(planetaRepository.existsById(id)){
            Planeta planeta = planetaRepository.findById(id).get();
            return new PlanetaDTOResponse(planeta);
        }
        return null;
    }

    public PlanetaDTOResponse buscarPorNome(String nome){

        if (!planetaRepository.existsByNome(nome)){
            return null;
        }
        Planeta planeta = planetaRepository.findByNomeIgnoreCase(nome).get(0);
        PlanetaDTOResponse planetaDTOResponse = new PlanetaDTOResponse(planeta);
        return planetaDTOResponse;
    }

    @Transactional
    public PlanetaDTOResponse atualizar(PlanetaDTO planetaDTO){
        Planeta planeta = planetaRepository.getReferenceById(planetaDTO.id());
        Planeta planetaAtualizado = planeta.atualizar(planetaDTO);
        PlanetaDTO planetaDTOAtualizado = planetaAtualizado.toPlanetaDto();
        return new PlanetaDTOResponse(planetaDTOAtualizado.toPlaneta());
    }

    public Boolean deletar( Integer id){
        if(!planetaRepository.existsById(id)){
            return false;
        } else {
            planetaRepository.deleteById(id);
            return true;
        }
    }
}
