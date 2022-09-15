package com.example.weather_api_practice.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/{cityName}")
    public Weather getWeatherForCity(@PathVariable String cityName) {
        return weatherService.getWeatherForCity(cityName);
    }

    @GetMapping("/average/{countryName}")
    public double getAverageCountryTemperature(@PathVariable String countryName) {
        return weatherService.getAverageCountryTemperature(countryName);
    }

    @PostMapping("/{cityName}")
    public Weather postWeatherForCity(@PathVariable String cityName) {
        return weatherService.postWeatherForCity(cityName);
    }

    @PutMapping("/{cityName}")
    public Weather updateWeatherForCity(@PathVariable String cityName) {
        return weatherService.updateWeatherForCity(cityName);
    }

    @PutMapping("/country/{countryName}")
    public HttpStatus updateWeatherForCountry(@PathVariable String countryName) {
        weatherService.updateWeatherForCountry(countryName);
        return HttpStatus.OK;
    }

    @DeleteMapping("/delete/{cityName}")
    public HttpStatus deleteWeatherForCity(@PathVariable String cityName) {
        weatherService.deleteWeatherForCity(cityName);
        return HttpStatus.OK;
    }

}
