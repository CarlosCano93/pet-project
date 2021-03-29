package com.ckno.petproject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Value("${com.ckno.igp-front-url}")
    private String campeonatoFiaUrl;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/v1/igp/**")
                .allowedOrigins(campeonatoFiaUrl)
                .allowedMethods("GET");
    }
}
