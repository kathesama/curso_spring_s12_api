package com.kahesama.demo.curso_spring_s12_api.model.mappers.persistence;

import com.kahesama.demo.curso_spring_s12_api.entities.ProductEntity;
import com.kahesama.demo.curso_spring_s12_api.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE //ignorar los campos que no se mapean
)
public interface ProductPersistenceMapper {
    ProductEntity toProductEntity(Product product);

    Product toProduct(ProductEntity entity);

    List<Product> toProductList(List<ProductEntity> entityList);
}
