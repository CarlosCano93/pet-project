package com.ckno.petproject.pokemon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PokemonService {

    private final PokeApiClient pokeApiClient;

    public Pokemon getPokemonBy(String pokemonName) {
        return pokeApiClient.findPokemonBy(pokemonName)
                .orElseThrow(() -> new PokemonNotFoundException(
                        String.format("Pokemon %s not found", pokemonName)));
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Pokemon(String name, String type) {}
}
