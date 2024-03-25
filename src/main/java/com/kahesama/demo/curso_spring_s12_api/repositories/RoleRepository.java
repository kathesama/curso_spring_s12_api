package com.kahesama.demo.curso_spring_s12_api.repositories;

import com.kahesama.demo.curso_spring_s12_api.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String name);
}
