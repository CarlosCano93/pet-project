package com.ckno.petproject.application;

import com.ckno.petproject.adapters.entity.UserEntity;
import com.ckno.petproject.application.dto.UserDto;
import com.ckno.petproject.domain.AuthService;
import io.sentry.Sentry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/v1")
public class Controller {
    private final AuthService authService;

    public Controller(final AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public String sayHey() {
        log.info("Hello Pet");
        return "Hello Pet";
    }

    @PostMapping("/login")
    public ResponseEntity<UserEntity> login(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(authService.login(userDto.toUser()));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> signUp(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(authService.signUp(userDto.toUser()));
    }
}
