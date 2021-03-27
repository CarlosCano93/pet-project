package com.ckno.petproject.adapters.users;

import com.ckno.petproject.adapters.users.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Serializable> {
    Optional<UserEntity> findByNameAndPassword(String name, String password);
}
