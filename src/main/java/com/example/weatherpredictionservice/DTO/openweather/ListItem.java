package com.example.weatherpredictionservice.DTO.openweather;

import lombok.Data;

import java.util.List;

@Data
public class ListItem{
	private int dt;
	private int pop;
	private int visibility;
	private String dtTxt;
	private List<WeatherItem> weather;
	private Main main;
	private Clouds clouds;
	private Sys sys;
	private Wind wind;
}