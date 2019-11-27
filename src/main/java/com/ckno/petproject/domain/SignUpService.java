package com.ckno.petproject.domain;

import com.ckno.petproject.application.dto.UserDto;
import com.ckno.petproject.domain.entity.User;
import com.ckno.petproject.domain.port.SignUpClientPort;

public class SignUpService {

    private SignUpClientPort signUpClient;

    public SignUpService(SignUpClientPort signUpClient) {
        this.signUpClient = signUpClient;
    }

    public User signUp(UserDto user) {
        return signUpClient.signUp(user.toUser());
    }
}
