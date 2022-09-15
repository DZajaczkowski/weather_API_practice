package com.example.weather_api_practice;

import com.example.weather_api_practice.city.CityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CityControllerTests {

    @Autowired
    CityService cityService;

    @Test
    void shouldGetCity() {
        cityService.getCity("warsaw");
    }

    @Test
    void shouldCountCitiesByCountry() {
        cityService.countByCountry("pl");
    }

    @Test
    void shouldCreateCity() {
        cityService.createCity("london");
    }

    @Test
    void shouldDeleteCity() {
        cityService.deleteCity("london");
    }


}
