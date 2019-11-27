package com.ckno.petproject.application;

import com.ckno.petproject.application.dto.UserDto;
import com.ckno.petproject.domain.LoginService;
import com.ckno.petproject.domain.SignUpService;
import com.ckno.petproject.domain.entity.User;
import io.sentry.Sentry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/v1")
public class Controller {
    private LoginService loginService;
    private SignUpService signUpService;

    public Controller(LoginService loginService,
                      SignUpService signUpService) {
        this.loginService = loginService;
        this.signUpService = signUpService;
    }

    @GetMapping("/")
    public String sayHey() {
        Sentry.capture("Hello Sentry!");
        return "Hello Pet";
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserDto userDto) {
        try {
            Sentry.capture("login: " + userDto.toString());
            return ResponseEntity.ok(loginService.login(userDto));
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User " + userDto.getName() + " not found in DB",
                    ex);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody UserDto userDto) {
        Sentry.capture("signup" + userDto.toString());
        return ResponseEntity.ok(signUpService.signUp(userDto));
    }
}
