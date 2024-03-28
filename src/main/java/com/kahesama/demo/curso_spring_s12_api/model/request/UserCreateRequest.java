package com.kahesama.demo.curso_spring_s12_api.model.request;

import com.kahesama.demo.curso_spring_s12_api.common.ConfigurationCatalog;
import com.kahesama.demo.curso_spring_s12_api.validation.ExistsByUsername;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

//@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {

    @NotBlank(message = "{NotBlank.product.name}")
    @Size(min = 3, max = 45)
    @ExistsByUsername(message="{ExistsUsername.user.username}")
    String username;

    @Nullable
    private Boolean enabled = false; // Valor por defecto: false

    @Nullable
    @Pattern(
            regexp = ConfigurationCatalog.PASSWORD_REGEX,
            message = "{WhenIsPresent.user.password.message}"
    )
    private String password;
}
