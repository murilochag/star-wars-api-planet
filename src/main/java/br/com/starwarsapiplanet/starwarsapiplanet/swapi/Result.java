package br.com.starwarsapiplanet.starwarsapiplanet.swapi;

import br.com.starwarsapiplanet.starwarsapiplanet.dto.PlanetaApiDTO;

import java.util.List;

public class Result {
    public String count;
    public String next;
    public String previous;
    public List<PlanetaApiDTO> results;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<PlanetaApiDTO> getResults() {
        return results;
    }

    public void setResults(List<PlanetaApiDTO> results) {
        this.results = results;
    }
}
