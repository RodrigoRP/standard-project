package com.rodrigorp.standardprojectapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodrigorp.standardprojectapi.controller.impl.PersonControllerImpl;
import com.rodrigorp.standardprojectapi.dto.PersonNewDto;
import com.rodrigorp.standardprojectapi.model.Address;
import com.rodrigorp.standardprojectapi.model.Person;
import com.rodrigorp.standardprojectapi.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

/*

@MockMVCTest : annotation is used for Spring MVC tests. It disables full auto-configuration and instead apply only configuration relevant to MVC tests.
@MockMvc : is a class part of Spring MVC Test which help you to test controllers explicitly starting a Servlet container.

*/
@WebMvcTest(controllers = PersonControllerImpl.class)
@ActiveProfiles("test")
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonServiceImpl service;

    private List<Person> personList;

    @BeforeEach
    void setUp() {
        this.personList = new ArrayList<>();
        Person person = new Person(1L, "Ronaldo", "Nazario",
                "0000000", "ronaldo@bol.com.br", new Address("Serafim Correa",
                "20", "9999999", "Rio de Janeiro"));
        this.personList.add(person);
    }

    @Test
    void shouldCreateNewPerson() throws Exception {
        given(service.save(any(Person.class))).willAnswer((invocation) -> invocation.getArgument(0));

        PersonNewDto personNewDto = new PersonNewDto("Ronaldo", "Nazario",
                "0000000", "ronaldo@bol.com.br", "Serafim Correa",
                "20", "9999999", "Rio de Janeiro");

        this.mockMvc.perform(post("/api/person/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personNewDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email", is(personNewDto.getEmail())))
                .andExpect(jsonPath("$.lastName", is(personNewDto.getLastName())))
                .andExpect(jsonPath("$.firstName", is(personNewDto.getFirstName())))
        ;
    }

    /*https://medium.com/backend-habit/integrate-junit-and-mockito-unit-testing-for-controller-layer-91bb4099c2a5*/
}
