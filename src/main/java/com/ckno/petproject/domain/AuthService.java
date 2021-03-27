package com.ckno.petproject.domain;

import com.ckno.petproject.adapters.users.entity.UserEntity;
import com.ckno.petproject.domain.model.User;
import com.ckno.petproject.domain.port.AuthClientPort;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthClientPort loginClient;

    public AuthService(final AuthClientPort loginClient) {
        this.loginClient = loginClient;
    }

    public UserEntity login(User user) {
        return loginClient.login(user).orElseThrow();
    }

    public UserEntity signUp(User user) {
        return loginClient.signUp(user);
    }
}
