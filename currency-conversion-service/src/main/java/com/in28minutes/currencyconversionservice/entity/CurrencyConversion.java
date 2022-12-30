package com.in28minutes.currencyconversionservice.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyConversion {
    Long id;
    String from;
    String to;
    BigDecimal conversionMultiple;
    int quantity;
    BigDecimal totalCalculatedAmount;
    String environment;
}
