package com.example.weather_api_practice.weather.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class WeatherData {


    public int id;
    public WeatherCoordinates coord;
    public WeatherMain main;


}
