package com.example.weather_api_practice;

import com.example.weather_api_practice.city.City;
import com.example.weather_api_practice.city.CityRepository;
import com.example.weather_api_practice.weather.Weather;
import com.example.weather_api_practice.weather.WeatherRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class WeatherControllerTests {

    @MockBean
    private static WeatherRepository weatherRepository;
    @MockBean
    private static CityRepository cityRepository;

    @Before
    public void setUp() {
        City cityWarsaw =
                new City(new UUID(231, 324), "warsaw", 21.0067, 52.232, "PL");
        Weather weatherWarsaw =
                new Weather(756135, cityWarsaw, cityWarsaw.getLon(), cityWarsaw.getLat(), 14.02, 1001, 80);

        when(weatherRepository.existsWeatherByCity_Name(weatherWarsaw.getCity().getName()))
                .thenReturn(true);
        when(weatherRepository.existsWeatherByCity_Country(weatherWarsaw.getCity().getCountry()))
                .thenReturn(true);
        when(cityRepository.existsCityByName(cityWarsaw.getName()))
                .thenReturn(true);

        when(weatherRepository.getWeatherByCity_Name(weatherWarsaw.getCity().getName()))
                .thenReturn(weatherWarsaw);
        when(weatherRepository.getWeathersByCity_Country(weatherWarsaw.getCity().getCountry()))
                .thenReturn(Stream.of(weatherWarsaw).toList());
        when(cityRepository.getCityByName(cityWarsaw.getName()))
                .thenReturn(cityWarsaw);
    }

    @Test
    public void getWeatherForCityShouldGetResponseOK() {
        given()
                .when()
                .get("/weather/warsaw")
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldGetAverageCountryTemperature() {
        given()
                .when()
                .get("/weather/average/pl")
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldPostWeatherForCity() {
        City cityKatowice =
                new City(new UUID(233, 320), "katowice", 19.0216, 50.2599, "PL");
        when(cityRepository.existsCityByName(cityKatowice.getName()))
                .thenReturn(true);
        when(cityRepository.getCityByName("katowice"))
                .thenReturn(cityKatowice);

        given()
                .when()
                .post("/weather/katowice")
                .then()
                .statusCode(201);
    }

    @Test
    public void shouldUpdateWeatherForCity() {
        given()
                .when()
                .put("/weather/warsaw")
                .then()
                .statusCode(201);
    }

    @Test
    public void shouldUpdateWeatherForCountry() {
        given()
                .when()
                .put("/weather/country/pl")
                .then()
                .statusCode(201);
    }

    @Test
    public void shouldDeleteWeatherForCity() {
        given()
                .when()
                .delete("/weather/delete/warsaw")
                .then()
                .statusCode(200);
    }

}
