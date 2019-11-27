package com.ckno.petproject.infrastructure;

import com.ckno.petproject.domain.port.LoginClientPort;
import com.ckno.petproject.domain.entity.User;

import java.util.Optional;

public class LoginClientAdapter implements LoginClientPort {

    private LoginRepository loginRepository;

    public LoginClientAdapter(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public Optional<User> login(String username, String password) {
        return loginRepository.findByNameAndPassword(username, password);
    }
}
