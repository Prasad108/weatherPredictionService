package com.example.weatherpredictionservice.service;

import com.example.weatherpredictionservice.DTO.APIResponse;
import com.example.weatherpredictionservice.util.CityNotFoundException;

import java.net.URISyntaxException;

public interface WeatherService {
     APIResponse getWeatherDataForCity(String city) throws CityNotFoundException, URISyntaxException;
}
