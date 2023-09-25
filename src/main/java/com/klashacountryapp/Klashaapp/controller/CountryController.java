package com.klashacountryapp.Klashaapp.controller;

import com.klashacountryapp.Klashaapp.dtos.request.CountryApiRequest;
import com.klashacountryapp.Klashaapp.dtos.request.CountryCitiesPopulationDataRequest;
import com.klashacountryapp.Klashaapp.service.CountryExchangeRateService;
import com.klashacountryapp.Klashaapp.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/klasha/country")
@RequiredArgsConstructor
//@Tag(name = "Klasha country APIs")
public class CountryController {

    private final CountryService countryService;

    private final CountryExchangeRateService countryExchangeRateService;


    @GetMapping("/population")
    public ResponseEntity<Object> getCountry(@RequestParam String country) throws IOException {
        return ResponseEntity.ok(countryService.getCountryInformations(country));
    }

    @PostMapping ("/states")
    public ResponseEntity<Object> getCountryStates(@RequestBody CountryApiRequest country) throws IOException {
        return ResponseEntity.ok(countryService.getCountryStates(country));
    }

    @PostMapping("/cities")
    public ResponseEntity<Object> getCountryCities(@RequestBody CountryApiRequest country) throws IOException {
        return ResponseEntity.ok(countryService.getCountryCities(country));
    }

    @GetMapping("/states/cities")
    public ResponseEntity<Object> getCountryStatesCities(@RequestBody CountryApiRequest country) throws IOException {
        return ResponseEntity.ok(countryService.getCountryStatesCities(country));
    }

    @GetMapping("/cities/population-by-size")
    public ResponseEntity<Object> getCountryCitiesBySize(@RequestBody CountryCitiesPopulationDataRequest queryParams) throws IOException {
        return ResponseEntity.ok(countryService.getCountryCitiesByPopulationAsRequested(queryParams));
    }

    @PostMapping("/convertCurrency")
    public Double convertCurrency(@RequestBody String requestSourceCurrency, String requestTargetCurrency, Double requestAmount){
        return countryExchangeRateService.convertCurrency(requestSourceCurrency, requestTargetCurrency,requestAmount);
    }

    @PostMapping("/countryCurrency")
    public String getCountryCurrency(@RequestBody String requestSourceCurrency, String requestTargetCurrency, Double requestAmount){
        return countryExchangeRateService.getCountryCurrency(requestSourceCurrency, requestTargetCurrency,requestAmount);
    }

    @PostMapping("/exchangeRate")
    public Double findExchangeRate(@RequestBody String requestSourceCurrency, String requestTargetCurrency){
        return countryExchangeRateService.findExchangeRate(requestSourceCurrency, requestTargetCurrency);
    }



}
