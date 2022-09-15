package com.example.weather_api_practice.city;

import com.example.weather_api_practice.exceptions.CityAlreadyExistsException;
import com.example.weather_api_practice.exceptions.CityNotFoundException;
import com.example.weather_api_practice.weather.WeatherClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@Service
public class CityService {

    private final WeatherClient weatherClient;
    private final CityRepository cityRepository;


    public City getCity(String cityName) {
        cityName = cityName.toLowerCase(Locale.ROOT);
        if(cityRepository.existsCityByName(cityName))
            return cityRepository.getCityByName(cityName);
        else throw new CityNotFoundException();
    }

    public City createCity(String cityName) {
        cityName = cityName.toLowerCase(Locale.ROOT);
        if(cityRepository.existsCityByName(cityName))
            throw new CityAlreadyExistsException();
        var cityDataArray = weatherClient.getCityData(cityName).getBody();
        if (cityDataArray == null)
            throw new CityNotFoundException();
        var city = convertCityDataToCity(cityDataArray[0]);
        return cityRepository.save(city);
    }

    public int countByCountry(String countryName) {
        countryName = countryName.toUpperCase();
        return cityRepository.countAllByCountry(countryName);
    }

    public List<City> getCitiesByCountry(String countryName) {
        return cityRepository.getCitiesByCountry(countryName);
    }

    public void deleteCity(String cityName) {
        cityRepository.delete(getCity(cityName));
    }

    public City convertCityDataToCity(CityData cityData) {
        return new City(cityData);
    }

}
