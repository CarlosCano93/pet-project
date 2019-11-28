package com.ckno.petproject.infrastructure;

import com.ckno.petproject.domain.entity.User;
import com.ckno.petproject.domain.port.SignUpClientPort;
import io.sentry.Sentry;

public class SignUpClientAdapter implements SignUpClientPort {

    private UserRepository userRepository;

    public SignUpClientAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signUp(User user) {
        Sentry.capture("SignUpClientAdapter: " + user.getName());
        return userRepository.save(user);
    }
}
