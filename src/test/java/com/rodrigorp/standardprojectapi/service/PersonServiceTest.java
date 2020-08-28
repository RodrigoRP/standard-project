package com.rodrigorp.standardprojectapi.service;

import com.rodrigorp.standardprojectapi.dto.PersonUpdateDto;
import com.rodrigorp.standardprojectapi.model.Address;
import com.rodrigorp.standardprojectapi.model.Person;
import com.rodrigorp.standardprojectapi.repository.PersonRepository;
import com.rodrigorp.standardprojectapi.service.exception.ObjectNotFoundException;
import com.rodrigorp.standardprojectapi.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class PersonServiceTest {

    /**
     * Autowire in the service we want to test
     */
    @Autowired
    private PersonServiceImpl service;

    /**
     * Create a mock implementation of the WidgetRepository
     */
    @MockBean
    private PersonRepository repository;

    @Test
    @DisplayName("Test save person")
    void testSave() {
        // Setup our mock repository
        Person person = new Person(1L, "Ronaldo", "Nazario",
                "0000000", "ronaldo@bol.com.br", new Address("Serafim Correa",
                "20", "9999999", "Rio de Janeiro"));
        doReturn(person).when(repository).save(any());

        // Execute the service call
        Person returnedPerson = service.save(person);

        // Assert the response
        Assertions.assertNotNull(returnedPerson, "The saved person should not be null");
        Assertions.assertEquals(person, returnedPerson);
    }

    @Test
    @DisplayName("Test findById person")
    void findByIdTest() {
        // Setup our mock repository
        Person person = new Person(1L, "Ronaldo", "Nazario",
                "0000000", "ronaldo@bol.com.br", new Address("Serafim Correa",
                "20", "9999999", "Rio de Janeiro"));
        doReturn(Optional.of(person)).when(repository).findById(person.getId());

        // Execute the service call
        Person returnedPerson = service.findById(person.getId());

        // Assert the response
        Assertions.assertNotNull(returnedPerson, "The saved person should not be null");
        Assertions.assertEquals(person, returnedPerson);
    }

    @Test
    @DisplayName("Test findById person")
    void findByIdExceptionTest() {

        assertThrows(ObjectNotFoundException.class, () -> service.findById(2L));

        verify(repository, times(1)).findById(2L);
    }

    @Test
    @DisplayName("Test findAll person")
    void findAllTest() {
        // Setup our mock repository
        Person person = new Person(1L, "Ronaldo", "Nazario",
                "0000000", "ronaldo@bol.com.br", new Address("Serafim Correa",
                "20", "9999999", "Rio de Janeiro"));
        List<Person> personList = Arrays.asList(person);

        doReturn(personList).when(repository).findAll();

        // Execute the service call
        List<Person> returnedPersonList = service.findAll();

        // Assert the response
        Assertions.assertEquals(personList, returnedPersonList);
    }

    @Test
    @DisplayName("Test deleteById person")
    void deleteByIdTest() {
        // Setup our mock repository
        Person person = new Person(1L, "Ronaldo", "Nazario",
                "0000000", "ronaldo@bol.com.br", new Address("Serafim Correa",
                "20", "9999999", "Rio de Janeiro"));

        doReturn(Optional.of(person)).when(repository).findById(person.getId());

        service.deleteById(person.getId());
        service.deleteById(person.getId());

        verify(repository, times(2)).deleteById(person.getId());
    }

    @Test
    void shouldThrowErrorWhenBeDeleted() {

        assertThrows(ObjectNotFoundException.class, () -> service.deleteById(2L));

        verify(repository, never()).deleteById(2L);
    }

    @Test
    @DisplayName("Test update person")
    void updateByIdTest() {
        // Setup our mock repository
        Person person = new Person(1L, "Ronaldo", "Nazario",
                "0000000", "ronaldo@bol.com.br", new Address("Serafim Correa",
                "20", "9999999", "Rio de Janeiro"));
        doReturn(Optional.of(person)).when(repository).findById(person.getId());

        PersonUpdateDto personUpdateDto = new PersonUpdateDto();
        personUpdateDto.setStreet(JsonNullable.of("Presidente Vargas"));

        // Execute the service call
        service.update(personUpdateDto, person.getId());

        // Assert the response
        Assertions.assertEquals("Presidente Vargas", person.getAddress().getStreet());
    }
}
