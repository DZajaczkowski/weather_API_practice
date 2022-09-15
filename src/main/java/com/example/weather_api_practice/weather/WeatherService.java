package com.example.weather_api_practice.weather;

import com.example.weather_api_practice.city.City;
import com.example.weather_api_practice.city.CityService;
import com.example.weather_api_practice.exceptions.CountryNotFoundException;
import com.example.weather_api_practice.exceptions.WeatherAlreadyExistsException;
import com.example.weather_api_practice.exceptions.WeatherNotFoundException;
import com.example.weather_api_practice.weather.data.WeatherData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@Service
public class WeatherService {

    private final WeatherClient weatherClient;
    private final WeatherRepository weatherRepository;
    private final CityService cityService;

    public Weather getWeatherForCity(String c) {
        c = c.toLowerCase(Locale.ROOT);
        if(!weatherRepository.existsWeatherByCity_Name(c))
            throw new WeatherNotFoundException();
        return weatherRepository.getWeatherByCity_Name(c);
    }

    public Weather postWeatherForCity(String c) {
        c = c.toLowerCase(Locale.ROOT);
        if(weatherRepository.existsWeatherByCity_Name(c))
            throw new WeatherAlreadyExistsException();
        City city = cityService.getCity(c);
        var weatherResponseBody = weatherClient.getWeatherData(city);
        var weather = convertWeatherDataToWeather(weatherResponseBody.getBody(), city);
        return weatherRepository.save(weather);
    }

    public double getAverageCountryTemperature(String country) {
        double average = 0;
        updateWeatherForCountry(country.toUpperCase());

        List<Weather> weathers = weatherRepository.getWeathersByCity_Country(country.toUpperCase());
        if (weathers.size() == 0)
            throw new WeatherNotFoundException();

        for (Weather weather : weathers) {
            average = average + weather.getTemp();
        }
        return average / weathers.size();
    }

    public void updateWeatherForCountry(String country) {
        country = country.toUpperCase();
        if (!weatherRepository.existsWeatherByCity_Country(country))
            throw new CountryNotFoundException();
        List<City> cities = cityService.getCitiesByCountry(country);
        for (City city : cities) {
            updateWeatherForCity(city.getName());
        }
    }

    public Weather updateWeatherForCity(String c) {
        c = c.toLowerCase(Locale.ROOT);
        City city = cityService.postCity(c);
        if(!weatherRepository.existsWeatherByCity_Name(c))
            throw new WeatherNotFoundException();
        Weather weather = weatherRepository.getWeatherByCity(city);
        weather = setUpdatedValues(city, weather);
        return weatherRepository.save(weather);
    }

    public void deleteWeatherForCity(String c) {
        c = c.toLowerCase(Locale.ROOT);
        Weather weather = getWeatherForCity(c);
        weatherRepository.delete(weather);
    }

    public Weather convertWeatherDataToWeather(WeatherData weatherData, City city) {
        return new Weather(weatherData, city);
    }

    public Weather setUpdatedValues(City city, Weather weather) {
        var weatherData = weatherClient.getWeatherData(city).getBody();
        if (weatherData == null)
            throw new RuntimeException();
        weather.setHumidity(weatherData.main.humidity);
        weather.setPressure(weatherData.main.pressure);
        weather.setTemp(weatherData.main.temp);
        return weather;
    }

}
