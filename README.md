# Weather_API

## About

Spring Boot REST API project created for learning purposes.

The application stores city data using OpenWeatherMap API and allows basic CRUD operations on cities and weather records stored in a PostgreSQL database.


## How to run the application?

### Requirements

* Docker Desktop
* OpenWeatherMap API key (You can generate a free API key at OpenWeatherMap.)

### Environment Variables

Set the following environment variable before starting the application:

```text
WEATHER_API_KEY=<your_openweathermap_api_key>
```

### Run

From the project root:

```bash
docker compose up -d
```

The application will be available at:

```text
http://localhost:8080
```


## Running Tests

Run tests from IntelliJ IDEA or with Maven:

```bash
./mvnw test
```


## Why it was created?

* To practice consuming data from external APIs.
* To practice working with a relational database.
* To further practice Spring Boot.
* To practice Mockito and RestAssured.
* To practice creating Dockerfiles and Docker Compose configurations.


## What does it do?

* Retrieves city data from OpenWeatherMap.
* Retrieves weather information for stored cities.
* Calculates average temperature for a selected country.
* Supports CRUD operations for City and Weather entities.


## Technologies

* Java 17
* Spring Boot
* PostgreSQL
* Docker
* Docker Compose
* Mockito
* RestAssured
* Maven


## More projects:
* [Github Repositories](https://github.com/DZajaczkowski?tab=repositories)
