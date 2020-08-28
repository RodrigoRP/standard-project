package com.rodrigorp.standardprojectapi.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

//@TestPropertySource("/application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
       /// RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/api/v1/person";
    }

    @Test
    void shouldReturnStatus201_whenSavePerson() {
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

    @Test
    void shouldReturnStatus200_WhenGetPerson() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/")
                .then()
               // .body("", hasSize(2))
                .body("firstName", hasItems("Adamastor"))
                .statusCode(HttpStatus.OK.value());

    }
}
