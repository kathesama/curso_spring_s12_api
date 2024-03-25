package com.kahesama.demo.curso_spring_s12_api.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private Date createAt;
    private Date updatedAt;
    private String username;
    private String password;
    private Boolean enabled;
}
