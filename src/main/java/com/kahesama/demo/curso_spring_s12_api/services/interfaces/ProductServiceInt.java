package com.kahesama.demo.curso_spring_s12_api.services.interfaces;

import com.kahesama.demo.curso_spring_s12_api.model.Product;

import java.util.List;

public interface ProductServiceInt {
    List<Product> findAll();

    Product findById(Long id);

    Product save(Product product);

    Product update(Long id, Product product);

    void deleteById(Long id);

    boolean existsBySku(String sku);

}
