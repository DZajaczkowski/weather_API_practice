package com.example.weather_api_practice.weather;

import com.example.weather_api_practice.city.City;
import com.example.weather_api_practice.city.CityService;
import com.example.weather_api_practice.weather.data.WeatherData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WeatherService {

    private final WeatherClient weatherClient;
    private final WeatherRepository weatherRepository;
    private final CityService cityService;

    public Weather getWeatherForCity(String c) {
        String cityInput = c.substring(0, 1).toUpperCase() + c.substring(1);
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
        updateWeatherForCountry(country.toUpperCase());

        List<Weather> weathers = weatherRepository.getWeathersByCity_Country(country.toUpperCase());
        if (weathers.size() == 0)
            throw new RuntimeException();

        for (Weather weather : weathers) {
            average = average + weather.getTemp();
        }

        return average / weathers.size();
    }

    public Weather convertWeatherDataToWeather(WeatherData weatherData, City city) {
        return new Weather(weatherData, city);
    }

    public void updateWeatherForCountry(String country) {
        country = country.toUpperCase();
        List<City> cities = cityService.getCitiesByCountry(country);
        for (City city : cities) {
            updateWeatherForCity(city.getName());
        }
    }

    public Weather updateWeatherForCity(String c) {
        City city = cityService.postCity(c);
        Weather weather = weatherRepository.getWeatherByCity(city);
        weather = setUpdatedValues(city, weather);
        return weatherRepository.save(weather);
    }

    public Weather setUpdatedValues(City city, Weather weather) {
        var weatherData = weatherClient.getWeatherData(city).getBody();
        assert weatherData != null;
        weather.setHumidity(weatherData.main.humidity);
        weather.setPressure(weatherData.main.pressure);
        weather.setTemp(weatherData.main.temp);
        return weather;
    }

    public void deleteWeatherForCity(String c) {
        Weather weather = getWeatherForCity(c);
        weatherRepository.delete(weather);
    }

}
