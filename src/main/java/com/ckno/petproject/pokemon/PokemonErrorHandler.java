package com.ckno.petproject.pokemon;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.logging.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Log4j2
class PokemonErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PokemonNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePokemonNotFound(
            final PokemonNotFoundException exception) {
        log.error(exception.getCause(), exception);
        return new ResponseEntity<>(
                new ErrorResponse(exception.getMessage(), ErrorCode.POKEMON_NOT_FOUND),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            final Exception exception) {

        log.error(exception.getCause(), exception);

        return new ResponseEntity<>(
                new ErrorResponse(exception.getMessage(), ErrorCode.INTERNAL_SERVER_ERROR),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private record ErrorResponse(String error, ErrorCode errorCode) {}

    enum ErrorCode {POKEMON_NOT_FOUND, INTERNAL_SERVER_ERROR}
}


class PokemonNotFoundException extends RuntimeException {
    public PokemonNotFoundException(String message) {
        super(message);
    }
}
