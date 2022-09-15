package com.example.weather_api_practice;

import com.example.weather_api_practice.weather.WeatherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WeatherControllerTests {

    @Autowired
    WeatherService weatherService;

    @Test
    void shouldGetWeatherForCity() {
        weatherService.getWeatherForCity("warsaw");
    }

    @Test
    void shouldGetAverageCountryTemperature() {
        weatherService.getAverageCountryTemperature("pl");
    }

//    @Test
//    void shouldPostWeatherForCity() {
//        weatherService.postWeatherForCity("warsaw");
//    }

    @Test
    void shouldUpdateWeatherForCity() {
        weatherService.updateWeatherForCity("warsaw");
    }

    @Test
    void shouldUpdateWeatherForCountry() {
        weatherService.updateWeatherForCountry("pl");
    }

    @Test
    void shouldDeleteWeatherForCity() {
        weatherService.deleteWeatherForCity("warsaw");
    }

}
