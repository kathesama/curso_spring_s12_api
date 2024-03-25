package com.kahesama.demo.curso_spring_s12_api.services;

import com.kahesama.demo.curso_spring_s12_api.exceptions.ProductNotFoundException;
import com.kahesama.demo.curso_spring_s12_api.model.Product;
import com.kahesama.demo.curso_spring_s12_api.model.mappers.persistence.ProductPersistenceMapper;
import com.kahesama.demo.curso_spring_s12_api.repositories.ProductRepository;
import com.kahesama.demo.curso_spring_s12_api.services.interfaces.ProductServiceInt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceIntImpl implements ProductServiceInt {
    private final ProductRepository repository;
    private final ProductPersistenceMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return mapper.toProductList(repository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public Product findById(Long id) {
        return repository.findById(id).map(mapper::toProduct)
                .orElseThrow(ProductNotFoundException::new);
    }

//    @Transactional
//    @Override
//    public ProductEntity save(ProductEntity productEntity) {
//        return repository.save(productEntity);
//    }

    @Transactional
    @Override
    public Product save(Product product) {
        return saveFn(product);
    }

    @Transactional
    @Override
    public Product update(Long id, Product productParam) {
        return repository.findById(id)
                .map(mapper::toProduct)
                .map((Product product) -> {
                    product.setName(productParam.getName());
                    product.setDescription(productParam.getDescription());
                    product.setPrice(productParam.getPrice());

                    return saveFn(product);
                }).orElseThrow(ProductNotFoundException::new);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        if(repository.findById(id).isEmpty()){
            throw new ProductNotFoundException();
        }

        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsBySku(String sku) {
        return false;
    }

    private Product saveFn(Product product) {
        return mapper.toProduct(
                repository.save(
                        mapper.toProductEntity(product)
                )
        );
    }
}
