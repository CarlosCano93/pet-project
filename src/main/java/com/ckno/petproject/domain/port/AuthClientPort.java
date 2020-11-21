package com.ckno.petproject.domain.port;

import com.ckno.petproject.adapters.entity.UserEntity;
import com.ckno.petproject.domain.model.User;

import java.util.Optional;

public interface AuthClientPort {

    Optional<UserEntity> login(User user);

    UserEntity signUp(User user);
}
