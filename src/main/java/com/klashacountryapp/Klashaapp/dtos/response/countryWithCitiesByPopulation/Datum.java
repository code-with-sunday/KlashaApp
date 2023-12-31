package com.klashacountryapp.Klashaapp.dtos.response.countryWithCitiesByPopulation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Datum{
    public String city;
    public String country;
//    public ArrayList<PopulationCountCities> populationCounts;
    public ArrayList<PopulationCountCity> populationCounts;
}