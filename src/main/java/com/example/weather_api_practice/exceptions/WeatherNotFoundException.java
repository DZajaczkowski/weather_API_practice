package com.example.weather_api_practice.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(code = NOT_FOUND, reason = "Weather does not exist in the database.")
public class WeatherNotFoundException extends RuntimeException {

    public WeatherNotFoundException() {
        super();
    }

}
