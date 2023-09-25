package com.klashacountryapp.Klashaapp.dtos;

import lombok.*;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CountryAndExchangeRate {

    private String sourceCurrency;
    private String targetCurrency;
    private Double rate;
}
