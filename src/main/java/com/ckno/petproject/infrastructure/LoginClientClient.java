package com.ckno.petproject.infrastructure;

import com.ckno.petproject.domain.LoginClientPort;
import com.ckno.petproject.domain.entity.User;

import java.util.Optional;

public class LoginClientClient implements LoginClientPort {

    private LoginRepository loginRepository;

    public LoginClientClient(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public Optional<User> login(String username, String password) {
        return loginRepository.findByNameAndPassword(username, password);
    }
}
