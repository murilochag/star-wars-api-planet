package br.com.starwarsapiplanet.starwarsapiplanet.dto;

import br.com.starwarsapiplanet.starwarsapiplanet.domain.PlanetaClima;

import java.util.List;

public record PlanetaDTOResponse(Integer id, String nome, PlanetaClima clima, String terreno, List<String> aparicoes) {
}
