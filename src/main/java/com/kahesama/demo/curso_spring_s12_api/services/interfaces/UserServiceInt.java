package com.kahesama.demo.curso_spring_s12_api.services.interfaces;

import com.kahesama.demo.curso_spring_s12_api.model.User;

import java.util.List;

public interface UserServiceInt {
    List<User> findAll();

    User findById(Long id);

    User save(User product);

    boolean existsByUsername(String username);

    User findByUsername(String username);

    void deleteById(Long id);
//    Product update(Long id, Product product);
//
//
//    boolean existsBySku(String sku);

}
