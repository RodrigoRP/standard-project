package com.rodrigorp.standardprojectapi.service;

import com.rodrigorp.standardprojectapi.dto.PersonNewDto;
import com.rodrigorp.standardprojectapi.model.Person;

public interface PersonService extends GenericService<Person, Long> {
    Person toModel(PersonNewDto personNewDto);
}