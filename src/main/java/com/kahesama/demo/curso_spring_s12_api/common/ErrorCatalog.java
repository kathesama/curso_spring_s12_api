package com.kahesama.demo.curso_spring_s12_api.common;

import lombok.Getter;

@Getter
public enum ErrorCatalog {
    GENERIC_ERROR("ERR_GEN_001", "An unexpected error occurred."),
    PRODUCT_NOT_FOUND("ERR_PRODUCT_001", "ProductEntity not found."),
    INVALID_PRODUCT("ERR_PRODUCT_002", "Invalid product parameters.");

    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
