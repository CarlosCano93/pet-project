package com.ckno.petproject.adapters;

import com.ckno.petproject.adapters.entity.UserEntity;
import com.ckno.petproject.domain.model.User;
import com.ckno.petproject.domain.port.AuthClientPort;
import io.sentry.Sentry;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthClientAdapter implements AuthClientPort {

    private final UserRepository userRepository;

    public AuthClientAdapter(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserEntity> login(User user) {
        return userRepository.findByNameAndPassword(user.getName(), user.getPassword());
    }

    @Override
    public UserEntity signUp(User user) {
        Sentry.capture("SignUpClientAdapter: " + user.getName());
        return userRepository.save(UserEntity.from(user));
    }

}
