package com.example.weather_api_practice.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CONFLICT;

@ResponseStatus(code = CONFLICT, reason = "Weather already exists in the database.")
public class WeatherAlreadyExistsException extends RuntimeException {

    public WeatherAlreadyExistsException() {
        super();
    }

}
