package com.in28minutes.currencyexchangeservice.currencyexchangeservice;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    @Bulkhead(name = "sample-api")
    public String sampleApi() {
        logger.info("Sample api call received ");
        var entity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
        return entity.getBody();
    }
}
