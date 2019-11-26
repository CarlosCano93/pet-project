package com.ckno.petproject.infrastructure;

import com.ckno.petproject.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<User, Serializable> {
    Optional<User> findByNameAndPassword(String name, String password);
}
