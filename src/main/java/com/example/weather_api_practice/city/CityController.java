package com.example.weather_api_practice.city;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    @GetMapping("/{cityName}")
    @ResponseStatus(OK)
    public City getCity(@PathVariable String cityName) {
        return cityService.getCity(cityName);
    }

    @GetMapping("/count/{countryName}")
    @ResponseStatus(OK)
    public int getCountOfCitiesByCountry(@PathVariable String countryName) {
        return cityService.countByCountry(countryName);
    }

    @PostMapping("/{cityName}")
    @ResponseStatus(CREATED)
    public City createCity(@PathVariable String cityName) {
        return cityService.createCity(cityName);
    }

    @DeleteMapping("/{cityName}/delete")
    @ResponseStatus(OK)
    public City deleteCity(@PathVariable String cityName) {
        var city = getCity(cityName);
        cityService.deleteCity(cityName);
        return city;
    }

}
