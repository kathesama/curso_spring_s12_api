package com.kahesama.demo.curso_spring_s12_api;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource(value = "classpath:messages.properties", encoding = "ISO8859-1"),
})
public class AppConfig {
}
