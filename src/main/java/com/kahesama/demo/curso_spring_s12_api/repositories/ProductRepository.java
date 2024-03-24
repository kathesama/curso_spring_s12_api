package com.kahesama.demo.curso_spring_s12_api.repositories;

import com.kahesama.demo.curso_spring_s12_api.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
