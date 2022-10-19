# Spring Boot application for importing a CSV file into PostgreSQL
Import csv file into PostgreSQL by using Apache Commons CSV

## Description

### Dependencies

* Spring Boot
* Spring Web
* Spring Data JPA
* PostgreSQL
* Apache Commons CSV

### Startup

* Run the following command

Generate JAR file

```
mvn install -DskipTests
```
<br/>

Creating of Spring boot image

```
docker build -t csv-db-import.jar .
```
<br/>

Running Spring Boot and PostgreSQL containers

```
docker-compose up -d
```
<br/>

You can test the endpoint API.

### API Endpoints

Base URI path *http://localhost:8080/api/v1*

Method                                                    | HTTP request         | Description
-----------------------------------------------------------|----------------------| -------------
[**uploadFile**](http://localhost:8080/api/v1/feed/upload) | **POST** /feed/upload | Upload the news list file
[**getAllNews**](http://localhost:8080/api/v1/feed/news)       | **GET** /feed/news    | Get all the news
[**findByRole**](http://localhost:8080/api/v1/feed/news/{role})           | **GET** /feed/news/{role}    | Get news by role
[**deleteAll**](http://localhost:8080/api/v1/feed/news)         | **DELETE** /feed/news   | Delete all news
