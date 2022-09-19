package com.example.weather_api_practice.city;

import lombok.Data;

@Data
public class CityData {

    String name;
    public double lon;
    public double lat;
    String country;

}
