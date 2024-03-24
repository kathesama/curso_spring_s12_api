package com.kahesama.demo.curso_spring_s12_api.controllers;

import com.kahesama.demo.curso_spring_s12_api.exceptions.ProductNotFoundException;
import com.kahesama.demo.curso_spring_s12_api.model.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.tools.Diagnostic;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

import static com.kahesama.demo.curso_spring_s12_api.common.ErrorCatalog.GENERIC_ERROR;
import static com.kahesama.demo.curso_spring_s12_api.common.ErrorCatalog.PRODUCT_NOT_FOUND;
import static com.kahesama.demo.curso_spring_s12_api.common.ErrorCatalog.INVALID_PRODUCT;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGenericError(Exception exception) {
        return ErrorResponse.builder()
                .code(GENERIC_ERROR.getCode())
                .message(GENERIC_ERROR.getMessage())
                .details(Collections.singletonList(exception.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorResponse handleStudentNotFoundException() {
        return ErrorResponse.builder()
                .code(PRODUCT_NOT_FOUND.getCode())
                .message(PRODUCT_NOT_FOUND.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();

        return ErrorResponse.builder()
                .code(INVALID_PRODUCT.getCode())
                .message(INVALID_PRODUCT.getMessage())
                .details(result.getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                        .map((fieldError) -> fieldError.getDefaultMessage())  //otra forma de hacerlo
                        .collect(Collectors.toList()))
                .timestamp(LocalDateTime.now())
                .build();
    }

}
