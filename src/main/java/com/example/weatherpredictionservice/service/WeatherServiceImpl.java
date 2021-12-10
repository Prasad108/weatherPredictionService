package com.example.weatherpredictionservice.service;

import com.example.weatherpredictionservice.DTO.APIResponse;
import com.example.weatherpredictionservice.DTO.openweather.ListItem;
import com.example.weatherpredictionservice.DTO.openweather.OpenWeatheringResponse;
import com.example.weatherpredictionservice.DTO.openweather.Main;
import com.example.weatherpredictionservice.util.CityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class WeatherServiceImpl implements WeatherService{


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WeatherMessageServiceImpl weatherMessageService;

    @Override
    public APIResponse getWeatherDataForCity(String city) throws CityNotFoundException, URISyntaxException {
        OpenWeatheringResponse openWeatherResponse = getOpenWeatherResponse(city);

        APIResponse response= new APIResponse();
        addTempDetails(openWeatherResponse, response);
        Set<String> messages = new HashSet<String>();
        weatherMessageService.addMessages(openWeatherResponse,messages);
        response.setMessages(messages);
        return response;
    }

    private void addTempDetails(OpenWeatheringResponse openWeatherResponse, APIResponse response) {
        Double minTemperature = getMinTemperature(openWeatherResponse);
        response.setMinTemp(minTemperature);
        Double maxTemperature = getMaxTemperature(openWeatherResponse);
        response.setMaxTemp(maxTemperature);
    }

    private OpenWeatheringResponse getOpenWeatherResponse(String city) throws CityNotFoundException, URISyntaxException {
        URI uri = new URI("https://api.openweathermap.org/data/2.5/forecast?q="+city+"&appid=d2929e9483efc82c82c32ee7e02d563e&amp;cnt=10");
        try {
            OpenWeatheringResponse weatherResponse = restTemplate.getForObject(uri, OpenWeatheringResponse.class);
            return weatherResponse;
        } catch (RestClientException e) {
            if (e.getMessage().contains("404 Not Found")) {
                throw new CityNotFoundException("City not found");
            }
            e.printStackTrace();
        }
        return null;
    }



    private Double getMinTemperature(OpenWeatheringResponse weatherResponse) {
        Optional<Double> minTemp = weatherResponse.getList()
                .stream()
                .map(ListItem::getMain)
                .map(Main::getTemp)
                .min(Double::compareTo);
        return minTemp.get();

    }

    private Double getMaxTemperature(OpenWeatheringResponse weatherResponse) {
        Optional<Double> maxTemp = weatherResponse.getList()
                .stream()
                .map(ListItem::getMain)
                .map(Main::getTemp)
                .max(Double::compareTo);
        return maxTemp.get();

    }
}
