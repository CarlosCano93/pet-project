package com.ckno.petproject.domain.port;

import com.ckno.petproject.domain.entity.User;

public interface SignUpClientPort {

    User signUp(User user);
}
