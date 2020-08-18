package com.rodrigorp.standardprojectapi.mapper;

import com.rodrigorp.standardprojectapi.dto.PersonNewDto;
import com.rodrigorp.standardprojectapi.model.Address;
import com.rodrigorp.standardprojectapi.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public Person toModel(PersonNewDto personNewDto) {
        Address address = new Address(personNewDto.getStreet(), personNewDto.getNumber(),
                personNewDto.getCep(), personNewDto.getCity());
        Person person = new Person(null, personNewDto.getFirstName(), personNewDto.getLastName(),
                personNewDto.getPhone(), personNewDto.getEmail(), address);

        return person;
    }
}