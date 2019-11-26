package com.ckno.petproject;

import com.ckno.petproject.domain.UserRepository;
import com.ckno.petproject.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
public class PetProjectConfiguration {

    @Bean
    public HandlerExceptionResolver sentryExceptionResolver() {
        return new io.sentry.spring.SentryExceptionResolver();
    }

    @Bean
    @Autowired
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }
}
