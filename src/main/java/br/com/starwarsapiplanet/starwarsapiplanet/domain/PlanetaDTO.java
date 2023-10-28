package br.com.starwarsapiplanet.starwarsapiplanet.domain;

import org.springframework.web.bind.annotation.PathVariable;

public record PlanetaDTO(String nome, PlanetaClima clima, String terreno) {


    public Planeta toPlaneta(){
        Planeta planeta = new Planeta();
        planeta.setId(null);
        planeta.setNome(this.nome);
        planeta.setClima(this.clima.toString());
        planeta.setTerreno(this.terreno);
        return planeta;
    }
}
