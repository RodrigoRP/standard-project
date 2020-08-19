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
class ExceptionControllerUnitTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.basePath = "/api/v1";
    }


    @Test
    void should_return_404_getById() {
        given()
                .pathParam("id", 99)
                .accept(ContentType.JSON)
                .when()
                .get("/person/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }


}
