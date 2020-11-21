package com.ckno.petproject.application;

import com.ckno.petproject.adapters.entity.UserEntity;
import com.ckno.petproject.application.dto.UserDto;
import com.ckno.petproject.domain.AuthService;
import io.sentry.Sentry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class Controller {
    private final AuthService authService;

    public Controller(final AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public String sayHey() {
        Sentry.capture("Hello Sentry!");
        return "Hello Pet";
    }

    @PostMapping("/login")
    public ResponseEntity<UserEntity> login(@Valid @RequestBody UserDto userDto) {
        Sentry.capture("Controller.login: " + userDto.toString());
        return ResponseEntity.ok(authService.login(userDto.toUser()));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> signUp(@Valid @RequestBody UserDto userDto) {
        Sentry.capture("Controller.signUp: " + userDto.toString());
        return ResponseEntity.ok(authService.signUp(userDto.toUser()));
    }
}
