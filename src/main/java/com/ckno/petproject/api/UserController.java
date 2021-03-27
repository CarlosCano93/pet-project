package com.ckno.petproject.api;

import com.ckno.petproject.adapters.users.entity.UserEntity;
import com.ckno.petproject.api.dto.UserDto;
import com.ckno.petproject.domain.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/v1")
public class UserController {
    private final AuthService authService;

    public UserController(final AuthService authService) {
        this.authService = authService;
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
