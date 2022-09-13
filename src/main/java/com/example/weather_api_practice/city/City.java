package com.example.weather_api_practice.city;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(unique = true)
    private String name;
    private double lon;
    private double lat;
    private String country;

    public City(CityData cityData) {
        this.name = cityData.name;
        this.lon = cityData.lon;
        this.lat = cityData.lat;
        this.country = cityData.country;
    }

}
