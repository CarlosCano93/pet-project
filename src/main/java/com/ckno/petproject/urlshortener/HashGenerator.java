package com.ckno.petproject.urlshortener;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class HashGenerator {
    public String newHash() {
        return UUID.randomUUID().toString();
    }
}
