package com.ckno.petproject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;


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

@RequiredArgsConstructor
@Service
class PokemonService {
    private final PokeApiFeignClient pokeApiFeignClient;

    public void pokemonExists(String pokemonName) {
        pokeApiFeignClient.getPokemon(pokemonName);
    }

    public String getPokemonType(String pokemonName) {
        PokeApiFeignClient.Pokemon pokemon = pokeApiFeignClient.getPokemon(pokemonName);
        return pokemon.types().get(0).type().name();
    }

}

@FeignClient(name = "pokeapi",
             url = "https://pokeapi.co/api/v2",
             configuration = PokeApiFeignClient.PokeApiFeignConfiguration.class)
interface PokeApiFeignClient {

    @GetMapping("/pokemon/{name}")
    Pokemon getPokemon(@PathVariable String name);

    @JsonIgnoreProperties(ignoreUnknown = true)
    record Pokemon(List<Type> types) {}

    record Type(int slot, TypeName type) {}

    record TypeName(String name) {}

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
}

@ControllerAdvice
class PokemonErrorHandler extends ResponseEntityExceptionHandler {

    private record ErrorResponse(String error, int status) {}

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handlePokeApiException(
            final ResponseStatusException exception) {
        var error = new ErrorResponse(exception.getMessage(),
                                      exception.getStatus().value());
        return new ResponseEntity<>(error, exception.getStatus());
    }

    @ExceptionHandler(PokemonNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePokemonNotFoundException(
            final PokemonNotFoundException exception) {
        var error = new ErrorResponse(exception.getMessage(),
                                      HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}

class PokemonNotFoundException extends RuntimeException {
    public PokemonNotFoundException(String message) {
        super(message);
    }
}

