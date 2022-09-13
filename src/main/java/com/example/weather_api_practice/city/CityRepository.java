package com.example.weather_api_practice.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<City, UUID> {

    boolean existsCityByName(String c);
    City getCityByName(String c);
    int countAllByCountry(String country);
    List<City> getCitiesByCountry(String country);

}
