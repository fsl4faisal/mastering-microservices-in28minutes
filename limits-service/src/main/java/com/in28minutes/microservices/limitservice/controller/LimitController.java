package com.in28minutes.microservices.limitservice.controller;

import com.in28minutes.microservices.limitservice.configuration.Configuration;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public Limits retrieveLimits() {
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
    }
}

@Data
@AllArgsConstructor
class Limits {
    private int minimum;
    private int maximum;
}