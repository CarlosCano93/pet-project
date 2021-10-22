package com.ckno.petproject;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class PokemonService {
    private final PokeApiFeignClient pokeApiFeignClient;

    public void pokemonExists(String pokemonName) {
        pokeApiFeignClient.getPokemon(pokemonName);
    }

    public String getPokemonType(String pokemonName) {
        PokemonDto pokemon = pokeApiFeignClient.getPokemon(pokemonName);
        return pokemon.types().get(0).type().name();
    }

}
