package com.klashacountryapp.Klashaapp.service;

public interface CountryExchangeRateService {

    Double convertCurrency(String requestSourceCurrency, String requestTargetCurrency, Double requestAmount);

    String getCountryCurrency(String requestSourceCurrency, String requestTargetCurrency, Double requestAmount);

    public Double findExchangeRate(String sourceCurrency, String targetCurrency);


}
