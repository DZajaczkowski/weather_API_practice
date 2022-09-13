package com.example.weather_api_practice.weather;

import com.example.weather_api_practice.city.City;
import com.example.weather_api_practice.city.CityService;
import com.example.weather_api_practice.weather.data.WeatherData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class WeatherService {

    private final WeatherClient weatherClient;
    private final WeatherRepository weatherRepository;
    private final CityService cityService;

    public Weather getWeatherForCity(String c) {
        String cityInput = c.substring(0,1).toUpperCase() + c.substring(1);
        return weatherRepository.getWeatherByCity_Name(cityInput);
    }

    public Weather postWeatherForCity(String c) {
        City city = cityService.postCity(c);
        var weatherResponseBody = weatherClient.getWeatherData(city);
        if (!weatherResponseBody.hasBody())
            throw new RuntimeException();
        var weather = convertWeatherDataToWeather(weatherResponseBody.getBody(), city);
        return weatherRepository.save(weather);
    }

    public double getAverageCountryTemperature(String country) {
        double average = 0;
        country = country.toUpperCase();
        City[] cities = cityService.getCitiesByCountry(country);
        if (cities.length == 0)
            throw new RuntimeException();
        for (City city : cities) {
            average = average +
                    updateWeatherForCity(Objects.requireNonNull(weatherClient.getWeatherData(city).getBody()),city).getTemp();
        }
        return average/cities.length;
    }

    public Weather convertWeatherDataToWeather(WeatherData weatherData, City city) {
        return new Weather(weatherData, city);
    }

    //TODO
    public void updateCountryWeather(String country) {
        country = country.toUpperCase();
        City[] cities = cityService.getCitiesByCountry(country);
        for (City city : cities) {
            updateWeatherForCity(Objects.requireNonNull(weatherClient.getWeatherData(city).getBody()), city);
        }
    }

    //FIXME
    public Weather updateWeatherForCity(WeatherData weatherData, City city) {
        Weather weather = weatherRepository.getWeatherByCity(city);
        weather.setHumidity(weatherData.main.humidity);
        weather.setPressure(weatherData.main.pressure);
        weather.setTemp(weatherData.main.temp);
        return weatherRepository.save(weather);
    }


}
