package com.example.weather_api_practice.weather;

import com.example.weather_api_practice.city.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {

    List<Weather> getWeathersByCity_Country(String country);
    Weather getWeatherByCity(City city);
    Weather getWeatherByCity_Name(String city);
    boolean existsWeatherByCity(City city);
    boolean existsWeatherByCity_Name(String city);
    boolean existsWeatherByCity_Country(String country);

}
