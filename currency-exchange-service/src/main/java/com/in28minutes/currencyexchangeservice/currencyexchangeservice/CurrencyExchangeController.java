package com.in28minutes.currencyexchangeservice.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {
    private static final Logger LOG = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    Environment environment;

    @Autowired
    CurrencyExchangeRepository currencyExchangeRepository;


    @GetMapping("/test")
    public String getTest() {
        return "Test";
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getCurrencyExchange(@PathVariable String from,
                                                @PathVariable String to) {
        LOG.info("getCurrencyExchange: from:{},to:{}", from, to);
        return currencyExchangeRepository.findByFromAndTo(from, to)
                .map(this::setEnvironment)
                .orElseThrow(() -> new RuntimeException(String.format("Not found for from:%s and to:%s", from, to)));
    }

    private CurrencyExchange setEnvironment(CurrencyExchange currencyExchange) {
        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
        return currencyExchange;
    }
}


