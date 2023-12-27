package br.com.starwarsapiplanet.starwarsapiplanet.dto;

import br.com.starwarsapiplanet.starwarsapiplanet.domain.Planeta;
import br.com.starwarsapiplanet.starwarsapiplanet.domain.PlanetaClima;
import br.com.starwarsapiplanet.starwarsapiplanet.service.SwapiService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PlanetaDTOResponse {
    private Integer id;
    private String nome;
    private PlanetaClima clima;
    private String terreno;
    private List<String> aparicoes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public PlanetaClima getClima() {
        return clima;
    }

    public void setClima(PlanetaClima clima) {
        this.clima = clima;
    }

    public String getTerreno() {
        return terreno;
    }

    public void setTerreno(String terreno) {
        this.terreno = terreno;
    }

    public List<String> getAparicoes() {
        return aparicoes;
    }

    public void setAparicoes(List<String> aparicoes) {
        this.aparicoes = aparicoes;
    }

    public PlanetaDTOResponse(Planeta planeta){
        this.id = planeta.getId();
        this.nome = planeta.getNome();
        this.clima = planeta.getClima();
        this.terreno = planeta.getTerreno();
        SwapiService swapiService = new SwapiService();
        this.aparicoes = swapiService.buscarAparicaoPorNome(planeta.getNome());
    }
}
