package com.ckno.petproject.domain;

import com.ckno.petproject.application.dto.UserDto;
import com.ckno.petproject.domain.entity.User;
import com.ckno.petproject.domain.port.LoginClientPort;

public class LoginService {
    private LoginClientPort loginClient;

    public LoginService(LoginClientPort loginClient) {
        this.loginClient = loginClient;
    }

    public User login(UserDto user) {
        return loginClient
                .login(user.getName(), user.getPassword())
                .orElseThrow();
    }
}
