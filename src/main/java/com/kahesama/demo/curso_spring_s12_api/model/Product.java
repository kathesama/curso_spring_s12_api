package com.kahesama.demo.curso_spring_s12_api.model;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;
    String name;
    String description;
    Double price;
    private Date createAt;
    private Date updatedAt;
}