package com.ckno.petproject;

import com.ckno.petproject.domain.port.LoginClientPort;
import com.ckno.petproject.domain.LoginService;
import com.ckno.petproject.infrastructure.LoginClientAdapter;
import com.ckno.petproject.infrastructure.LoginRepository;
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
    public LoginClientPort userClient(LoginRepository loginRepository) {
        return new LoginClientAdapter(loginRepository);
    }

    @Bean
    @Autowired
    public LoginService userService(LoginClientPort userClient) {
        return new LoginService(userClient);
    }
}
