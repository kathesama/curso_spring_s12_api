package com.kahesama.demo.curso_spring_s12_api;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource(value = "classpath:messages.properties", encoding = "UTF-8"),
        @PropertySource(value = "classpath:configuration.properties", encoding = "UTF-8"),
})
public class AppConfig {
}
