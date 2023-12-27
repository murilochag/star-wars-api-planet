package br.com.starwarsapiplanet.starwarsapiplanet.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetaRepository extends JpaRepository<Planeta, Integer> {

    @Query("from Planeta p where p.nome = :nomePlaneta")
    List<Planeta> buscarPlanetaPorNome(String nomePlaneta);

    List<Planeta> findByNomeIgnoreCase(String nome);

    boolean existsByNome(String nome);


}
