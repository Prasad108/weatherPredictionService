package com.example.weatherpredictionservice.service;

import com.example.weatherpredictionservice.DTO.openweather.OpenWeatheringResponse;

import java.util.Set;

public interface WeatherMessageService {
    void addMessages(OpenWeatheringResponse weatherResponse, Set<String> messages);
}
