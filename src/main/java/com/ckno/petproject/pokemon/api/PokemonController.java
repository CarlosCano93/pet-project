package com.ckno.petproject.pokemon.api;

import com.ckno.petproject.pokemon.domain.PokemonService;
import com.ckno.petproject.pokemon.domain.PokemonService.Pokemon;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("v1/pokemon")
@RequiredArgsConstructor
class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping("{name}/exists")
    public void pokemonExists(@PathVariable String name) {
        pokemonService.getPokemonBy(name);
    }

    @GetMapping("{name}")
    public PokemonResponse getPokemon(@PathVariable String name) {
        Pokemon pokemon = pokemonService.getPokemonBy(name);
        return new PokemonResponse(pokemon.name(), pokemon.type());
    }

    private record PokemonResponse(String name, String type) {}
}

