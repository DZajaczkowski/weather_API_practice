package com.example.weather_api_practice.weather;

import com.example.weather_api_practice.city.City;
import com.example.weather_api_practice.city.CityData;
import com.example.weather_api_practice.weather.data.WeatherData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherClient {

    private final RestTemplate restTemplate;

    public ResponseEntity<CityData[]> getCityData(String cityName) {
        String key = "55832a1dadee8a9132e71d5acf5821a7";
        String currentCoordinates
                = "https://api.openweathermap.org/geo/1.0/direct?q={city}&limit=1&appid={key}";
        return restTemplate.getForEntity(currentCoordinates, CityData[].class, cityName, key);
    }

    public ResponseEntity<WeatherData> getWeatherData(City city) {
        String key = "55832a1dadee8a9132e71d5acf5821a7";
        String currentWeather
                = "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&units=metric&appid={key}";
        return restTemplate.getForEntity(currentWeather, WeatherData.class, city.getLat(), city.getLon(), key);
    }

}
