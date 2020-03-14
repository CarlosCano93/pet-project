package com.ckno.petproject;

import com.ckno.petproject.domain.SignUpService;
import com.ckno.petproject.domain.port.LoginClientPort;
import com.ckno.petproject.domain.LoginService;
import com.ckno.petproject.domain.port.SignUpClientPort;
import com.ckno.petproject.adapters.LoginClientAdapter;
import com.ckno.petproject.adapters.SignUpClientAdapter;
import com.ckno.petproject.adapters.UserRepository;
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
    public LoginClientPort loginClient(UserRepository userRepository) {
        return new LoginClientAdapter(userRepository);
    }

    @Bean
    @Autowired
    public LoginService loginService(LoginClientPort loginClient) {
        return new LoginService(loginClient);
    }

    @Bean
    @Autowired
    public SignUpClientPort signUpClient(UserRepository userRepository) {
        return new SignUpClientAdapter(userRepository);
    }

    @Bean
    @Autowired
    public SignUpService signUpService(SignUpClientPort signUpClient) {
        return new SignUpService(signUpClient);
    }
}
