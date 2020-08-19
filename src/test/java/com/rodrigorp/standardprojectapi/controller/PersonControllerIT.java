package com.rodrigorp.standardprojectapi.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.basePath = "/api/v1/person";
    }

    @Test
    void should_return_201_when_save_person() {
        String newPerson = "{\n" +
                "  \"cep\": \"97015000\",\n" +
                "  \"city\": \"São Paulo\",\n" +
                "  \"email\": \"adamastor@ig.com.br\",\n" +
                "  \"firstName\": \"Adamastor\",\n" +
                "  \"lastName\": \"Pitaco\",\n" +
                "  \"number\": \"15\",\n" +
                "  \"phone\": \"11999999999\",\n" +
                "  \"street\": \"Av. Paulista\"\n" +
                "}";

        given()
                .body(newPerson)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("/")
                .then()
                .statusCode(HttpStatus.CREATED.value());

    }
}
