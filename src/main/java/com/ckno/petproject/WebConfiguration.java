package com.ckno.petproject;

import io.sentry.Sentry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@Slf4j
public class WebConfiguration implements WebMvcConfigurer {

    @Value("${com.ckno.igp-front-url}") private String campeonatoFiaUrl;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        Sentry.capture("campeonatoFiaUrl: " + campeonatoFiaUrl);
        registry.addMapping("/v1/igp/**")
                .allowedOrigins("https://campeonato-fia-igp.herokuapp.com")
                .allowedMethods("GET");
    }
}
