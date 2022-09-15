package com.example.weather_api_practice.weather;

import com.example.weather_api_practice.city.City;
import com.example.weather_api_practice.weather.data.WeatherData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Weather {

    @Id
    private int id;
    @OneToOne(cascade = CascadeType.PERSIST)
    private City city;
    private double lon;
    private double lat;
    private double temp;
    private int pressure;
    private int humidity;

    public Weather(WeatherData weatherData, City city) {
        this.id = weatherData.id;
        this.city = city;
        this.lon = weatherData.coord.lon;
        this.lat = weatherData.coord.lat;
        this.temp = weatherData.main.temp;
        this.pressure = weatherData.main.pressure;
        this.humidity = weatherData.main.humidity;
    }

}
