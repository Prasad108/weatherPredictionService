package com.example.weatherpredictionservice.service;

import com.example.weatherpredictionservice.DTO.openweather.ListItem;
import com.example.weatherpredictionservice.DTO.openweather.OpenWeatheringResponse;
import com.example.weatherpredictionservice.util.Messages;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class WeatherMessageServiceImpl implements WeatherMessageService{
    @Override
    public void addMessages(OpenWeatheringResponse weatherResponse, Set<String> messages) {
        addRainMessage(weatherResponse,messages);
        addWindyMessage(weatherResponse,messages);
        addTempMessage(weatherResponse,messages);
        addThunderStormMessage(weatherResponse,messages);
    }


    private void addWindyMessage(OpenWeatheringResponse weatherResponse, Set<String> messages) {
        boolean highWind = weatherResponse.getList()
                .stream()
                .map(ListItem::getWind)
                .anyMatch(wind -> wind.getSpeed() > 10);

        if (highWind) {
            messages.add(Messages.WINDY);
        }
    }

    private void addTempMessage(OpenWeatheringResponse weatherResponse, Set<String> messages) {
        boolean highTemp = weatherResponse.getList()
                .stream()
                .map(ListItem::getMain)
                .anyMatch(weatherDetail -> null!=weatherDetail &&  weatherDetail.getTemp()>(40+273.15));

        if (highTemp) {
            messages.add(Messages.SUNNY);
        }
    }

    private void addRainMessage(OpenWeatheringResponse weatherResponse, Set<String> messages) {
        boolean isRainy = weatherResponse.getList()
                .stream()
                .map(ListItem::getWeather)
                .flatMap(weatherDescriptions -> weatherDescriptions.stream())
                .peek(System.out::println)
                .anyMatch(weatherDescriptions -> weatherDescriptions.getMain().contains("Rain"));

        if (isRainy) {
            messages.add(Messages.RAINING);
        }
    }

    private void addThunderStormMessage(OpenWeatheringResponse weatherResponse, Set<String> messages) {
        boolean isThunderstorm = weatherResponse.getList()
                .stream()
                .map(ListItem::getWeather)
                .flatMap(weatherDescriptions -> weatherDescriptions.stream())
                .anyMatch(weatherDescriptions -> weatherDescriptions.getMain().contains("Thunderstorm"));

        if (isThunderstorm) {
            messages.add(Messages.THUNDERSTORM);
        }
    }
}
