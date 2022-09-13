package com.example.weather_api_practice.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/{city}")
    public Weather getWeatherForCity(@PathVariable String city) {
        return weatherService.getWeatherForCity(city);
    }

    @GetMapping("/average/{country}")
    public double getAverageCountryTemperature(@PathVariable String country) {
        return weatherService.getAverageCountryTemperature(country);
    }

    @PostMapping("/{city}")
    public Weather postWeatherForCity(@PathVariable String city) {
        return weatherService.postWeatherForCity(city);
    }

}
