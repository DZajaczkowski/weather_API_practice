package com.example.weather_api_practice.weather.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class WeatherCoordinates {

    public double lon;

    public double lat;

}
