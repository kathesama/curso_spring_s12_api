package com.kahesama.demo.curso_spring_s12_api.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAuthorityJsonCreator {

    /**
     * Se crea para que pueda aceptar un atributo authority en vez de roles en SimpleGrantedAuthority
     * */
    @JsonCreator
    public SimpleGrantedAuthorityJsonCreator(@JsonProperty("authority") String role){}
}
