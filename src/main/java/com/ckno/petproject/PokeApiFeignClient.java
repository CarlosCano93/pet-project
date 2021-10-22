package com.ckno.petproject;

import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@FeignClient(name = "pokeapi",
             url = "https://pokeapi.co/api/v2",
             configuration = PokeApiFeignConfiguration.class)
interface PokeApiFeignClient {

    @GetMapping("/pokemon/{name}")
    PokemonDto getPokemon(@PathVariable String name);
}

class PokeApiFeignConfiguration {
    @Bean
    public ErrorDecoder errorDecoder() {
        return (s, response) -> {
            if (response.status() == 404) throw new PokemonNotFoundException(
                    "Pokemon doesn't exists. Try another!");

            throw new ResponseStatusException(HttpStatus.valueOf(response.status()));
        };
    }
}
