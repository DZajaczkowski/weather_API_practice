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

    public Weather getWeatherForCity(String cityName) {
        cityName = cityName.toLowerCase(Locale.ROOT);
        if (!weatherRepository.existsWeatherByCity_Name(cityName))
            throw new WeatherNotFoundException();
        return weatherRepository.getWeatherByCity_Name(cityName);
    }

    public Weather postWeatherForCity(String cityName) {
        cityName = cityName.toLowerCase(Locale.ROOT);
        if (weatherRepository.existsWeatherByCity_Name(cityName))
            throw new WeatherAlreadyExistsException();
        City city = cityService.getCity(cityName);
        var weatherResponseBody = weatherClient.getWeatherData(city);
        var weather = convertWeatherDataToWeather(weatherResponseBody.getBody(), city);
        return weatherRepository.save(weather);
    }

    public double getAverageCountryTemperature(String countryName) {
        double average = 0;
        updateWeatherForCountry(countryName.toUpperCase());

        List<Weather> weathers = weatherRepository.getWeathersByCity_Country(countryName.toUpperCase());
        if (weathers.size() == 0)
            throw new WeatherNotFoundException();

        for (Weather weather : weathers) {
            average += weather.getTemp();
        }
        return average / weathers.size();
    }

    public void updateWeatherForCountry(String countryName) {
        countryName = countryName.toUpperCase();
        if (!weatherRepository.existsWeatherByCity_Country(countryName))
            throw new CountryNotFoundException();
        List<City> cities = cityService.getCitiesByCountry(countryName);
        for (City city : cities) {
            updateWeatherForCity(city.getName());
        }
    }

    public Weather updateWeatherForCity(String cityName) {
        cityName = cityName.toLowerCase(Locale.ROOT);
        City city = cityService.getCity(cityName);
        if (!weatherRepository.existsWeatherByCity_Name(cityName))
            postWeatherForCity(cityName);
        Weather weather = weatherRepository.getWeatherByCity(city);
        weather = setUpdatedValues(city, weather);
        return weatherRepository.save(weather);
    }

    public void deleteWeatherForCity(String cityName) {
        cityName = cityName.toLowerCase(Locale.ROOT);
        Weather weather = getWeatherForCity(cityName);
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
