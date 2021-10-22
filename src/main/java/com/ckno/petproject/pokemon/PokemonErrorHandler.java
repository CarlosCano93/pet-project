package com.ckno.petproject.pokemon;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
