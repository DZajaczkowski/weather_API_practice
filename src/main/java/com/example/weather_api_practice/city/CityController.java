package com.example.weather_api_practice.city;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    @GetMapping("/{city}")
    public City getCity(@PathVariable String city) {
        return cityService.getCity(city);
    }

    @GetMapping("/count/{country}")
    public int getCountOfCitiesByCountry(@PathVariable String country) {
        return cityService.countByCountry(country);
    }

    @PostMapping("/save/{city}")
    public City postCity(@PathVariable String city) {
        return cityService.postCity(city);
    }

}
