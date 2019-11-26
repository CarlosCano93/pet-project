package com.ckno.petproject.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Builder
@Entity
public class User {

    @Id
    private long id;
    private String name;
    private String password;
}
