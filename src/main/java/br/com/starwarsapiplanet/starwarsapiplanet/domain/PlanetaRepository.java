package br.com.starwarsapiplanet.starwarsapiplanet.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlanetaRepository extends JpaRepository<Planeta, Integer> {

    @Query("from Planeta p where p.nome = :nomePlaneta")
    List<Planeta> buscarPlanetaPorNome(String nomePlaneta);


}
