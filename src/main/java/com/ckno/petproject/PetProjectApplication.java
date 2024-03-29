package com.ckno.petproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PetProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetProjectApplication.class, args);
    }
}
