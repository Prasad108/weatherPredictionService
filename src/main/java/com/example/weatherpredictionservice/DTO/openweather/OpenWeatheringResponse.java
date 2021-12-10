package com.example.weatherpredictionservice.DTO.openweather;

import lombok.Data;

import java.util.List;

@Data
public class OpenWeatheringResponse {
	private City city;
	private int cnt;
	private String cod;
	private int message;
	private List<ListItem> list;
}