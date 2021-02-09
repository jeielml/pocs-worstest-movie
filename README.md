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

### Running the integration tests

```
./mvnw test
```

## Builded with

* [Spring Boot](http://spring.io/projects/spring-boot)
