package com.ckno.petproject.domain.port;

import com.ckno.petproject.domain.entity.User;

import java.util.Optional;

public interface LoginClientPort {

    Optional<User> login(String username, String password);
}
