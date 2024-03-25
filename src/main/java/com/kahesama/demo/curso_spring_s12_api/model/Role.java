package com.kahesama.demo.curso_spring_s12_api.model;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Long id;
    private Date createAt;
    private Date updatedAt;
    String name;
}
