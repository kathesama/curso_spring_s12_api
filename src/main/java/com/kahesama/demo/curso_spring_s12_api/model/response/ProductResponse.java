package com.kahesama.demo.curso_spring_s12_api.model.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    String name;
    String description;
    Double price;
}
