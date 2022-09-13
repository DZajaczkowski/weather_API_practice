package com.example.weather_api_practice.city;

import com.example.weather_api_practice.weather.WeatherClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CityService {

    private final WeatherClient weatherClient;
    private final CityRepository cityRepository;


    public City getCity(String c) {
        String cityInput = c.substring(0,1).toUpperCase() + c.substring(1);
        if(cityRepository.existsCityByName(cityInput))
            return cityRepository.getCityByName(cityInput);
        else throw new RuntimeException();
    }

    public City postCity(String c) {
        String cityInput = c.substring(0,1).toUpperCase() + c.substring(1);
        if(cityRepository.existsCityByName(cityInput))
            return cityRepository.getCityByName(cityInput);
        var cityDataArray = weatherClient.getCityData(c).getBody();
        if (cityDataArray == null)
            throw new RuntimeException();
        var city = convertCityDataToCity(cityDataArray[0]);
        return cityRepository.save(city);
    }

    public int countByCountry(String country) {
        country = country.toUpperCase();
        return cityRepository.countAllByCountry(country);
    }

    public City[] getCitiesByCountry(String country) {
        return cityRepository.getCitiesByCountry(country);
    }

    public City convertCityDataToCity(CityData cityData) {
        return new City(cityData);
    }
}
