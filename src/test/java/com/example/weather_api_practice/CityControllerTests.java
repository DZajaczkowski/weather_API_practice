package com.example.weather_api_practice;

import com.example.weather_api_practice.city.City;
import com.example.weather_api_practice.city.CityRepository;
import io.restassured.config.RestAssuredConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.DOUBLE;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isA;
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
    public void shouldGetCityWarsaw() {
        given()
                .config(RestAssuredConfig.config().jsonConfig(jsonConfig().numberReturnType(DOUBLE)))
                .when()
                .get("/city/warsaw")
                .then()
                .statusCode(200)
                .body("name", equalTo("warsaw"),
                        "lon", equalTo(21.0067),
                        "lat", equalTo(52.232),
                        "country", equalTo("PL"));
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
        City cityKatowice =
                new City(new UUID(233, 320), "katowice", 19.0216, 50.2599, "PL");
        when(cityRepository.existsCityByName(cityKatowice.getName()))
                .thenReturn(false);

        given()
                .config(RestAssuredConfig.config().jsonConfig(jsonConfig().numberReturnType(DOUBLE)))
                .when()
                .post("/city/katowice")
                .then()
                .statusCode(201)
                .body("name", equalTo("katowice"),
                "lon", isA(Double.class),
                "lat", isA(Double.class),
                "country", equalTo("PL"));
    }

    @Test
    public void shouldDeleteCity() {
        given()
                .config(RestAssuredConfig.config().jsonConfig(jsonConfig().numberReturnType(DOUBLE)))
                .when()
                .delete("/city/warsaw/delete")
                .then()
                .statusCode(200)
                .body("name", equalTo("warsaw"),
                        "lon", equalTo(21.0067),
                        "lat", equalTo(52.232),
                        "country", equalTo("PL"));
    }


}
