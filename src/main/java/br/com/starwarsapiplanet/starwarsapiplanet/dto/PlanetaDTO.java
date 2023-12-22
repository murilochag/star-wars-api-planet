package br.com.starwarsapiplanet.starwarsapiplanet.dto;

import br.com.starwarsapiplanet.starwarsapiplanet.domain.Planeta;
import br.com.starwarsapiplanet.starwarsapiplanet.domain.PlanetaClima;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public record PlanetaDTO(Integer id, String nome, PlanetaClima clima, String terreno) {


    public Planeta toPlaneta(){
        Planeta planeta = new Planeta();
        planeta.setId(null);
        planeta.setNome(this.nome);
        planeta.setClima(this.clima);
        planeta.setTerreno(this.terreno);
        return planeta;
    }


}
