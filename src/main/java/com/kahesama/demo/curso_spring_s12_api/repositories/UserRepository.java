package com.kahesama.demo.curso_spring_s12_api.repositories;

import com.kahesama.demo.curso_spring_s12_api.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUsername(String username);
    Optional<UserEntity> findByUsername(String username);
}
