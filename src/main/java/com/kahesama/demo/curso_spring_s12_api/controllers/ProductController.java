package com.kahesama.demo.curso_spring_s12_api.controllers;

import com.kahesama.demo.curso_spring_s12_api.model.mappers.rest.ProductRestMapper;
import com.kahesama.demo.curso_spring_s12_api.model.request.ProductCreateRequest;
import com.kahesama.demo.curso_spring_s12_api.model.response.ProductResponse;
import com.kahesama.demo.curso_spring_s12_api.services.interfaces.ProductServiceInt;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:4200", originPatterns = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductServiceInt productService;
    private final ProductRestMapper productMapper;

    @GetMapping("/v1/api")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<ProductResponse> findAll() {
        return productMapper.toProductResponseList(productService.findAll());
    }

    @GetMapping("/v1/api/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ProductResponse findById(@PathVariable Long id) {
        return productMapper.toProductResponse(productService.findById(id));
    }

    @PostMapping("/v1/api")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductCreateRequest productRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                    productMapper.toProductResponse(
                        productService.save(
                                productMapper.toProduct(productRequest)
                        )
                    )
                );
    }

    @PutMapping("/v1/api/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse update(@PathVariable Long id, @Valid @RequestBody ProductCreateRequest productRequest) {
        return productMapper.toProductResponse(
                productService.update(id, productMapper.toProduct(productRequest)
            )
        );
    }

    @DeleteMapping("/v1/api/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
