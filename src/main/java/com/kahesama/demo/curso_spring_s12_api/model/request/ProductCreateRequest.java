package com.kahesama.demo.curso_spring_s12_api.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateRequest {
//    @NotBlank(message = "Field name cannot be empty or null.")
    @NotBlank(message = "{NotBlank.product.name}")
    @Size(min = 3, max = 45)
    String name;

    @NotBlank(message = "{NotBlank.product.description}")
    @Size(min = 3, max = 250, message = "{Size.product.price}")
    String description;

    @NotNull(message = "{NotNull.product.price}")
    @Min(value = 500, message = "{Min.product.price}")
    Double price;
}
