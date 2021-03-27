package com.ckno.petproject.adapters.users.entity;

import com.ckno.petproject.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String password;

    public static UserEntity from(User user) {
        return UserEntity.builder()
                .name(user.getName())
                .password(user.getPassword())
                .build();
    }
}
