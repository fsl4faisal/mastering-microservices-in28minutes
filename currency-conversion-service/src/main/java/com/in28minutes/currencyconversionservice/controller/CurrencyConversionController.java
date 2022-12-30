package com.in28minutes.currencyconversionservice.controller;

import com.in28minutes.currencyconversionservice.entity.CurrencyConversion;
import com.in28minutes.currencyconversionservice.proxy.CurrencyExchangeProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    private static final Logger LOG = LoggerFactory.getLogger(CurrencyConversionController.class);

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion getCurrencyConversion(@PathVariable String from,
                                                    @PathVariable String to,
                                                    @PathVariable int quantity) {
        var map = Map.of(
                "from", from,
                "to", to
        );
        var responseEntity = new RestTemplate()
                .getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                        CurrencyConversion.class, map);
        var currencyConversion = responseEntity.getBody();

        return new CurrencyConversion(
                100l,
                from,
                to,
                currencyConversion.getConversionMultiple(),
                quantity,
                currencyConversion.getConversionMultiple().multiply(BigDecimal.valueOf(quantity)),
                currencyConversion.getEnvironment());
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion getCurrencyConversionFeign(@PathVariable String from,
                                                         @PathVariable String to,
                                                         @PathVariable int quantity) {
        LOG.info("getCurrencyConversionFeign: from:{},to:{},Quantity{}", from, to, quantity);
        var currencyConversion = currencyExchangeProxy.retrieveCurrencyExchange(from, to);
        currencyConversion.setEnvironment(currencyConversion.getEnvironment() + " from feign-client");
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(currencyConversion.getConversionMultiple().multiply(BigDecimal.valueOf(quantity)));
        return currencyConversion;
    }
}
