package com.kahesama.demo.curso_spring_s12_api.common;


import lombok.experimental.UtilityClass;

@UtilityClass
public class ConfigurationCatalog {
    public static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])(?=.*[a-z]).{8,}$";
}
