package com.ckno.petproject.infrastructure;

import com.ckno.petproject.domain.entity.User;
import com.ckno.petproject.domain.port.SignUpClientPort;

public class SignUpClientAdapter implements SignUpClientPort {

    private UserRepository userRepository;

    public SignUpClientAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signUp(User user) {
        return userRepository.save(user);
    }
}
