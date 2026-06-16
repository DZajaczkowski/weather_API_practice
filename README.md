# Weather_API

## About

Spring Boot REST API project created for learning purposes.
The application stores city data using OpenWeatherMap API and allows basic CRUD operations on cities and weather records stored in a PostgreSQL database.

## What was practiced?

* Consuming data from external APIs
* Working with a relational database
* Spring Boot development
* Testing with Mockito and RestAssured
* Creating Dockerfiles and Docker Compose configurations

## Requirements

* Java 17 with JAVA_HOME configured
* Docker Desktop
* OpenWeatherMap API key (You can generate a free API key at OpenWeatherMap.)

## Environment Variables

Set the following environment variable before starting the application:

```text
WEATHER_API_KEY=<your_openweathermap_api_key>
```

## Running Application

From the project root:

```bash
docker compose up -d
```

The application will be available at:

```text
http://localhost:8080
```

## Running Tests

Docker must be running because integration tests use Testcontainers.
Remember to set the WEATHER_API_KEY variable before starting the tests.
Run tests from IntelliJ IDEA or with Maven:

```bash
./mvnw test
```

## Features

* Integration with OpenWeatherMap API
* Weather data storage in PostgreSQL
* Average temperature calculation by country
* CRUD operations for City and Weather entities

## Technologies

* Java 17
* Spring Boot
* PostgreSQL
* Docker
* Docker Compose
* Mockito
* RestAssured
* Maven

## Other Projects

* [View More Projects](https://github.com/DZajaczkowski?tab=repositories)