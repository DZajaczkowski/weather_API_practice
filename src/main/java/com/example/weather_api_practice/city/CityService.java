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


    public City getCity(String c) {
        c = c.toLowerCase(Locale.ROOT);
        if(cityRepository.existsCityByName(c))
            return cityRepository.getCityByName(c);
        else throw new CityNotFoundException();
    }

    public City postCity(String c) {
        c = c.toLowerCase(Locale.ROOT);
        if(cityRepository.existsCityByName(c))
            throw new CityAlreadyExistsException();
        var cityDataArray = weatherClient.getCityData(c).getBody();
        if (cityDataArray == null)
            throw new CityNotFoundException();
        var city = convertCityDataToCity(cityDataArray[0]);
        return cityRepository.save(city);
    }

    public int countByCountry(String country) {
        country = country.toUpperCase();
        return cityRepository.countAllByCountry(country);
    }

    public List<City> getCitiesByCountry(String country) {
        return cityRepository.getCitiesByCountry(country);
    }

    public void deleteCity(String city) {
        cityRepository.delete(getCity(city));
    }

    public City convertCityDataToCity(CityData cityData) {
        return new City(cityData);
    }
}
