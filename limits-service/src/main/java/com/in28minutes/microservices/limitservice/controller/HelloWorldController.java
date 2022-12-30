package com.in28minutes.microservices.limitservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {


    @GetMapping("/helloworld")
    public String helloWorld() {
        return "hello world";
    }
}
