package com.rodrigorp.standardprojectapi.controller;

import com.rodrigorp.standardprojectapi.controller.impl.PersonControllerImpl;
import com.rodrigorp.standardprojectapi.model.Address;
import com.rodrigorp.standardprojectapi.model.Person;
import com.rodrigorp.standardprojectapi.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*

@MockMVCTest : annotation is used for Spring MVC tests. It disables full auto-configuration and instead apply only configuration relevant to MVC tests.
@MockMvc : is a class part of Spring MVC Test which help you to test controllers explicitly starting a Servlet container.

*/
@WebMvcTest(PersonControllerImpl.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonServiceImpl service;


    @Test
    void getPersonByIdAPI() throws Exception {
        Person person = new Person(null, "Ronaldo", "Nazario",
                "0000000", "ronaldo@bol.com.br", new Address("Serafim Correa",
                "20", "9999999", "Rio de Janeiro"));

        given(service.findById(1L)).willReturn(person);

        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/person/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(person.getFirstName())))
                .andExpect(jsonPath("$.id", is(person.getId())));
    }









    /*
    https://howtodoinjava.com/spring-boot2/testing/spring-boot-mockmvc-example/
    https://medium.com/backend-habit/integrate-junit-and-mockito-unit-testing-for-controller-layer-91bb4099c2a5*/
}
