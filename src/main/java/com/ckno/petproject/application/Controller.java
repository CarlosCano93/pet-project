package com.ckno.petproject.application;

import com.ckno.petproject.application.dto.UserDto;
import com.ckno.petproject.domain.LoginService;
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

    public Controller(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/")
    public String sayHey() {
        Sentry.capture("Hello Sentry!");
        return "Hello Pet";
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserDto userDto) {
        try {
            return ResponseEntity.ok(loginService.login(userDto));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "User unauthorized", e);
        }
    }


}
