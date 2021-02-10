# Worstest Movie

This repository constains the Worstest Movie Api for Golden Raspberry Awards

## Quick start

Follow the instructions bellow to run th project localy.


### Requirements

You will need the [JDK 8](https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html) installed to compile the project.
You will need the Apache maven Version 3.5.4 installed to compile the project 

### About this project

This is a Spring Boot Maven RestFul API whitch uses in memory H2 database to store the worstest movies winners for Golden Raspberry Awards

#### Changing Initial Data Source

To change the initial Data Source replace the `src/main/resources/movielist.csv` file content

### Running the project

```ssh
 mvn spring-boot:run
```

### Access H2 in memory database

-  http://localhost:8080/h2-console

```
jdbc url = jdbc:h2:mem:testdb
user name = sa
password = password
```

### Restful API Herokuapp

#### All movies
- http://jeiel.herokuapp.com/api/movies

#### A single Movie
- http://jeiel.herokuapp.com/api/movies/3

#### A non existin  Movie
- http://jeiel.herokuapp.com/api/movies/300000
```
{"message":"Record Not Found","details":["This movie does not exists!"]}
```

#### All producers
- http://jeiel.herokuapp.com/api/producers

#### All winners' producers
- http://jeiel.herokuapp.com/api/producers/winners

#### All winners' producers in the top and tail awards
- http://jeiel.herokuapp.com/api/producers/winners/intervals/top-tail-awards

### Restful API Local

#### All movies
- http://localhost:8080/api/movies

#### A single Movie
- http://localhost:8080/api/movies/3

#### A non existin  Movie
- http://localhost:8080/api/movies/300000
```
{"message":"Record Not Found","details":["This movie does not exists!"]}
```

#### All producers
- http://localhost:8080/api/producers

#### All winners' producers
- http://localhost:8080/api/producers/winners

#### All winners' producers in the top and tail awards
- http://localhost:8080/api/producers/winners/intervals/top-tail-awards


### Running the integration tests

```
 mvn verify -Pfailsafe
```

## Builded with

* [Spring Boot](http://spring.io/projects/spring-boot)
