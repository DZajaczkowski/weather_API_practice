package com.example.weather_api_practice.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CONFLICT;

@ResponseStatus(code = CONFLICT, reason = "City already exists in the database.")
public class CityAlreadyExistsException extends RuntimeException {

    public CityAlreadyExistsException() {
        super();
    }
}
