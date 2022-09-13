package com.example.weather_api_practice.city;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CityData {

    String name;
    public double lon;
    public double lat;
    String country;


}
