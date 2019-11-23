package com.ckno.petproject.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class Collector {

    @GetMapping("/")
    public String sayHey() {
        return "Hello Pet";
    }
}
