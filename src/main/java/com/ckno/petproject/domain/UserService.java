package com.ckno.petproject.domain;

import com.ckno.petproject.application.dto.UserDto;
import com.ckno.petproject.domain.entity.User;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(UserDto user) {
        return userRepository
                .findByNameAndPassword(user.getName(), user.getPassword())
                .orElseThrow();
    }
}
