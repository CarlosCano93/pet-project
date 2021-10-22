package com.ckno.petproject.pokemon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PokeApiClient {
    private final PokeApiFeignClient pokeApiFeignClient;

    public Optional<PokemonService.Pokemon> findPokemonBy(String name) {
        return pokeApiFeignClient.findPokemonByName(name)
                .map(PokemonDto::toPokemon);
    }

    @FeignClient(name = "pokeapi",
                 url = "${com.ckno.external.pokeapi.url}",
                 decode404 = true)
    interface PokeApiFeignClient {
        @GetMapping("/pokemon/{name}")
        Optional<PokemonDto> findPokemonByName(@PathVariable String name);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private record PokemonDto(Species species, List<Type> types) {

        public PokemonService.Pokemon toPokemon() {
            return new PokemonService.Pokemon(species.name(), types.get(0).type().name());
        }

        private record Type(int slot, TypeName type) {}

        private record Species(String name) {}

        private record TypeName(String name) {}
    }
}
