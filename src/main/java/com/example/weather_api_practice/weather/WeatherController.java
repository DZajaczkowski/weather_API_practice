package com.example.weather_api_practice.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PutMapping("/{city}")
    public Weather updateWeatherForCity(@PathVariable String city) {
        return weatherService.updateWeatherForCity(city);
    }

    @PutMapping("/country/{country}")
    public HttpStatus updateWeatherForCountry(@PathVariable String country) {
        weatherService.updateWeatherForCountry(country);
        return HttpStatus.OK;
    }

    @DeleteMapping("/delete/{city}")
    public HttpStatus deleteWeatherForCity(@PathVariable String city) {
        weatherService.deleteWeatherForCity(city);
        return HttpStatus.OK;
    }

}
