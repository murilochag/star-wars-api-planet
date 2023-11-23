package br.com.starwarsapiplanet.starwarsapiplanet.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "planetas")
public class Planeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private PlanetaClima clima;

    public PlanetaClima getClima() {
        return clima;
    }

    public void setClima(PlanetaClima clima) {
        this.clima = clima;
    }

    private String terreno;


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


    public String getTerreno() {
        return terreno;
    }

    public void setTerreno(String terreno) {
        this.terreno = terreno;
    }

    public PlanetaDTO toPlanetaDto(){

        PlanetaDTO planetaDTO = new PlanetaDTO(
                this.nome,
                this.clima,
                this.terreno
        );
        return planetaDTO;
    }
}
