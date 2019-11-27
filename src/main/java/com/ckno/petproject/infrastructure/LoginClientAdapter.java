package com.ckno.petproject.infrastructure;

import com.ckno.petproject.domain.port.LoginClientPort;
import com.ckno.petproject.domain.entity.User;

import java.util.Optional;

public class LoginClientAdapter implements LoginClientPort {

    private UserRepository userRepository;

    public LoginClientAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> login(String username, String password) {
        return userRepository.findByNameAndPassword(username, password);
    }
}
