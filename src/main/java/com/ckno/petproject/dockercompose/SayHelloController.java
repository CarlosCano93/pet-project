package com.ckno.petproject.dockercompose;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/hello")
@RequiredArgsConstructor
public class SayHelloController {

    private final NamesRepository namesRepository;

    @GetMapping
    String randomHello() {
        return "Hey " + namesRepository.findAll().get(0);
    }

    @GetMapping("/add")
    void addName() {
        namesRepository.save(new Person("Carlos"));
    }
}
