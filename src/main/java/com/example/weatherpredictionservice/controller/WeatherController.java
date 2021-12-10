package com.example.weatherpredictionservice.controller;

import com.example.weatherpredictionservice.DTO.APIResponse;
import com.example.weatherpredictionservice.DTO.openweather.OpenWeatheringResponse;
import com.example.weatherpredictionservice.service.WeatherServiceImpl;
import com.example.weatherpredictionservice.util.CityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
public class WeatherController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WeatherServiceImpl weatherService;


    @GetMapping(value="/weather/{cityName}", produces={"application/json"})
    public ResponseEntity<Object> getWeather(@PathVariable("cityName")  String cityName) throws URISyntaxException, CityNotFoundException {

        APIResponse weatherDataForCity = weatherService.getWeatherDataForCity(cityName);
        return ResponseEntity.ok(weatherDataForCity);
    }




}
