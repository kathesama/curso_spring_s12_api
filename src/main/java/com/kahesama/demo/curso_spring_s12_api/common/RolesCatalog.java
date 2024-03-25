package com.kahesama.demo.curso_spring_s12_api.common;

import lombok.Getter;

@Getter
public enum RolesCatalog {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String name;

    RolesCatalog(String code) {
        this.name = code;
    }
}
