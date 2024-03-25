package com.kahesama.demo.curso_spring_s12_api.model.mappers.rest;
import com.kahesama.demo.curso_spring_s12_api.model.Product;
import com.kahesama.demo.curso_spring_s12_api.model.request.ProductCreateRequest;
import com.kahesama.demo.curso_spring_s12_api.model.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE //ignorar los campos que no se mapean
)
public interface ProductRestMapper {
    Product toProduct(ProductCreateRequest request);
    ProductResponse toProductResponse(Product product);
    List<ProductResponse> toProductResponseList(List<Product> productList);
}
