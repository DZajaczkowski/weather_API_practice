package com.example.weather_api_practice.weather.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherMain {

    public double temp;

    public int pressure;

    public int humidity;
}
