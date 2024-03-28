package com.kahesama.demo.curso_spring_s12_api.validation.validatorComponent;

import com.kahesama.demo.curso_spring_s12_api.services.interfaces.UserServiceInt;
import com.kahesama.demo.curso_spring_s12_api.validation.ExistsByUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExistsByUsernameComponent implements ConstraintValidator<ExistsByUsername, String> {
    private final UserServiceInt userService;
    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return !userService.existsByUsername(username);
    }
}
