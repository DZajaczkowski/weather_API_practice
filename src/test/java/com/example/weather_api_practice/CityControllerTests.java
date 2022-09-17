package com.example.weather_api_practice;

import com.example.weather_api_practice.city.City;
import com.example.weather_api_practice.city.CityRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CityControllerTests {

    @MockBean
    private static CityRepository cityRepository;

    @Before
    public void setUp() {
        City cityWarsaw =
                new City(new UUID(231, 324), "warsaw", 21.0067, 52.232, "PL");

        when(cityRepository.existsCityByName(cityWarsaw.getName()))
                .thenReturn(true);

        when(cityRepository.getCityByName(cityWarsaw.getName()))
                .thenReturn(cityWarsaw);
    }

    @Test
    public void shouldGetCity() {
        given()
                .when()
                .get("/city/warsaw")
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldCountCitiesByCountry() {
        given()
                .when()
                .get("/city/count/pl")
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldCreateCity() {
        given()
                .when()
                .post("/city/save/katowice")
                .then()
                .statusCode(201);
    }

    @Test
    public void shouldDeleteCity() {
        given()
                .when()
                .delete("/city/delete/warsaw")
                .then()
                .statusCode(200);
    }


}
