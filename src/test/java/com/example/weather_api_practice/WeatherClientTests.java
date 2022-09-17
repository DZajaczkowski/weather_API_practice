package com.example.weather_api_practice;

import com.example.weather_api_practice.city.CityService;
import com.example.weather_api_practice.weather.WeatherClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WeatherClientTests {

    @Autowired
    WeatherClient weatherClient;
    @Autowired
    CityService cityService;

    @Test
    void shouldGetCityData() {
        var cityResponse = weatherClient.getCityData("Warsaw");
        Assertions.assertNotNull(cityResponse);
    }

    @Test
    void getCityDataShouldReturnBody() {
        var cityResponse = weatherClient.getCityData("Warsaw");
        var cityResponseBody = cityResponse.getBody();
        Assertions.assertNotNull(cityResponseBody);
    }

    @Test
    void shouldGetWeatherData() {
        var weatherResponse = weatherClient.getWeatherData(cityService.getCity("Warsaw"));
        var responseStatus = weatherResponse.getStatusCode();
        Assertions.assertTrue(responseStatus.is2xxSuccessful());
    }

    @Test
    void getWeatherDataShouldReturnBody() {
        var weatherResponse = weatherClient.getWeatherData(cityService.getCity("Warsaw"));
        var weatherResponseBody = weatherResponse.getBody();
        Assertions.assertNotNull(weatherResponseBody);
    }

    @Test
    void getWeatherDataBodyShouldContainWarsawId() {
        var weatherResponse = weatherClient.getWeatherData(cityService.getCity("Warsaw"));
        var weatherResponseBody = weatherResponse.getBody();
        Assertions.assertNotNull(weatherResponseBody);
        Assertions.assertEquals(756135, weatherResponseBody.id);
    }

}
