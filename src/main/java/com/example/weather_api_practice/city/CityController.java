package com.example.weather_api_practice.city;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    @GetMapping("/{cityName}")
    public City getCity(@PathVariable String cityName) {
        return cityService.getCity(cityName);
    }

    @GetMapping("/count/{countryName}")
    public int getCountOfCitiesByCountry(@PathVariable String countryName) {
        return cityService.countByCountry(countryName);
    }

    @PostMapping("/save/{cityName}")
    public City createCity(@PathVariable String cityName) {
        return cityService.createCity(cityName);
    }

    @DeleteMapping("/delete/{cityName}")
    public HttpStatus deleteCity(@PathVariable String cityName) {
        cityService.deleteCity(cityName);
        return HttpStatus.OK;
    }

}
