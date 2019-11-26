package com.ckno.petproject.application.dto;

import com.ckno.petproject.domain.entity.User;
import lombok.*;

@Data
@Builder
public class UserDto {
    private String name;
    private String password;

    public User toUser() {
        return User.builder()
                .name(this.name)
                .password(this.password)
                .build();
    }
}
