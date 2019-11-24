package com.ckno.petproject.application;

import io.sentry.Sentry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class Collector {

    @GetMapping("/")
    public String sayHey() {
        Sentry.capture("Hello Sentry!");
        return "Hello Pet";
    }

    @GetMapping("/ex")
    public String throwException() {
        try {
            unsafeMethod();
        } catch (Exception e) {
            Sentry.capture(e);
        }

        return "";
    }

    private void unsafeMethod() {
        throw new UnsupportedOperationException("You shouldn't call this!");
    }
}
