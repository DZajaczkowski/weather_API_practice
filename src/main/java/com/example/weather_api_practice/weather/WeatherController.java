package com.example.weather_api_practice.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/{cityName}")
    @ResponseStatus(OK)
    public Weather getWeatherForCity(@PathVariable String cityName) {
        return weatherService.getWeatherForCity(cityName);
    }

    @GetMapping("/average/{countryName}")
    @ResponseStatus(OK)
    public double getAverageCountryTemperature(@PathVariable String countryName) {
        return weatherService.getAverageCountryTemperature(countryName);
    }

    @PostMapping("/{cityName}")
    @ResponseStatus(CREATED)
    public Weather postWeatherForCity(@PathVariable String cityName) {
        return weatherService.postWeatherForCity(cityName);
    }

    @PutMapping("/{cityName}")
    @ResponseStatus(CREATED)
    public Weather updateWeatherForCity(@PathVariable String cityName) {
        return weatherService.updateWeatherForCity(cityName);
    }

    @PutMapping("/country/{countryName}")
    @ResponseStatus(CREATED)
    public List<Weather> updateWeatherForCountry(@PathVariable String countryName) {
        return weatherService.updateWeatherForCountry(countryName);
    }

    @DeleteMapping("/delete/{cityName}")
    @ResponseStatus(OK)
    public Weather deleteWeatherForCity(@PathVariable String cityName) {
        Weather weather = weatherService.getWeatherForCity(cityName);
        weatherService.deleteWeatherForCity(cityName);
        return weather;
    }

}
