package com.rodrigorp.standardprojectapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodrigorp.standardprojectapi.dao.PersonRepository;
import com.rodrigorp.standardprojectapi.dto.PersonNewDto;
import com.rodrigorp.standardprojectapi.model.Address;
import com.rodrigorp.standardprojectapi.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
class PersonControllerV2Test {

    private final String BASE_URL = "/api/v1/person";

    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private PersonRepository mockRepository;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }


    @Test
    void should_save_person_and_return_201() throws Exception {
        final PersonNewDto dto = new PersonNewDto("Ronaldo", "Nazario",
                "0000000", "ronaldo@bol.com.br", "Serafim Correa",
                "20", "9999999", "Rio de Janeiro");
        final Person person = new Person(1L, "Ronaldo", "Nazario",
                "0000000", "ronaldo@bol.com.br", new Address("Serafim Correa",
                "20", "9999999", "Rio de Janeiro"));

        when(mockRepository.save(any(Person.class))).thenReturn(person);

        mockMvc.perform(post(BASE_URL + "/")
                .content(objectMapper.writeValueAsString(dto))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(mockRepository, times(1)).save(any(Person.class));
    }

    @Test
    void should_findAll_person_and_return_200() throws Exception {
        final Person person = new Person(1L, "Ronaldo", "Nazario",
                "0000000", "ronaldo@bol.com.br", new Address("Serafim Correa",
                "20", "9999999", "Rio de Janeiro"));
        List<Person> personList = Arrays.asList(person);

        when(mockRepository.findAll()).thenReturn(personList);

        mockMvc.perform(get(BASE_URL + "/"))
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("Ronaldo")));

        verify(mockRepository, times(1)).findAll();
    }


}
