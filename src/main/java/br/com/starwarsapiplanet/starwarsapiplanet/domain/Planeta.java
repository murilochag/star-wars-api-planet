package br.com.starwarsapiplanet.starwarsapiplanet.domain;


import br.com.starwarsapiplanet.starwarsapiplanet.dto.PlanetaDTO;
import br.com.starwarsapiplanet.starwarsapiplanet.dto.PlanetaDTOResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


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
                this.id,
                this.nome,
                this.clima,
                this.terreno
        );
        return planetaDTO;
    }

    public Planeta atualizar(PlanetaDTO planetaDTO) {
        if(planetaDTO.nome() != null){
            this.nome = planetaDTO.nome();
        }
        if(planetaDTO.clima() != null){
            this.clima = planetaDTO.clima();
        }
        if(planetaDTO.terreno() != null){
            this.terreno = planetaDTO.terreno();
        }

        return this;
    }
}
