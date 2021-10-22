package com.ckno.petproject;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("v1/pokemon")
@RequiredArgsConstructor
class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping("hello/{name}")
    public String hello(@PathVariable String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("exists/{name}")
    public void pokemonExists(@PathVariable String name) {
        pokemonService.pokemonExists(name);
    }

    @GetMapping("{name}")
    public PokemonResponse getPokemon(@PathVariable String name) {
        String pokemonType = pokemonService.getPokemonType(name);

        return new PokemonResponse(name, pokemonType);
    }

    private record PokemonResponse(String name, String type) {}
}

