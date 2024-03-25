package com.kahesama.demo.curso_spring_s12_api.model.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    String username;
    Boolean enabled;
}
