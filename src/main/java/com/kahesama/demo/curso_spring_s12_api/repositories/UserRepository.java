package com.kahesama.demo.curso_spring_s12_api.repositories;

import com.kahesama.demo.curso_spring_s12_api.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
