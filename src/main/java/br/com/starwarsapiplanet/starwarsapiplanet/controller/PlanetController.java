package br.com.starwarsapiplanet.starwarsapiplanet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/planet")
public class PlanetController {

    @GetMapping
    public String planetas(){
        return "<h1><planestas de star wars/h1>";
    }

}
