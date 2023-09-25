package com.klashacountryapp.Klashaapp.service.serviceImpl;

import com.klashacountryapp.Klashaapp.dtos.CountryAndExchangeRate;
import com.klashacountryapp.Klashaapp.service.CountryExchangeRateService;
import com.klashacountryapp.Klashaapp.utils.CountryExchangeCode;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryExchangeRateServiceImpl implements CountryExchangeRateService {


//    @Autowired
//    CountryAndExchangeRate countryAndExchangeRate;

    private List<CountryAndExchangeRate> exchangeRatesList;


    @PostConstruct
    public void initializeExchangeRates() throws IOException {
        // Load exchange rates from the CSV file and store them in the exchangeRates list

        Path path = Path.of("src", "main", "resources", "exchangeRate.csv");
        exchangeRatesList = Files.lines(path)
                .skip(1)
                .map(CountryExchangeRateServiceImpl::currencyExchange)
                .collect(Collectors.toList());
    }

    private static CountryAndExchangeRate currencyExchange(String line) {
        String[] fields = line.split(",");
        if (fields.length != 3)
            throw new RuntimeException("Invalid CSV line - " + line);

        String sourceCurrency = fields[0];
        String targetCurrency = fields[1];
        Double rate = Double.parseDouble(fields[2]);

        return new CountryAndExchangeRate(sourceCurrency, targetCurrency, rate);
    }


    @Override
    public Double convertCurrency(String requestSourceCurrency, String requestTargetCurrency, Double requestAmount) {
        Double exchangeRate = findExchangeRate(requestSourceCurrency, requestTargetCurrency);

        // Apply the conversion
        Double convertedAmount = requestAmount * exchangeRate;

        return convertedAmount ;

    }

    public Double findExchangeRate(String sourceCurrency, String targetCurrency) {
        for (CountryAndExchangeRate rate : exchangeRatesList) {
            if (rate.getSourceCurrency().equals(sourceCurrency) && rate.getTargetCurrency().equals(targetCurrency)) {
                return rate.getRate();
            }
        }
        // Handle the case where no exchange rate is found (return a default value or throw an exception)
        throw new RuntimeException("Exchange rate not found for " + sourceCurrency + " to " + targetCurrency);
    }

    @Override
    public String getCountryCurrency(String requestSourceCurrency, String requestTargetCurrency, Double requestAmount) {
        return switch (requestSourceCurrency) {
            case CountryExchangeCode.GBP_CODE -> CountryExchangeCode.GBP_MESSAGE;
            case CountryExchangeCode.EUR_CODE -> CountryExchangeCode.EUR_MESSAGE;
            case CountryExchangeCode.JPY_CODE -> CountryExchangeCode.JPY_MESSAGE;
            case CountryExchangeCode.USD_CODE -> CountryExchangeCode.USD_MESSAGE;
            default -> null;
        };
    }


}
