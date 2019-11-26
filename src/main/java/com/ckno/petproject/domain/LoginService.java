package com.ckno.petproject.domain;

import com.ckno.petproject.application.dto.UserDto;
import com.ckno.petproject.domain.entity.User;

public class LoginService {
    private LoginClientPort userClient;

    public LoginService(LoginClientPort userClient) {
        this.userClient = userClient;
    }

    public User login(UserDto user) {
        return userClient
                .login(user.getName(), user.getPassword())
                .orElseThrow();
    }
}
