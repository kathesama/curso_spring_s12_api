package com.kahesama.demo.curso_spring_s12_api.services.interfaces;

import com.kahesama.demo.curso_spring_s12_api.model.User;

import java.util.List;

public interface UserServiceInt {
    List<User> findAll();

//    Product findById(Long id);
//
    User save(User product);
//
//    Product update(Long id, Product product);
//
//    void deleteById(Long id);
//
//    boolean existsBySku(String sku);

}
