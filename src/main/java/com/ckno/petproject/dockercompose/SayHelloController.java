package com.ckno.petproject.dockercompose;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("v1/hello")
@RequiredArgsConstructor
@Log4j2
public class SayHelloController {

    private final NamesRepository namesRepository;

    @GetMapping
    String randomHello() {
        List<Person> persons = namesRepository.findAll();
        Random random = new Random();
        return "Hey " + persons.get(random.nextInt(persons.size())).name();
    }

    @GetMapping("/add/{name}")
    void addName(@PathVariable String name) {
        namesRepository.save(new Person(name));
    }

    @GetMapping("/all")
    List<Person> all() {
        return namesRepository.findAll();
    }
}
