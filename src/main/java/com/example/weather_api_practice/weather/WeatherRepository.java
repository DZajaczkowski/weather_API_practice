package com.example.weather_api_practice.weather;

import com.example.weather_api_practice.city.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {

    Weather[] getWeathersByCity_Country(String country);
    Weather getWeatherByCity(City city);
    Weather getWeatherByCity_Name(String city);
    boolean existsWeatherByCity(City city);
}
